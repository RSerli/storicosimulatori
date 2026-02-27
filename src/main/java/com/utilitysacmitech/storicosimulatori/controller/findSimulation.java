package com.utilitysacmitech.storicosimulatori.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/TrovaSimulazione")

public class findSimulation {

    @GetMapping()
        public String view() {
            return "findSim";
        }

}
