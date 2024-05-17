package org.ticketshop.service;

import org.springframework.stereotype.Service;
import org.ticketshop.model.Manifestation;
import org.ticketshop.repository.ManifestationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ManifestationService {

    private final ManifestationRepository manifestationRepository;

    public ManifestationService(ManifestationRepository mr) {
        this.manifestationRepository = mr;
    }

    // Create
    public Manifestation createManifestation(Manifestation m) {
        return manifestationRepository.save(m);
    }

    public List<Manifestation> saveAllManifestacije(List<Manifestation> m) {
        manifestationRepository.saveAll(m);
        return m;
    }

    // Read
    public List<Manifestation> getAllManifestation() {
        return (List<Manifestation>) manifestationRepository.findAll();
    }

    public Manifestation getManifestation(int id) {
        Optional<Manifestation> m = manifestationRepository.findById(id);
        return m.orElse(null);
    }

    // Update
    public Manifestation updateManifestation(Manifestation m) {
        if (manifestationRepository.findById(m.getId()).isPresent()){
            return manifestationRepository.save(m);
        }
        return null;
    }

    // Delete
    public boolean deleteManifestationById(int id) {
        if (manifestationRepository.existsById(id)){
            manifestationRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
