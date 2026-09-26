package com.agrocontrol.campana.domain;

import java.time.LocalDate;

public class Campana {

    private final Long id;
    private final Long parcelaId;
    private final Long cultivoId;
    private final LocalDate fechaInicio;
    private String estado;

    public Campana(Long id, Long parcelaId, Long cultivoId, LocalDate fechaInicio) {
        this.id = id;
        this.parcelaId = parcelaId;
        this.cultivoId = cultivoId;
        this.fechaInicio = fechaInicio;
        this.estado = "PLANIFICADA";
    }

    public Long getId() { return id; }
    public Long getParcelaId() { return parcelaId; }
    public Long getCultivoId() { return cultivoId; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public String getEstado() { return estado; }
}