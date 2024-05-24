package org.ticketshop.service;

import org.springframework.stereotype.Service;
import org.ticketshop.model.User;
import org.ticketshop.repository.UserRepository;

@Service
public class UserService {

        UserRepository userRepository;

        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }



}
