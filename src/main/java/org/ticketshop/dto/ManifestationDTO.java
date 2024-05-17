package org.ticketshop.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record ManifestationDTO(String name, int type, int numOfSeats,
                               LocalDateTime dateTime, int price, String location) {}
