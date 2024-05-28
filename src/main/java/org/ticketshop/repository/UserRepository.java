package org.ticketshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ticketshop.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
