package org.ticketshop.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ticketshop.dto.SearchDTO;
import org.ticketshop.model.Manifestation;
import org.ticketshop.model.User;
import org.ticketshop.repository.LocationRepository;
import org.ticketshop.repository.ManifestationRepository;
import org.ticketshop.repository.TicketRepository;
import org.ticketshop.repository.UserRepository;

import java.util.List;

@Service
public class ManifestationService {

    private final ManifestationRepository manifestationRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    public ManifestationService(ManifestationRepository manifestationRepository, LocationRepository locationRepository, UserRepository userRepository) {this.manifestationRepository = manifestationRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

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

    @Transactional
    public Manifestation sellerAddManifestation(Manifestation manifestation, Long locationId, Long userId) {
        try{if(manifestationRepository.findByLocationAndDateBetween(
                manifestation.getLocation(), manifestation.getDate(), manifestation.getDate().plusHours(6)).isEmpty()){
            manifestation.setLocation(locationRepository.findById(locationId).orElseThrow(() -> new EntityNotFoundException("Entity not found")));
            manifestation.setUserSeller(userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Entity not found")));
            manifestationRepository.save(manifestation);
            System.out.println(manifestation.getLocation().getManifestations());
            return manifestation;
            }
        }catch(EntityNotFoundException e){
            return new Manifestation();
        }
        return new Manifestation();
    }

    @Transactional
    public Manifestation approveManifestation(Long manifestation_id){
        Manifestation manifestation = manifestationRepository.findById(manifestation_id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        if(manifestation.getStatus().equals("innactive")){
            manifestation.setStatus("active");
        }
        return manifestationRepository.save(manifestation);

    }

}
