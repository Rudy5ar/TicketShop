package org.ticketshop.mapper;

import org.springframework.stereotype.Component;
import org.ticketshop.dto.ManifestationDTO;
import org.ticketshop.model.Manifestation;

@Component
public class ManifestationMapper {

    public ManifestationDTO toDto(Manifestation m) {
        return ManifestationDTO.builder()
                .name(m.getName())
                .numOfSeats(m.getNumOfSeats())
                .priceRegular(m.getPriceRegular())
                .date(m.getDate())
                .type(m.getType())
                .build();
    }

    public Manifestation fromDto(ManifestationDTO dto) {
        return Manifestation.builder()
                .name(dto.name())
                .numOfSeats(dto.numOfSeats())
                .priceRegular(dto.priceRegular())
                .date(dto.date())
                .build();
    }
}
