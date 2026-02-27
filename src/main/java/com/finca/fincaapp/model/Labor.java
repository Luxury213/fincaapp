package com.finca.fincaapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Labor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private Integer frecuenciaDias;

    private LocalDate fechaUltimaEjecucion;

    private LocalDate proximaFecha;

    private Double precio;

    // RELACION CON LOTE
    @ManyToOne
    @JoinColumn(name = "lote_id")
    private Lote lote;

    // RELACION CON TRABAJADOR 
    @ManyToOne
    @JoinColumn(name = "trabajador_id")
    private Trabajador trabajador;
}