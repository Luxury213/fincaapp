package com.finca.fincaapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finca.fincaapp.model.Labor;

public interface LaborRepository extends JpaRepository<Labor, Long> {

    List<Labor> findByLoteId(Long loteId);
}