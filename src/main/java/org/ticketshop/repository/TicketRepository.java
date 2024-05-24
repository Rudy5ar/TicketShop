package org.ticketshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ticketshop.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
