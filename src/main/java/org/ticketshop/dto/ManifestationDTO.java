package org.ticketshop.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record ManifestationDTO(String name, int type, int num_of_seats,
                               LocalDateTime date, int price_regular, String location) {}
