package xyz.peasfultown.transactional_outbox.ticket_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.peasfultown.transactional_outbox.ticket_service.entity.Outbox;
import xyz.peasfultown.transactional_outbox.ticket_service.entity.Ticket;
import xyz.peasfultown.transactional_outbox.ticket_service.repository.OutboxRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OutboxService {
    private final OutboxRepository outboxRepo;
    private final ObjectMapper objMapper;

    public void saveOutbox(Ticket t) {
        Outbox o = Outbox.builder()
                .payload(objMapper.valueToTree(t))
                .build();
        outboxRepo.save(o);
    }

    public void saveOutboxAll(List<Ticket> ts) {
        List<Outbox> os = new ArrayList<>();
        ts.forEach(t -> {
            Outbox o = Outbox.builder()
                    .payload(objMapper.valueToTree(t))
                    .build();
            os.add(o);
        });

        outboxRepo.saveAll(os);
    }
}
