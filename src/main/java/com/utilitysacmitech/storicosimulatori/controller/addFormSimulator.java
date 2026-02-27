package com.utilitysacmitech.storicosimulatori.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utilitysacmitech.storicosimulatori.model.simulatore;
import com.utilitysacmitech.storicosimulatori.service.simulatoreService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/AggiungiSimulatore")

public class addFormSimulator {

    @Autowired
    private simulatoreService service;

    @GetMapping()
    public String viewForm(Model model) {
        model.addAttribute("simulatore", new simulatore());
        return "addSimulator";
    }

    @PostMapping()
    public String saveSimulatore(@Valid simulatore simulatore, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addSimulator";
        }
        service.saveSimulatore(simulatore);
        return "redirect:/";
    }

}
