package org.ticketshop.dto;

import lombok.Builder;

import java.util.HashMap;

@Builder
public record SendReserveInfoDTO(HashMap<String, Integer> ticketsToReserve, Long manifestationId, Long buyerId) {
}
