package xyz.peasfultown.transactional_outbox.ticket_service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import xyz.peasfultown.transactional_outbox.ticket_service.entity.Ticket;
import xyz.peasfultown.transactional_outbox.ticket_service.service.TicketService;

import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@RequiredArgsConstructor
public class TicketServiceApplication {
	private final TicketService ticketService;

	public static void main(String[] args) {
		SpringApplication.run(TicketServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			List<Ticket> tickets = asList(
					Ticket.builder()
							.subject("Database Seeding Issue")
							.description("Encountered a syntax error while attempting to insert UUID data fields.")
							.build(),

					Ticket.builder()
							.subject("Payment Processing Failure")
							.description("Customer was charged twice during checkout for transaction ref #9948.")
							.build(),

					Ticket.builder()
							.subject("Account Access Locked")
							.description("User entered the wrong password 5 times. Needs manual password profile unlock.")
							.build(),

					Ticket.builder()
							.subject("Slow Page Load Times")
							.description("Dashboard takes over 12 seconds to load when fetching historical transaction logs.")
							.build(),

					Ticket.builder()
							.subject("AMQP Outbox Queue Timeout")
							.description("Messages are stuck in the outbox_queue table and not hitting the main exchange.")
							.build()
			);

			ticketService.saveTickets(tickets);
		};
	}
}
