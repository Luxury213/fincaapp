package com.finca.fincaapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finca.fincaapp.entity.Factura;
import com.finca.fincaapp.repository.FacturaRepository;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<Factura> listar() {
        return facturaRepository.findAll();
    }

    public void guardar(Factura factura) {
        facturaRepository.save(factura);
    }
}