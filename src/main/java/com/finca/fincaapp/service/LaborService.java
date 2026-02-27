package com.finca.fincaapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finca.fincaapp.model.HistorialEjecucion;
import com.finca.fincaapp.model.Labor;
import com.finca.fincaapp.model.Lote;
import com.finca.fincaapp.model.Trabajador;
import com.finca.fincaapp.repository.HistorialRepository;
import com.finca.fincaapp.repository.LaborRepository;
import com.finca.fincaapp.repository.LoteRepository;
import com.finca.fincaapp.repository.TrabajadorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LaborService {

    private final LaborRepository laborRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final LoteRepository loteRepository;
    private final HistorialRepository historialRepository;


    // Crear labor con trabajador y precio
    @Transactional
    public Labor crearLabor(Long loteId, Labor labor, String fechaTexto, Long trabajadorId, Double precio) {

        Lote lote = loteRepository.findById(loteId).orElseThrow();
        Trabajador trabajador = trabajadorRepository.findById(trabajadorId).orElseThrow();
        LocalDate fecha = LocalDate.parse(fechaTexto);

        labor.setFechaUltimaEjecucion(fecha);
        labor.setProximaFecha(fecha.plusDays(labor.getFrecuenciaDias()));
        labor.setLote(lote);
        labor.setTrabajador(trabajador);
        labor.setPrecio(precio);

        return laborRepository.save(labor);
    }


    // Ejecutar labor
    @Transactional
    public Labor ejecutarLabor(Long laborId) {

        Labor labor = laborRepository.findById(laborId)
                .orElseThrow(() -> new RuntimeException("Labor no encontrada"));

        LocalDate hoy = LocalDate.now();

        HistorialEjecucion historial = HistorialEjecucion.builder()
                .fechaEjecucion(hoy)
                .labor(labor)
                .build();

        historialRepository.save(historial);

        labor.setFechaUltimaEjecucion(hoy);
        labor.setProximaFecha(hoy.plusDays(labor.getFrecuenciaDias()));

        return laborRepository.save(labor);
    }


    // Actualizar labor con trabajador y precio
    @Transactional
    public Labor actualizarLabor(Long id,
                                 String tipo,
                                 Integer frecuenciaDias,
                                 String fechaUltimaEjecucion,
                                 Long trabajadorId,
                                 Double precio) {

        Labor labor = laborRepository.findById(id).orElseThrow();

        labor.setTipo(tipo);
        labor.setFrecuenciaDias(frecuenciaDias);

        if (fechaUltimaEjecucion != null && !fechaUltimaEjecucion.isEmpty()) {

            LocalDate fecha = LocalDate.parse(fechaUltimaEjecucion);

            labor.setFechaUltimaEjecucion(fecha);
            labor.setProximaFecha(fecha.plusDays(frecuenciaDias));
        }

        if (trabajadorId != null) {

            Trabajador trabajador = trabajadorRepository
                    .findById(trabajadorId)
                    .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));

            labor.setTrabajador(trabajador);
        }

        labor.setPrecio(precio);

        return laborRepository.save(labor);
    }


    // Eliminar labor
    @Transactional
    public void eliminar(Long laborId) {

        // borrar historial primero
        historialRepository.deleteByLaborId(laborId);

        // borrar labor
        laborRepository.deleteById(laborId);
    }


    // Obtener loteId desde labor
    public Long obtenerLoteId(Long laborId) {

        Labor labor = laborRepository.findById(laborId).orElseThrow();

        return labor.getLote().getId();
    }


    // Listar labores por lote
    public List<Labor> listarPorLote(Long loteId) {

        return laborRepository.findByLoteId(loteId);
    }


    // Listar todas
    public List<Labor> listarTodas() {

        return laborRepository.findAll();
    }


    // Buscar por id
    public Labor buscarPorId(Long id) {

        return laborRepository.findById(id).orElse(null);
    }

}