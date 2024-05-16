package org.ticketshop.ticketshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ticketshop.ticketshop.model.Manifestacija;

@Repository
public interface ManifestacijaRepository extends JpaRepository<Manifestacija, Integer> {


}
