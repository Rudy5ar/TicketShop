package org.ticketshop.mapper;

import org.springframework.stereotype.Component;
import org.ticketshop.dto.ReservedTicketDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReservedTicketMapper {
    public ReservedTicketDTO toDto(Map<String, Integer> reservedTickets, BigDecimal finalPrice) {
        return ReservedTicketDTO.builder()
                .reservedTickets((HashMap<String, Integer>) reservedTickets)
                .finalPrice(finalPrice)
                .build();
    }
}
