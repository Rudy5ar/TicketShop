package org.ticketshop.mapper;

import org.springframework.stereotype.Component;
import org.ticketshop.dto.ReservedTicketDTO;

import java.math.BigDecimal;
import java.util.HashMap;

@Component
public class ReservedTicketMapper {
    public ReservedTicketDTO toDto(HashMap<String, Integer> reservedTickets, BigDecimal finalPrice) {
        return ReservedTicketDTO.builder()
                .reservedTickets(reservedTickets)
                .finalPrice(finalPrice)
                .build();
    }
}
