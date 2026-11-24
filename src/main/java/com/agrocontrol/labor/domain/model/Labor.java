package com.agrocontrol.labor.domain.model;

import java.time.LocalDate;

/**
 * Entidad de dominio "Labor" (entidad dependiente de Campana).
 * Guarda solamente el ID de su campaña (campanaId), NUNCA una referencia
 * a un objeto CampanaJpaEntity: el dominio no conoce JPA.
 */
public class Labor {

    private final Integer id;
    private final Integer campanaId;
    private final Integer parcelaId;
    private final String tipo;
    private final LocalDate fechaPlan;
    private final LocalDate fechaEjecucion;
    private final String estado;

    /**
     * Constructor para una labor NUEVA, tal como llega desde el request
     * HTTP: todavía no tiene id, todavía no se sabe su parcela (la
     * completa el Service a partir de la campaña), no tiene fecha de
     * ejecución todavía, y arranca en estado PLANIFICADA.
     */
    public Labor(Integer campanaId, String tipo, LocalDate fechaPlan) {
        this(null, campanaId, null, tipo, fechaPlan, null, "PLANIFICADA");
    }

    /**
     * Constructor para reconstruir una labor que YA existe en la base de
     * datos, o para que el Service arme la versión final (con parcelaId
     * completo) justo antes de guardarla.
     */
    public Labor(Integer id, Integer campanaId, Integer parcelaId, String tipo,
                 LocalDate fechaPlan, LocalDate fechaEjecucion, String estado) {
        this.id = id;
        this.campanaId = campanaId;
        this.parcelaId = parcelaId;
        this.tipo = tipo;
        this.fechaPlan = fechaPlan;
        this.fechaEjecucion = fechaEjecucion;
        this.estado = estado;
    }

    public Integer getId() { return id; }
    public Integer getCampanaId() { return campanaId; }
    public Integer getParcelaId() { return parcelaId; }
    public String getTipo() { return tipo; }
    public LocalDate getFechaPlan() { return fechaPlan; }
    public LocalDate getFechaEjecucion() { return fechaEjecucion; }
    public String getEstado() { return estado; }
}
