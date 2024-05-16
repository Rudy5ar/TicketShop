package org.ticketshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ticketshop.model.Manifestacija;

@Repository
public interface ManifestacijaRepository extends CrudRepository<Manifestacija, Integer> {


}
