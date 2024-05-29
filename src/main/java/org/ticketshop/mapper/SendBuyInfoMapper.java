package org.ticketshop.mapper;

import org.springframework.stereotype.Component;
import org.ticketshop.dto.SendBuyInfoDTO;

@Component
public class SendBuyInfoMapper {

    public SendBuyInfoDTO toDto(Integer numRegular, Integer numFan,
                                Integer numVip, Long manifestationId, Long buyerId) {
        return SendBuyInfoDTO.builder()
                .numRegular(numRegular)
                .numFan(numFan)
                .numVip(numVip)
                .manifestationId(manifestationId)
                .buyerId(buyerId)
                .build();
    }
}
