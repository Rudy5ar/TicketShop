package org.ticketshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ticketshop.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findAllByCancelationsGreaterThan(int cancelations);

}
