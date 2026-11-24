package com.agrocontrol.campana.infrastructure.adapter.out.persistence.mapper;

import com.agrocontrol.campana.domain.model.Campana;
import com.agrocontrol.campana.infrastructure.adapter.out.persistence.entity.CampanaJpaEntity;

public final class CampanaPersistenceMapper {

    private CampanaPersistenceMapper() {}

    public static CampanaJpaEntity toEntity(Campana dominio) {
        return new CampanaJpaEntity(
                dominio.getParcelaId(),
                dominio.getCultivoId(),
                dominio.getFechaInicio(),
                dominio.getEstado()
        );
    }

    public static Campana toDomain(CampanaJpaEntity entity) {
        return new Campana(
                entity.getId(),
                entity.getIdParcela(),
                entity.getIdCultivo(),
                entity.getFechaInicio(),
                entity.getFechaFin(),
                entity.getEstado()
        );
    }
}