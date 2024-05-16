package org.ticketshop.ticketshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.ticketshop.ticketshop.model.Manifestacija;
import org.ticketshop.ticketshop.service.ManifestacijaService;

import java.util.List;

@RestController
@RequestMapping("manifestacija")
public class ManifestacijaController {

    @Autowired
    ManifestacijaService ms;

    @GetMapping("getManifestacije")
    public @ResponseBody List<Manifestacija> getManifestacije() {

        return ms.getAllManifestacija();
    }

}
