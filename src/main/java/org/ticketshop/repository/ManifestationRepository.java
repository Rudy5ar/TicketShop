package org.ticketshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.ticketshop.model.Manifestation;

public interface ManifestationRepository extends CrudRepository<Manifestation, Integer> {
}
