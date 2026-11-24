package com.agrocontrol.campana.domain.model;

import java.time.LocalDate;

/**
 * Entidad de dominio "Campaña" (entidad padre).
 * A propósito NO tiene @Entity, @Table ni ninguna anotación de JPA:
 * el dominio no debe saber que existe una base de datos detrás.
 */
public class Campana {

    private final Integer id;
    private final Integer parcelaId;
    private final Integer cultivoId;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final String estado;

    /**
     * Constructor para una campaña NUEVA: todavía no tiene id (lo pone la
     * base de datos), todavía no tiene fecha de fin, y toda campaña nueva
     * arranca en estado PLANIFICADA.
     */
    public Campana(Integer parcelaId, Integer cultivoId, LocalDate fechaInicio) {
        this(null, parcelaId, cultivoId, fechaInicio, null, "PLANIFICADA");
    }

    /**
     * Constructor para reconstruir una campaña que YA existe en la base
     * de datos (lo usa el mapper cuando lee una fila real).
     */
    public Campana(Integer id, Integer parcelaId, Integer cultivoId,
                    LocalDate fechaInicio, LocalDate fechaFin, String estado) {
        this.id = id;
        this.parcelaId = parcelaId;
        this.cultivoId = cultivoId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public Integer getId() { return id; }
    public Integer getParcelaId() { return parcelaId; }
    public Integer getCultivoId() { return cultivoId; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public String getEstado() { return estado; }
}
