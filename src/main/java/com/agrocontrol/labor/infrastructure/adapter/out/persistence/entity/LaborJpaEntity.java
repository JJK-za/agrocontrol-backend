package com.agrocontrol.labor.infrastructure.adapter.out.persistence.entity;

import com.agrocontrol.campana.infrastructure.adapter.out.persistence.entity.CampanaJpaEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Relación 1:N UNIDIRECCIONAL (Capítulo 07): esta clase SÍ conoce a
 * CampanaJpaEntity mediante @ManyToOne, pero CampanaJpaEntity NO tiene
 * ninguna colección @OneToMany apuntando hacia Labor. La FK vive acá,
 * del lado "muchos", que es el lado natural de la relación en la BD.
 */
@Entity
@Table(name = "labor", schema = "agrocontrol")
public class LaborJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_labor")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_campana", nullable = false)
    private CampanaJpaEntity campana;

    @Column(name = "id_parcela", nullable = false)
    private Integer idParcela;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "fecha_plan", nullable = false)
    private LocalDate fechaPlan;

    @Column(name = "fecha_ejecucion")
    private LocalDate fechaEjecucion;

    @Column(name = "estado", nullable = false)
    private String estado;

    protected LaborJpaEntity() {
    }

    public LaborJpaEntity(CampanaJpaEntity campana, Integer idParcela, String tipo,
                           LocalDate fechaPlan, LocalDate fechaEjecucion, String estado) {
        this.campana = campana;
        this.idParcela = idParcela;
        this.tipo = tipo;
        this.fechaPlan = fechaPlan;
        this.fechaEjecucion = fechaEjecucion;
        this.estado = estado;
    }

    public Integer getId() { return id; }
    public CampanaJpaEntity getCampana() { return campana; }
    public Integer getIdParcela() { return idParcela; }
    public String getTipo() { return tipo; }
    public LocalDate getFechaPlan() { return fechaPlan; }
    public LocalDate getFechaEjecucion() { return fechaEjecucion; }
    public String getEstado() { return estado; }
}
