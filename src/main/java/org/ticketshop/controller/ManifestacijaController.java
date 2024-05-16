package org.ticketshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketshop.model.Manifestacija;
import org.ticketshop.service.ManifestacijaService;

import java.util.List;

@RestController
@RequestMapping("manifestacija")
public class ManifestacijaController {

    private final ManifestacijaService ms;

    public ManifestacijaController(ManifestacijaService ms){
        this.ms = ms;
    }

    @PostMapping("saveManifestacija")
    public ResponseEntity<Manifestacija> saveManifestacija(@RequestBody Manifestacija m) {
        Manifestacija mm = ms.saveManifestacija(m);
        if (mm != null) {
            return new ResponseEntity<>(mm, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("getAllManifestacija")
    public ResponseEntity<List<Manifestacija>> getAllManifestacija() {
        return new ResponseEntity<>(ms.getAllManifestacija(), HttpStatus.OK);
    }

    @GetMapping("getManifestacija")
    public ResponseEntity<Manifestacija> getManifestacija(@RequestParam int idManifestacija) {
        Manifestacija m = ms.getManifestacija(idManifestacija);
        if(m != null){
            return new ResponseEntity<>(m, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("updateManifestacija")
    public ResponseEntity<Manifestacija> updateManifestacija(@RequestBody Manifestacija m) {
        Manifestacija mm = ms.updateManifestacija(m);
        if (mm != null) {
            return new ResponseEntity<>(mm, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("deleteManifestacijaById")
    public ResponseEntity<String> deleteManifestacijaById(@RequestParam int idManifestacija) {
        if(ms.deleteManifestacijaById(idManifestacija)){
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
