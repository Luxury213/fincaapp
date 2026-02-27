package com.finca.fincaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finca.fincaapp.model.HistorialEjecucion;

public interface HistorialRepository extends JpaRepository<HistorialEjecucion, Long> {

    void deleteByLaborId(Long laborId);
}
