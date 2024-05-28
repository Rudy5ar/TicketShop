package org.ticketshop.dto;

import lombok.Builder;

@Builder
public record SendBuyInfoDTO(Integer numRegular, Integer numFan, Integer numVip, Long manifestationId, Long buyerId) {
}
