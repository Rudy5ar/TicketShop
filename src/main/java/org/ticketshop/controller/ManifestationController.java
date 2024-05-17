package org.ticketshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketshop.model.Manifestation;
import org.ticketshop.service.ManifestationService;

import java.util.List;

@RestController
@RequestMapping("/manifestations")
public class ManifestationController {
    private final ManifestationService manifestationService;

    public ManifestationController(ManifestationService ms){
        this.manifestationService = ms;
    }

    @PostMapping
    public ResponseEntity<Manifestation> createManifestacija(@RequestBody Manifestation m) {
        Manifestation mm = manifestationService.createManifestation(m);
        if (mm != null) {
            return new ResponseEntity<>(mm, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Manifestation>> getAllManifestation() {
        return new ResponseEntity<>(manifestationService.getAllManifestation(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manifestation> getManifestation(@PathVariable int id) {
        Manifestation m = manifestationService.getManifestation(id);
        if(m != null){
            return new ResponseEntity<>(m, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manifestation> updateManifestacija(@RequestBody Manifestation m, @PathVariable int id) {
        Manifestation mm = manifestationService.updateManifestation(m);
        if (mm != null) {
            return new ResponseEntity<>(mm, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManifestacijaById(@PathVariable int id) {
        if(manifestationService.deleteManifestationById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
