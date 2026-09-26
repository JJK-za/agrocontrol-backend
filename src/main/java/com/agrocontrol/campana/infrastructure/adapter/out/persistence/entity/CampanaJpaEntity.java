package com.agrocontrol.campana.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "campana", schema = "agrocontrol")
public class CampanaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campana")
    private Integer id;

    @Column(name = "id_parcela", nullable = false)
    private Integer idParcela;

    @Column(name = "id_cultivo", nullable = false)
    private Integer idCultivo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "estado", nullable = false)
    private String estado;

    protected CampanaJpaEntity() {
    }

    public CampanaJpaEntity(Integer idParcela, Integer idCultivo, LocalDate fechaInicio, String estado) {
        this.idParcela = idParcela;
        this.idCultivo = idCultivo;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
    }

    public Integer getId() { return id; }
    public Integer getIdParcela() { return idParcela; }
    public Integer getIdCultivo() { return idCultivo; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public String getEstado() { return estado; }
}