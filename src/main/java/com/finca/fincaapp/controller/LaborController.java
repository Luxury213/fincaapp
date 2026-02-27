package com.finca.fincaapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finca.fincaapp.model.Labor;
import com.finca.fincaapp.service.LaborService;
import com.finca.fincaapp.service.TrabajadorService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/labores")
public class LaborController {

    private final LaborService laborService;
    private final TrabajadorService trabajadorService;

    // Crear labor
    @PostMapping("/crear/{loteId}")
    public String crearLabor(
            @PathVariable Long loteId,
            @RequestParam String tipo,
            @RequestParam Integer frecuenciaDias,
            @RequestParam String fechaUltimaEjecucion,
            @RequestParam Long trabajadorId,
            @RequestParam Double precio
    ) {
        Labor labor = Labor.builder()
                .tipo(tipo)
                .frecuenciaDias(frecuenciaDias)
                .build();

        laborService.crearLabor(loteId, labor, fechaUltimaEjecucion, trabajadorId, precio);
        return "redirect:/lotes/" + loteId;
    }

    // Ejecutar labor
    @PostMapping("/ejecutar/{laborId}")
    public String ejecutarLabor(@PathVariable Long laborId) {
        Labor labor = laborService.ejecutarLabor(laborId);
        return "redirect:/lotes/" + labor.getLote().getId();
    }

    // Eliminar labor
    @PostMapping("/eliminar/{laborId}")
    public String eliminarLabor(@PathVariable Long laborId) {
        Long loteId = laborService.obtenerLoteId(laborId);
        laborService.eliminar(laborId);
        return "redirect:/lotes/" + loteId;
    }

    // Formulario editar labor
    @GetMapping("/editar/{id}")
    public String editarLabor(@PathVariable Long id, Model model) {
        Labor labor = laborService.buscarPorId(id);
        model.addAttribute("labor", labor);
        model.addAttribute("trabajadores", trabajadorService.listarTodos());
        return "editar-labor";
    }

    // Actualizar labor
    @PostMapping("/actualizar/{id}")
    public String actualizarLabor(
            @PathVariable Long id,
            @RequestParam String tipo,
            @RequestParam Integer frecuenciaDias,
            @RequestParam String fechaUltimaEjecucion,
            @RequestParam Long trabajadorId,
            @RequestParam Double precio
    ) {
        Labor labor = laborService.actualizarLabor(id, tipo, frecuenciaDias, fechaUltimaEjecucion, trabajadorId, precio);
        return "redirect:/lotes/" + labor.getLote().getId();
    }
}