package com.finca.fincaapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finca.fincaapp.service.FinanzaService;

@Controller
@RequestMapping("/finanzas")
public class FinanzaController {

    private final FinanzaService finanzaService;

    public FinanzaController(FinanzaService finanzaService) {
        this.finanzaService = finanzaService;
    }

    @GetMapping("/salario/{trabajadorId}")
    public String verSalario(
            @PathVariable Long trabajadorId,
            @RequestParam int mes,
            @RequestParam int anio,
            Model model) {

        double salario = finanzaService.calcularPagoMensual(trabajadorId, mes, anio);

        model.addAttribute("salario", salario);
        model.addAttribute("mes", mes);
        model.addAttribute("anio", anio);

        return "salario";
    }
}