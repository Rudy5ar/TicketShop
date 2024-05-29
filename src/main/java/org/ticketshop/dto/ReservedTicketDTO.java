package org.ticketshop.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.HashMap;

@Builder
public record ReservedTicketDTO(HashMap<String, Integer> reservedTickets, BigDecimal finalPrice) {
}
