package org.ticketshop.ticketshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ticketshop.ticketshop.model.Manifestacija;
import org.ticketshop.ticketshop.repository.ManifestacijaRepository;

import java.util.List;

@Service
public class ManifestacijaService {

    @Autowired
    private ManifestacijaRepository mr;

    public List<Manifestacija> getAllManifestacija() {
        return mr.findAll();
    }

}
