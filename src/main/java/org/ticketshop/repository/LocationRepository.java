package org.ticketshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ticketshop.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
