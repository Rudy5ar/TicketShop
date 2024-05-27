package org.ticketshop.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ticketshop.mapper.TicketMapper;
import org.ticketshop.model.Ticket;
import org.ticketshop.repository.TicketRepository;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
    }

    @Transactional(readOnly = true)
    public Page<Ticket> getAllTickets(Pageable page) {
        return ticketRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
    }

    @Transactional
    public Ticket saveTicket(Ticket ticket) {return ticketRepository.save(ticket);}

    @Transactional
    public Ticket updateTicket(Long id, Ticket ticket) {
        getTicketById(id);
        return ticketRepository.save(ticket);
    }

    @Transactional
    public void deleteTicket(Long id) {ticketRepository.deleteById(id);}

}
