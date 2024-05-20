package org.ticketshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketshop.dto.ManifestationDTO;
import org.ticketshop.mapper.ManifestationMapper;
import org.ticketshop.model.Manifestation;
import org.ticketshop.service.ManifestationService;

import java.util.List;

@RestController
@RequestMapping("/api/manifestations")
public class ManifestationController {
    private final ManifestationService manifestationService;
    private final ManifestationMapper manifestationMapper;

    public ManifestationController(ManifestationService service, ManifestationMapper mapper){
        this.manifestationService = service;
        this.manifestationMapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Manifestation> createManifestation(@RequestBody ManifestationDTO manifestationDTO) {
        try {
            Manifestation savedManifestation = manifestationService.createManifestation(manifestationMapper.fromDto(manifestationDTO));
            return new ResponseEntity<>(savedManifestation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Manifestation>> getAllManifestation() {
        return new ResponseEntity<>(manifestationService.getAllManifestation(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manifestation> getManifestation(@PathVariable Long id) {
        try {
            Manifestation foundManifestation = manifestationService.getManifestation(id);
            return new ResponseEntity<>(foundManifestation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manifestation> updateManifestation(@RequestBody ManifestationDTO manifestationDTO, @PathVariable Long id) {
        try {
            Manifestation saved = manifestationService.updateManifestation(manifestationMapper.fromDto(manifestationDTO), id);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteManifestationById(@PathVariable Long id) {
        manifestationService.deleteManifestationById(id);
    }

    @GetMapping("/getSorted/{sortBy}")
    public ResponseEntity<List<Manifestation>> getSortedManifestation(@PathVariable String sortBy) {
        return new ResponseEntity<>(manifestationService.getSorted(sortBy), HttpStatus.OK);
    }
}
