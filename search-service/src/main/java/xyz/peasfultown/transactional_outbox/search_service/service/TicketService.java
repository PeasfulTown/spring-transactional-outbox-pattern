package xyz.peasfultown.transactional_outbox.search_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.peasfultown.transactional_outbox.search_service.domain.Ticket;
import xyz.peasfultown.transactional_outbox.search_service.repository.TicketRepository;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepo;

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepo.save(ticket);
    }
}
