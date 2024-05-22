package org.ticketshop.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional(readOnly = true)
    public List<Manifestation> getAllManifestation() {
        return manifestationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Manifestation getManifestation(Long id) {
        return manifestationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    @Transactional
    public Manifestation updateManifestation(Manifestation manifestation, Long id) {
        manifestationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return manifestationRepository.save(manifestation);
    }

    @Transactional
    public void deleteManifestationById(Long id) {
            manifestationRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Manifestation> getSortedPage(String sortBy, int pageNumber, int pageSize) {
        if (sortBy.equals("name") || sortBy.equals("priceRegular") || sortBy.equals("date") || sortBy.equals("location")) {
            return manifestationRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<Manifestation> filterByType(List<Manifestation> listOfManifestations, int type) {
        return listOfManifestations.stream().filter(m -> m.getType() == type).toList();
    }

    @Transactional(readOnly = true)
    public List<Manifestation> searchByName(String name, int pageNumber, int pageSize) {
        return manifestationRepository.findAllByName(name, PageRequest.of(pageNumber, pageSize));
    }

}
