package org.ticketshop.mapper;

import org.springframework.stereotype.Component;
import org.ticketshop.dto.ManifestationDTO;
import org.ticketshop.model.Manifestation;

@Component
public class ManifestationMapper {

    public ManifestationDTO toDto(Manifestation m) {
        return new ManifestationDTO(m.getName(), m.getType(), m.getNum_of_seats(),
                m.getDate(), m.getPrice_regular(), m.getLocation());
    }

    public Manifestation fromDto(ManifestationDTO dto) {
        Manifestation m = new Manifestation();
        m.setName(dto.name());
        m.setType(dto.type());
        m.setNum_of_seats(dto.numOfSeats());
        m.setDate(dto.dateTime());
        m.setLocation(dto.location());
        m.setPrice_regular(dto.price());
        return m;
    }

}
