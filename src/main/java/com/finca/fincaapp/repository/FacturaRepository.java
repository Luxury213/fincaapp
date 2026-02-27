package com.finca.fincaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finca.fincaapp.entity.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
