package com.finca.fincaapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finca.fincaapp.model.Trabajador;
import com.finca.fincaapp.repository.TrabajadorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrabajadorService {

    private final TrabajadorRepository trabajadorRepository;

    public List<Trabajador> listarTodos() {
        return trabajadorRepository.findAll();
    }

    public Trabajador guardar(Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }

    public void eliminar(Long id) {
        trabajadorRepository.deleteById(id);
    }
    public Trabajador buscarPorId(Long id) {
    return trabajadorRepository.findById(id).orElse(null);
}

public void actualizarSueldo(Long id, Double sueldo) {

    Trabajador trabajador = trabajadorRepository.findById(id).orElseThrow();

    trabajador.setSueldoBase(sueldo);

    trabajadorRepository.save(trabajador);
}

}