package xyz.peasfultown.transactional_outbox.search_service.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import xyz.peasfultown.transactional_outbox.search_service.domain.Ticket;

@Repository
public interface TicketRepository extends ElasticsearchRepository<Ticket, String> {
}
