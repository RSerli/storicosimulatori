package com.utilitysacmitech.storicosimulatori.controller;

// import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utilitysacmitech.storicosimulatori.model.simulatore;
import com.utilitysacmitech.storicosimulatori.model.simulazioneGenerale;
import com.utilitysacmitech.storicosimulatori.service.simulatoreService;
import com.utilitysacmitech.storicosimulatori.service.simulazioneService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/simulatore")
public class detailSimulatorController {

    @Autowired
    private simulatoreService service;

    @Autowired
    private simulazioneService simulazioneService;

     @GetMapping("/{id}")
    public String dettaglioSimulatore(@PathVariable Integer id, Model model) {
        simulatore simulatore = service.findById(id);
        if (simulatore == null) {
            return "redirect:/";
        }
        // sortSimulazioniDesc(simulatore);
        model.addAttribute("simulatore", simulatore);
        model.addAttribute("simulazione", new simulazioneGenerale());
        return "dettaglioSimulatore";
    }

    // private void sortSimulazioniDesc(simulatore simulatore) {
    //     if (simulatore == null || simulatore.getSimulazioniAssociate() == null) {
    //         return;
    //     }
    //     simulatore.getSimulazioniAssociate().sort(Comparator.comparing(sim -> sim.getCreatedAt()).reversed());
    // }

    @PostMapping("/{ide}/aggiungiSimulazione")
    public String saveSimulazione(@PathVariable Integer ide, @Valid simulazioneGenerale simulazione, BindingResult result, Model model) {
        if (result.hasErrors()) {
            simulatore simulatore = service.findById(ide);
            // sortSimulazioniDesc(simulatore);
            model.addAttribute("simulatore", simulatore);
            model.addAttribute("simulazione", simulazione);
            return "dettaglioSimulatore";
        }
        
        simulatore simulatore = service.findById(ide);
        if (simulatore != null) {
            simulazione.setSimulatoreAssociatoImpianto(simulatore);
            simulazioneService.saveSimulazione(simulazione);
        }
        
        return "redirect:/simulatore/" + ide;
    }

    @PostMapping("/{id}/modifica")
    public String updateSimulatore(@PathVariable Integer id, @Valid simulatore simulatore, BindingResult result, Model model) {
        if (result.hasErrors()) {
            simulatore existing = service.findById(id);
            if (existing != null) {
                simulatore.setSimulazioniAssociate(existing.getSimulazioniAssociate());
            }
            // sortSimulazioniDesc(simulatore);
            model.addAttribute("simulatore", simulatore);
            model.addAttribute("simulazione", new simulazioneGenerale());
            model.addAttribute("openEditModal", true);
            return "dettaglioSimulatore";
        }

        simulatore.setId(id);
        service.saveSimulatore(simulatore);
        return "redirect:/simulatore/" + id;
    }
}
