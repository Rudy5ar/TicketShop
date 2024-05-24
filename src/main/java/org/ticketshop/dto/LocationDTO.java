package org.ticketshop.dto;

import lombok.Builder;

@Builder
public record LocationDTO(double latitude, double longitude, String address){}
