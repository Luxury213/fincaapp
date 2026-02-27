package com.finca.fincaapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finca.fincaapp.model.Lote;
import com.finca.fincaapp.service.LoteService;
import com.finca.fincaapp.service.TrabajadorService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/lotes")
@RequiredArgsConstructor
public class LoteController {

    private final LoteService loteService;
    private final TrabajadorService trabajadorService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lotes", loteService.listarTodos());
        return "lotes";
    }

    @PostMapping
    public String crear(@RequestParam String nombre,
                        @RequestParam Integer cantidadPalmas) {

        Lote lote = Lote.builder()
                .nombre(nombre)
                .cantidadPalmas(cantidadPalmas)
                .build();

        loteService.guardar(lote);

        return "redirect:/lotes";
    }

    @GetMapping("/{id}")
    public String verDetalleLote(@PathVariable Long id, Model model) {

        Lote lote = loteService.buscarPorId(id);

        model.addAttribute("lote", lote);
        model.addAttribute("labores", lote.getLabores());

        // trabajadores desde BD
        model.addAttribute("trabajadores", trabajadorService.listarTodos());

        return "lote-detalle";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        loteService.eliminar(id);

        return "redirect:/lotes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {

        Lote lote = loteService.buscarPorId(id);
        model.addAttribute("lote", lote);

        return "editar-lote";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id,
                             @RequestParam String nombre,
                             @RequestParam Integer cantidadPalmas) {

        Lote lote = loteService.buscarPorId(id);

        lote.setNombre(nombre);
        lote.setCantidadPalmas(cantidadPalmas);

        loteService.guardar(lote);

        return "redirect:/lotes";
    }
}