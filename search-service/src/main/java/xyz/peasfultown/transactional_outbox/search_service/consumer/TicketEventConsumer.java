package xyz.peasfultown.transactional_outbox.search_service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xyz.peasfultown.transactional_outbox.search_service.domain.Ticket;
import xyz.peasfultown.transactional_outbox.search_service.service.TicketService;

@Slf4j
@Component
@RequiredArgsConstructor
public class TicketEventConsumer {
    private final TicketService ticketService;

    @RabbitListener(
            queues = "#{queue.getName}",
            messageConverter = "jsonConverter"
    )
    public void consumeTicketEvent(Ticket ticket) {
        log.info("received ticket event: {}", ticket);
        ticketService.saveTicket(ticket);
        log.info("ticket saved in elasticsearch");
    }
}
