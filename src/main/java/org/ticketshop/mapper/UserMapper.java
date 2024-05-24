package org.ticketshop.mapper;

import org.springframework.stereotype.Component;
import org.ticketshop.dto.UserDTO;
import org.ticketshop.model.User;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .rewardPoints(user.getRewardPoints())
                .username(user.getUserType())
                .userType(user.getUserType())
                .password(user.getPassword())
                .build();
    }

    public User toUser(UserDTO userDTO) {
        return User.builder()
                .firstName(userDTO.firstName())
                .lastName(userDTO.lastName())
                .userType(userDTO.userType())
                .password(userDTO.password())
                .rewardPoints(userDTO.rewardPoints())
                .username(userDTO.username())
                .build();
    }

}
