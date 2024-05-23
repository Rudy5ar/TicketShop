package org.ticketshop.dto;

import lombok.Builder;

import java.util.Optional;

@Builder
public record SearchDTO(Optional<String> name, Optional<Integer> priceLow, Optional<Integer> priceHigh,
                        Optional<String> sortBy, Optional<String> filterType, boolean isDescending) {}
