package com.finca.fincaapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finca.fincaapp.model.Trabajador;
import com.finca.fincaapp.service.TrabajadorService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/trabajadores")
@RequiredArgsConstructor
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    @GetMapping
    public String listarTrabajadores(Model model) {
        List<Trabajador> trabajadores = trabajadorService.listarTodos();
        model.addAttribute("trabajadores", trabajadores);
        return "trabajadores/list";
    }
 /*Metodos para agregar trabjadores */
    @GetMapping("/nuevo")
public String mostrarFormulario(Model model) {
    model.addAttribute("trabajador", new Trabajador());
    return "trabajadores/form";
}

@PostMapping("/guardar")
public String guardarTrabajador(@ModelAttribute Trabajador trabajador) {
    trabajadorService.guardar(trabajador);
    return "redirect:/trabajadores";
}
@GetMapping("/editar/{id}")
public String editarTrabajador(@PathVariable Long id, Model model) {
    Trabajador trabajador = trabajadorService.buscarPorId(id);
    model.addAttribute("trabajador", trabajador);
    return "trabajadores/form";
}

@GetMapping("/eliminar/{id}")
public String eliminarTrabajador(@PathVariable Long id) {
    trabajadorService.eliminar(id);
    return "redirect:/trabajadores";
}

}
