package org.ticketshop.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record ManifestationDTO(String name, String type, int numOfSeats,
                               LocalDateTime date, BigDecimal priceRegular, Integer numOfRegularTickets,
                               Integer numOfFanpitTickets, Integer numOfVipTickets) {}
