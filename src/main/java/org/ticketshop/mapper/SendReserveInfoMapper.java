package org.ticketshop.mapper;

import org.springframework.stereotype.Component;
import org.ticketshop.dto.SendReserveInfoDTO;

import java.util.HashMap;

@Component
public class SendReserveInfoMapper {

    public SendReserveInfoDTO toDto(HashMap<String, Integer> map, Long manifestationId, Long buyerId) {
        return SendReserveInfoDTO.builder()
                .ticketsToReserve(map)
                .manifestationId(manifestationId)
                .buyerId(buyerId)
                .build();
    }
}
