package org.ticketshop.dto;

import lombok.Builder;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Builder
public record ManifestationDTO(String name, int type, int numOfSeats,
                               LocalDateTime date, int priceRegular, String location) {}
