package com.finca.fincaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finca.fincaapp.model.Trabajador;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {
}
