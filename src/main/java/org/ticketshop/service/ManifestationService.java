package org.ticketshop.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ticketshop.dto.SearchDTO;
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
    public Page<Manifestation> search(SearchDTO searchDTO, int pageNumber, int pageSize) {
        String sortBy = searchDTO.sortBy()
                .filter(sort -> sort.equals("name") || sort.equals("priceRegular") || sort.equals("date") || sort.equals("location") )
                .orElse("id");
        Sort.Direction direction = searchDTO.isDescending() ? Sort.Direction.DESC : Sort.Direction.ASC;


        return manifestationRepository.findAllByNameStartingWithAndTypeStartingWithAndPriceRegularBetween(
                                                        searchDTO.name().orElse("")
                                                        ,searchDTO.filterType().orElse("")
                                                        ,searchDTO.priceLow().orElse(Integer.MIN_VALUE)
                                                        ,searchDTO.priceHigh().orElse(Integer.MAX_VALUE)
                                                        ,PageRequest.of(pageNumber, pageSize, direction, sortBy));
    }

}
