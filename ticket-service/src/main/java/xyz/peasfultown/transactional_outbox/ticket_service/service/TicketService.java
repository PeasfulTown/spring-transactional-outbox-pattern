package xyz.peasfultown.transactional_outbox.ticket_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.peasfultown.transactional_outbox.ticket_service.entity.Outbox;
import xyz.peasfultown.transactional_outbox.ticket_service.entity.Ticket;
import xyz.peasfultown.transactional_outbox.ticket_service.repository.TicketRepository;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepo;
    private final OutboxService outboxService;

    public Ticket saveTicket(Ticket ticket) {
        Ticket t = ticketRepo.save(ticket);
        try {
            outboxService.saveOutbox(t);
        } catch (Exception e) {
            throw new RuntimeException("problem when trying to save ticket: {}", e.getCause());
        }
        return t;
    }

    public List<Ticket> saveTickets(List<Ticket> tickets) {
        List<Ticket> ts = ticketRepo.saveAll(tickets);
        try {
            outboxService.saveOutboxAll(ts);
        } catch (Exception e) {
            throw new RuntimeException("problem when trying to save ticket: {}", e.getCause());
        }
        return ts;
    }
}
