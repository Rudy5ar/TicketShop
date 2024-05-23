package org.ticketshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.ticketshop.model.Manifestation;

import java.util.List;

public interface ManifestationRepository extends JpaRepository<Manifestation, Long> {

    Page<Manifestation> findAllByNameStartingWithAndTypeStartingWithAndPriceRegularBetween(String name, String type, int lower, int upper, Pageable pageable);
}
