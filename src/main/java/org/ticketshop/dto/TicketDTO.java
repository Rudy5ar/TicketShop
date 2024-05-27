package org.ticketshop.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TicketDTO(LocalDateTime date, BigDecimal price, String type, Integer status) {
}
