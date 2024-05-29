package org.ticketshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.given;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.ticketshop.model.Ticket;
import org.ticketshop.repository.TicketRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void testGetAllTickets() {
        Ticket ticket = Ticket.builder()
                .date(LocalDateTime.now())
                .price(BigDecimal.valueOf(100))
                .type("regular")
                .status(1)
                .build();
        Ticket ticket2 = Ticket.builder()
                .date(LocalDateTime.now())
                .price(BigDecimal.valueOf(50))
                .type("fan_pit")
                .status(2)
                .build();
        Ticket ticket3 = Ticket.builder()
                .date(LocalDateTime.now())
                .price(BigDecimal.valueOf(10))
                .type("vip")
                .status(1)
                .build();

        given(ticketRepository.findAll(PageRequest.of(0, 3))).willReturn(new PageImpl<>(List.of(ticket, ticket2, ticket3)));
        Page<Ticket> tickets = ticketService.getAllTickets(PageRequest.of(0, 3));

        assertThat(tickets).isNotNull();
        assertThat(tickets.getTotalElements()).isEqualTo(3);
    }

}
