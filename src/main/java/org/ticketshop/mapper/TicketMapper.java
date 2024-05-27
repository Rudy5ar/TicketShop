package org.ticketshop.mapper;

import org.springframework.stereotype.Component;
import org.ticketshop.dto.TicketDTO;
import org.ticketshop.model.Ticket;

@Component
public class TicketMapper {

    public TicketDTO toDto(Ticket ticket) {
        return TicketDTO.builder()
                .date(ticket.getDate())
                .price(ticket.getPrice())
                .type(ticket.getType())
                .status(ticket.getStatus())
                .build();
    }

    public Ticket fromDto(TicketDTO ticketDTO){
        return Ticket.builder()
                .date(ticketDTO.date())
                .price(ticketDTO.price())
                .type(ticketDTO.type())
                .status(ticketDTO.status())
                .build();
    }

}
