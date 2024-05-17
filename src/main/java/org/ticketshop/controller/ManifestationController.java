package org.ticketshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketshop.model.Manifestation;
import org.ticketshop.service.ManifestationService;

import java.util.List;

@RestController
@RequestMapping("/api/manifestations")
public class ManifestationController {
    private final ManifestationService manifestationService;

    public ManifestationController(ManifestationService ms){
        this.manifestationService = ms;
    }

    @PostMapping
    public ResponseEntity<Manifestation> createManifestation(@RequestBody Manifestation manifestation) {
        try {
            Manifestation savedManifestation = manifestationService.createManifestation(manifestation);
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
    public ResponseEntity<Manifestation> getManifestation(@PathVariable int id) {
        try {
            Manifestation foundManifestation = manifestationService.getManifestation(id);
            return new ResponseEntity<>(foundManifestation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manifestation> updateManifestation(@RequestBody Manifestation manifestation, @PathVariable int id) {
        try {
            Manifestation saved = manifestationService.updateManifestation(manifestation, id);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteManifestationById(@PathVariable int id) {
        manifestationService.deleteManifestationById(id);
    }
}
