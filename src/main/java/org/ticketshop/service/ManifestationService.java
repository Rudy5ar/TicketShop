package org.ticketshop.service;

import org.springframework.stereotype.Service;
import org.ticketshop.model.Manifestation;
import org.ticketshop.repository.ManifestationRepository;

import java.util.List;

@Service
public class ManifestationService {

    private final ManifestationRepository manifestationRepository;

    public ManifestationService(ManifestationRepository manifestationRepository) {this.manifestationRepository = manifestationRepository;}

    public Manifestation createManifestation(Manifestation manifestation) {
        return manifestationRepository.save(manifestation);
    }

    public List<Manifestation> getAllManifestation() {
        return manifestationRepository.findAll();
    }

    public Manifestation getManifestation(int id) {
        return manifestationRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public Manifestation updateManifestation(Manifestation manifestation, int id) {
        manifestationRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        return manifestationRepository.save(manifestation);
    }

    public void deleteManifestationById(int id) {
            manifestationRepository.deleteById(id);
    }
}
