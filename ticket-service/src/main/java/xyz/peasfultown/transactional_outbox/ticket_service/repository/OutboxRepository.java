package xyz.peasfultown.transactional_outbox.ticket_service.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import xyz.peasfultown.transactional_outbox.ticket_service.entity.Outbox;
import xyz.peasfultown.transactional_outbox.ticket_service.entity.OutboxStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface OutboxRepository extends JpaRepository<Outbox, UUID> {

    @Query("""
            SELECT o FROM Outbox o WHERE o.status = :outboxStatus
            """)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "-2")})
    List<Outbox> findByStatus(OutboxStatus outboxStatus, Pageable pageable);

}
