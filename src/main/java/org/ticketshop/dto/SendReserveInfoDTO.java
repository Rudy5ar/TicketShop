package org.ticketshop.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public record SendReserveInfoDTO(Map<String, Integer> ticketsToReserve, Long manifestationId, Long buyerId) {
}
