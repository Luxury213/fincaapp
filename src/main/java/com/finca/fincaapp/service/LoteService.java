package com.finca.fincaapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finca.fincaapp.model.Lote;
import com.finca.fincaapp.repository.LoteRepository;

@Service
public class LoteService {

    private final LoteRepository loteRepository;

    public LoteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public List<Lote> listarTodos() {
        return loteRepository.findAll();
    }

    public Lote buscarPorId(Long id) {
        return loteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lote no encontrado"));
    }

    public void guardar(Lote lote) {
        loteRepository.save(lote);
    }

    public void eliminar(Long id) {
        loteRepository.deleteById(id);
    }
}
