package com.utilitysacmitech.storicosimulatori.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.utilitysacmitech.storicosimulatori.model.simulazioneGenerale;
import com.utilitysacmitech.storicosimulatori.service.simulazioneService;

@Controller
@RequestMapping("/TrovaSimulazione")
public class findSimulation {

    @Autowired
    private simulazioneService simulazioneService;

    @GetMapping
    public String view(
            @RequestParam(required = false) String nomeImpianto,
            @RequestParam(required = false) String nomeTecnico,
            Model model) {

        boolean hasImpianto = nomeImpianto != null && !nomeImpianto.isBlank();
        boolean hasTecnico = nomeTecnico != null && !nomeTecnico.isBlank();

        Iterable<simulazioneGenerale> simulazioni;

        if (hasImpianto && hasTecnico) {
            simulazioni = simulazioneService
                    .findByNomeImpiantoContainingIgnoreCaseAndNomeTencincoContainingIgnoreCase(nomeImpianto,
                            nomeTecnico);
        } else if (hasImpianto) {
            simulazioni = simulazioneService.findByNomeImpiantoContainingIgnoreCase(nomeImpianto);
        } else if (hasTecnico) {
            simulazioni = simulazioneService.findByNomeTencincoContainingIgnoreCase(nomeTecnico);
        } else {
            simulazioni = simulazioneService.findAll();
        }

        model.addAttribute("simulazioni", simulazioni);
        model.addAttribute("nomeImpianto", nomeImpianto);
        model.addAttribute("nomeTecnico", nomeTecnico);

        return "findSim";
    }

}
