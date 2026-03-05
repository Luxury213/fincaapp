package com.finca.fincaapp.controller;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.finca.fincaapp.service.FinanzaService;
import com.finca.fincaapp.service.TrabajadorService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FinanzaController {

    private final TrabajadorService trabajadorService;
    private final FinanzaService finanzaService;

    @GetMapping("/finanzas")
    public String verFinanzas(Model model) {

        model.addAttribute("trabajadores", trabajadorService.listarTodos());

        return "finanzas/list";
    }

@GetMapping("/finanzas/reporte/{trabajadorId}")
public String verReporte(
        @PathVariable Long trabajadorId,
        @RequestParam int mes,
        @RequestParam int anio,
        Model model
) {

    double pago = finanzaService.calcularPagoMensual(trabajadorId, mes, anio);

    String nombreMes = Month.of(mes)
            .getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

    model.addAttribute("trabajador",
            trabajadorService.buscarPorId(trabajadorId));

    model.addAttribute("mes", nombreMes);
    model.addAttribute("anio", anio);
    model.addAttribute("pago", pago);

    return "finanzas/reporte";
}

}