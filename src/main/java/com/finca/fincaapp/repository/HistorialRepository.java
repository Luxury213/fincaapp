package com.finca.fincaapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finca.fincaapp.model.HistorialEjecucion;

public interface HistorialRepository extends JpaRepository<HistorialEjecucion, Long> {

    void deleteByLaborId(Long laborId);

    List<HistorialEjecucion> findByFechaEjecucionBetween(LocalDate inicio, LocalDate fin);

    
}
