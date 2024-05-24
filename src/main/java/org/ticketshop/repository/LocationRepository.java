package org.ticketshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ticketshop.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
