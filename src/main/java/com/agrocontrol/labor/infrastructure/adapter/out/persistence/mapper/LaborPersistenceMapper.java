package com.agrocontrol.labor.infrastructure.adapter.out.persistence.mapper;

import com.agrocontrol.campana.infrastructure.adapter.out.persistence.entity.CampanaJpaEntity;
import com.agrocontrol.labor.domain.model.Labor;
import com.agrocontrol.labor.infrastructure.adapter.out.persistence.entity.LaborJpaEntity;

public final class LaborPersistenceMapper {

    private LaborPersistenceMapper() {}

    /**
     * Recibe la referencia JPA a la campaña (ya obtenida por el Adapter
     * con getReferenceById) y arma la entidad JPA completa.
     */
    public static LaborJpaEntity toJpa(Labor dominio, CampanaJpaEntity campanaRef) {
        return new LaborJpaEntity(
                campanaRef,
                dominio.getParcelaId(),
                dominio.getTipo(),
                dominio.getFechaPlan(),
                dominio.getFechaEjecucion(),
                dominio.getEstado()
        );
    }

    /**
     * El dominio dependiente conserva SOLO el id del padre (campanaId),
     * nunca el objeto CampanaJpaEntity completo.
     */
    public static Labor toDomain(LaborJpaEntity entity) {
        return new Labor(
                entity.getId(),
                entity.getCampana().getId(),
                entity.getIdParcela(),
                entity.getTipo(),
                entity.getFechaPlan(),
                entity.getFechaEjecucion(),
                entity.getEstado()
        );
    }
}
