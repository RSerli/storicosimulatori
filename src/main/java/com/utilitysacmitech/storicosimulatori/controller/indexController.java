package com.utilitysacmitech.storicosimulatori.controller;

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

    @GetMapping
    public String index(Model model) {
        List<simulatore> simulatori = simulatoreRepo.findAll();
        model.addAttribute("simulatori", simulatori);
        return "index";
    }

}

