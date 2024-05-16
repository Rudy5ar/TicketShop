package org.ticketshop.service;

import org.springframework.stereotype.Service;
import org.ticketshop.repository.ManifestacijaRepository;
import org.ticketshop.model.Manifestacija;

import java.util.List;
import java.util.Optional;
@Service
public class ManifestacijaService {

    private final ManifestacijaRepository mr;

    public ManifestacijaService(ManifestacijaRepository mr) {
        this.mr = mr;
    }

    // Create
    public Manifestacija saveManifestacija(Manifestacija m) {
        return mr.save(m);
    }

    public List<Manifestacija> saveAllManifestacije(List<Manifestacija> m) {
        for (Manifestacija mi : m) {
            saveManifestacija(mi);
        }
        return m;
    }

    // Read
    public List<Manifestacija> getAllManifestacija() {
        return (List<Manifestacija>) mr.findAll();
    }

    public Manifestacija getManifestacija(int id) {
        Optional<Manifestacija> m = mr.findById(id);
        return m.orElse(null);
    }

    // Update
    public Manifestacija updateManifestacija(Manifestacija m) {
        if (mr.findById(m.getId()).isPresent()){
            return mr.save(m);
        }
        return null;
    }

    // Delete
    public boolean deleteManifestacijaById(int id) {
        if (mr.existsById(id)){
            mr.deleteById(id);
        }
        return !mr.existsById(id);
    }

}
