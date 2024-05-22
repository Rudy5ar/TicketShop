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
        return manifestationRepository.findAll().reversed();
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
    public List<Manifestation> search(String name, Integer priceLow, Integer priceHigh, String sortBy, String filterType, int pageNumber, int pageSize, boolean isDescending) {
        List<Manifestation> manifestations = null;

        if (sortBy == null || !(sortBy.equals("name") || sortBy.equals("priceRegular") || sortBy.equals("date") || sortBy.equals("location"))) {
            sortBy = "id";
        }


        if(name == null && (priceLow == null || priceHigh == null)){
            manifestations = manifestationRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy))).toList();
        }
        else if(name != null && (priceLow == null || priceHigh == null)){
            manifestations = manifestationRepository.findAllByNameStartingWith(name, PageRequest.of(pageNumber, pageSize, Sort.by(sortBy))).toList();
        }
        else {
            manifestations = manifestationRepository.findAllByPriceRegularBetween(priceLow, priceHigh, PageRequest.of(pageNumber, pageSize, Sort.by(sortBy))).toList();
        }


        if(filterType != null){
            manifestations = manifestations.stream().filter(m -> m.getType().equals(filterType)).toList();
        }

        if(isDescending){
            return manifestations.reversed();
        }
        return manifestations;
    }

}
