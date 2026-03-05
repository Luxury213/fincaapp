package com.finca.fincaapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finca.fincaapp.model.HistorialEjecucion;
import com.finca.fincaapp.model.Trabajador;
import com.finca.fincaapp.repository.HistorialRepository;
import com.finca.fincaapp.repository.TrabajadorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FinanzaService {

    private final TrabajadorRepository trabajadorRepository;
    private final HistorialRepository historialRepository;

    public double calcularPagoMensual(Long trabajadorId, int mes, int anio) {

        Trabajador trabajador = trabajadorRepository.findById(trabajadorId)
                .orElseThrow();

        LocalDate inicio = LocalDate.of(anio, mes, 1);
        LocalDate fin = inicio.withDayOfMonth(inicio.lengthOfMonth());

        List<HistorialEjecucion> historial =
                historialRepository.findByFechaEjecucionBetween(inicio, fin);

        double extras = historial.stream()
                .filter(h -> h.getLabor().getTrabajador().getId().equals(trabajadorId))
                .mapToDouble(h -> h.getLabor().getPrecio())
                .sum();

        if(trabajador.getSueldoBase() == null){
            return extras;
        }

        return trabajador.getSueldoBase() + extras;
    }

}