package org.ticketshop.mapper;

import org.springframework.stereotype.Component;
import org.ticketshop.dto.LocationDTO;
import org.ticketshop.model.Location;

@Component
public class LocationMapper {

    public LocationDTO toDto(Location location) {
        return LocationDTO.builder()
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .address(location.getAddress())
                .build();
    }

    public Location fromDto(LocationDTO locationDto) {
        return Location.builder()
                .address(locationDto.address())
                .latitude(locationDto.latitude())
                .longitude(locationDto.longitude())
                .build();
    }

}
