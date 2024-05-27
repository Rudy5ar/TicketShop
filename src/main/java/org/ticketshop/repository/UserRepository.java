package org.ticketshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ticketshop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
