package com.utilitysacmitech.storicosimulatori.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utilitysacmitech.storicosimulatori.model.simulatore;
import com.utilitysacmitech.storicosimulatori.repository.simulatoreRepository;

@Controller
@RequestMapping("/")
public class indexController{

    @Autowired
    private simulatoreRepository simulatoreRepo;

    private void sortSimulatori(List<simulatore> simulatori) {
        if (simulatori == null) {
            return;
        }
        else {
             Collections.sort(simulatori, (s1, s2) -> s1.getIpString().compareTo(s2.getIpString()));
         }
    }

    @GetMapping
    public String index(Model model) {
        List<simulatore> simulatori = simulatoreRepo.findAll();
        sortSimulatori(simulatori);
        model.addAttribute("simulatori", simulatori);
        return "index";
    }

}

