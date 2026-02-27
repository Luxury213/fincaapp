package com.finca.fincaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finca.fincaapp.model.Lote;

public interface LoteRepository extends JpaRepository<Lote, Long> {
}
