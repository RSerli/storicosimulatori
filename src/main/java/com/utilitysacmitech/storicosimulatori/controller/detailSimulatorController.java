package com.utilitysacmitech.storicosimulatori.controller;

// import java.util.Comparator;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    private void sortSimulazioni(simulatore simulatore) {
        if (simulatore == null || simulatore.getSimulazioniAssociate() == null) {
            return;
        }
        Collections.sort(simulatore.getSimulazioniAssociate(), (s1, s2) -> s2.getCreatedAt().compareTo(s1.getCreatedAt()));
    }

     @GetMapping("/{id}")
    public String dettaglioSimulatore(@PathVariable Integer id, Model model) {
        simulatore simulatore = service.findById(id);
        if (simulatore == null) {
            return "redirect:/";
        }
        sortSimulazioni(simulatore);
        simulatore.getUltimaSimulazione();
        model.addAttribute("simulatore", simulatore);
        model.addAttribute("simulazione", new simulazioneGenerale());
        return "dettaglioSimulatore";
    }

    @PostMapping("/{ide}/aggiungiSimulazione")
    public String saveSimulazione(@PathVariable Integer ide, @Valid simulazioneGenerale simulazione, BindingResult result, Model model) {
        if (result.hasErrors()) {
            simulatore simulatore = service.findById(ide);
            sortSimulazioni(simulatore);
            model.addAttribute("simulatore", simulatore);
            model.addAttribute("simulazione", simulazione);
            return "dettaglioSimulatore";
        }
        
        simulatore simulatore = service.findById(ide);
        if (simulatore != null) {
            simulazione.setSimulatoreAssociatoImpianto(simulatore);
            simulatore.setIsFree(false);
            simulazioneService.saveSimulazione(simulazione);
        }

        return "redirect:/simulatore/" + ide;
    }

    @PostMapping("/{id}/libera")
    public String liberaSimulatore(@PathVariable Integer id) {
        simulatore simulatore = service.findById(id);
        if (simulatore != null) {
            simulatore.setIsFree(true);
            service.saveSimulatore(simulatore);
        }
        return "redirect:/simulatore/" + id;
    }

    @PostMapping("/{id}/elimina")
    public String eliminaSimulatore(@PathVariable Integer id) {
        simulatore simulatore = service.findById(id);
        if (simulatore != null) {
            service.deleteById(id);
        }
        return "redirect:/";
    }

    @PostMapping("/{id}/modifica")
    public String updateSimulatore(@PathVariable Integer id, @Valid simulatore simulatore, BindingResult result, Model model) {
        if (result.hasErrors()) {
            simulatore existing = service.findById(id);
            if (existing != null) {
                simulatore.setSimulazioniAssociate(existing.getSimulazioniAssociate());
            }
            sortSimulazioni(simulatore);
            model.addAttribute("simulatore", simulatore);
            model.addAttribute("simulazione", new simulazioneGenerale());
            model.addAttribute("openEditModal", true);
            return "dettaglioSimulatore";
        }

        simulatore.setId(id);
        service.saveSimulatore(simulatore);
        return "redirect:/simulatore/" + id;
    }

    @PostMapping("/{id}/aggiungiNota")
    public String aggiungiNota(@PathVariable Integer id, @RequestParam String nota) {
        simulatore simulatore = service.findById(id);
        if (simulatore != null && nota != null && !nota.trim().isEmpty()) {
            if (simulatore.getNoteSimulatore() == null) {
                simulatore.setNoteSimulatore(new java.util.ArrayList<>());
            }
            simulatore.getNoteSimulatore().add(nota.trim());
            service.saveSimulatore(simulatore);
        }
        return "redirect:/simulatore/" + id;
    }
}
