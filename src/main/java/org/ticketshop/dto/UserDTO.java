package org.ticketshop.dto;

import lombok.Builder;

@Builder
public record UserDTO(String username, String firstName, String lastName, Integer rewardPoints, String userType,
                      String password) {
}
