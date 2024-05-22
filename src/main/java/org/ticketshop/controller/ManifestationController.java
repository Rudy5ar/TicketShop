package org.ticketshop.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/search")
    public ResponseEntity<List<Manifestation>> search(@RequestParam(value = "name", required = false) String name,
                                                            @RequestParam(value = "priceLow", required = false) Integer priceLow,
                                                            @RequestParam(value = "priceHigh", required = false) Integer priceHigh,
                                                            @RequestParam(value = "sortBy", required = false) String sortBy,
                                                            @RequestParam(value = "filterType", required = false) String filterType,
                                                            @RequestParam("pageNumber") int pageNumber,
                                                            @RequestParam("pageSize") int pageSize,
                                                            @RequestParam(value = "isDescending", required = false) boolean isDescending) {

        return new ResponseEntity<>(manifestationService.search(name, priceLow, priceHigh, sortBy, filterType, pageNumber, pageSize, isDescending), HttpStatus.OK);

    }
}
