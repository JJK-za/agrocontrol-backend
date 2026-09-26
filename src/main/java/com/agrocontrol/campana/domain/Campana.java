package com.agrocontrol.campana.domain;

import java.time.LocalDate;

public class Campana {

    private final Integer id;
    private final Integer parcelaId;
    private final Integer cultivoId;
    private final LocalDate fechaInicio;
    private String estado;

    public Campana(Integer id, Integer parcelaId, Integer cultivoId, LocalDate fechaInicio) {
        this.id = id;
        this.parcelaId = parcelaId;
        this.cultivoId = cultivoId;
        this.fechaInicio = fechaInicio;
        this.estado = "PLANIFICADA";
    }

    public Campana(Integer id, Integer parcelaId, Integer cultivoId, LocalDate fechaInicio, String estado) {
        this.id = id;
        this.parcelaId = parcelaId;
        this.cultivoId = cultivoId;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
    }

    public Integer getId() { return id; }
    public Integer getParcelaId() { return parcelaId; }
    public Integer getCultivoId() { return cultivoId; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public String getEstado() { return estado; }
}