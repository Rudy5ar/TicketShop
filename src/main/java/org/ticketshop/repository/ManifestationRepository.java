package org.ticketshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ticketshop.model.Manifestation;

public interface ManifestationRepository extends JpaRepository<Manifestation, Integer> {
}
