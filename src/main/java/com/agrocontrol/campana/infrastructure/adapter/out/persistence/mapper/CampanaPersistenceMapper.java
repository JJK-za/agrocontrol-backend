package com.agrocontrol.campana.infrastructure.adapter.out.persistence.mapper;

import com.agrocontrol.campana.domain.Campana;
import com.agrocontrol.campana.infrastructure.adapter.out.persistence.entity.CampanaJpaEntity;

public final class CampanaPersistenceMapper {

    private CampanaPersistenceMapper() {}

    public static Campana toDomain(CampanaJpaEntity entity) {
        return new Campana(
                entity.getId(),
                entity.getIdParcela(),
                entity.getIdCultivo(),
                entity.getFechaInicio(),
                entity.getEstado()
        );
    }
}