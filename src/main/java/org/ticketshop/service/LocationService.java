package org.ticketshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ticketshop.model.Location;
import org.ticketshop.repository.LocationRepository;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Transactional(readOnly = true)
    public Page<Location> getLocations(Pageable page){
        return locationRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Location getLocation(Long id){
        return locationRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No location found with id: " + id));
    }

    @Transactional
    public Location saveLocation(Location location){
        return locationRepository.save(location);
    }

    @Transactional
    public Location updateLocation(Long id, Location location){
        locationRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No location found with id: " + id));
        return locationRepository.save(location);
    }

    @Transactional
    public void deleteLocationById(Long id){
        locationRepository.deleteById(id);
    }

}
