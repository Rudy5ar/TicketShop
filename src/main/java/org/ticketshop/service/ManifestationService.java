package org.ticketshop.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.ticketshop.model.Manifestation;
import org.ticketshop.repository.ManifestationRepository;

import java.util.List;

@Service
public class ManifestationService {

    private final ManifestationRepository manifestationRepository;

    public ManifestationService(ManifestationRepository manifestationRepository) {this.manifestationRepository = manifestationRepository;}

    @Transactional
    public Manifestation createManifestation(Manifestation manifestation) {
        return manifestationRepository.save(manifestation);
    }

    @Transactional
    public List<Manifestation> getAllManifestation() {
        return manifestationRepository.findAll();
    }

    @Transactional
    public Manifestation getManifestation(int id) {
        return manifestationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    @Transactional
    public Manifestation updateManifestation(Manifestation manifestation, int id) {
        manifestationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return manifestationRepository.save(manifestation);
    }

    @Transactional
    public void deleteManifestationById(int id) {
            manifestationRepository.deleteById(id);
    }
}
