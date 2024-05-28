package org.ticketshop.mapper;

import org.springframework.stereotype.Component;
import org.ticketshop.dto.BoughtTicketDTO;

import java.math.BigDecimal;

@Component
public class BoughtTicketMapper {
    public BoughtTicketDTO toDto(Integer numRegular, Integer numFan, Integer numVip, BigDecimal finalPrice) {
        return BoughtTicketDTO.builder()
                .numRegular(numRegular)
                .numFan(numFan)
                .numVip(numVip)
                .finalPrice(finalPrice)
                .build();
    }
}
