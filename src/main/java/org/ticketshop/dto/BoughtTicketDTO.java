package org.ticketshop.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record BoughtTicketDTO(Integer numRegular, Integer numFan, Integer numVip, BigDecimal finalPrice) {
}
