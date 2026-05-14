package xyz.peasfultown.transactional_outbox.ticket_service.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.peasfultown.transactional_outbox.ticket_service.config.RabbitMqConstants;
import xyz.peasfultown.transactional_outbox.ticket_service.entity.Outbox;
import xyz.peasfultown.transactional_outbox.ticket_service.entity.OutboxStatus;
import xyz.peasfultown.transactional_outbox.ticket_service.entity.Ticket;
import xyz.peasfultown.transactional_outbox.ticket_service.repository.OutboxRepository;

import java.util.List;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class OutboxScheduler {
    private final OutboxRepository outboxRepo;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objMapper;

    @Scheduled(initialDelay = 5000, fixedDelay = 1000)
    @Transactional
    public void pollAndPublish() {
        log.info("polling outbox table");
        List<Outbox> pending = outboxRepo.findByStatus(OutboxStatus.PENDING, PageRequest.of(0, 50));

        if (pending.isEmpty())
            return;

        for (Outbox o : pending) {
            try {
                Message m = MessageBuilder.withBody(objMapper.writeValueAsBytes(o.getPayload()))
                        .setHeader("__TypeId__", Ticket.class.getSimpleName())
                        .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                        .build();

                rabbitTemplate.send(RabbitMqConstants.exchange, RabbitMqConstants.routingKey, m);
                log.info("sent outbox event message: {}", o.getPayload().toPrettyString());
                outboxRepo.delete(o);
                log.info("deleted outbox event message");
            } catch (Exception e) {
                log.error("failed to send outbox event message", e);
                o.setStatus(OutboxStatus.FAILED);
                outboxRepo.save(o);
            }
        }
    }
}
