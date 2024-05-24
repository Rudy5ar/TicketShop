package org.ticketshop.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketshop.dto.LocationDTO;
import org.ticketshop.mapper.LocationMapper;
import org.ticketshop.model.Location;
import org.ticketshop.service.LocationService;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;
    private final LocationMapper locationMapper;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
        this.locationMapper = new LocationMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable long id) {
        try {
            return new ResponseEntity<>(locationMapper.toDto(locationService.getLocationById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getLocations(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return new ResponseEntity<>(locationService.getAllLocations(PageRequest.of(pageNumber, pageSize))
                .stream().map(locationMapper::toDto).toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LocationDTO> createLocation(@RequestBody LocationDTO locationDTO) {
        try {
            Location savedLocation = locationService.saveLocation(locationMapper.fromDto(locationDTO));
            return new ResponseEntity<>(locationMapper.toDto(savedLocation), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> updateLocation(@PathVariable long id, @RequestBody LocationDTO locationDTO) {
        try {
            Location updatedLocation = locationService.updateLocation(id, locationMapper.fromDto(locationDTO));
            return new ResponseEntity<>(locationMapper.toDto(updatedLocation), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable long id) {
        locationService.deleteLocationById(id);
    }

}
