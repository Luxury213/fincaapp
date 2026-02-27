package com.finca.fincaapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacturaController {

    @GetMapping("/test")
    public String test() {
        return "Proyecto funcionando correctamente";
    }
}
