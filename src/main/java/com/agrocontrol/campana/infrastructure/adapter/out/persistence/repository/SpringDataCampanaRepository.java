package com.agrocontrol.campana.infrastructure.adapter.out.persistence.repository;

import com.agrocontrol.campana.infrastructure.adapter.out.persistence.entity.CampanaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataCampanaRepository extends JpaRepository<CampanaJpaEntity, Integer> {

    boolean existsByIdParcelaAndEstadoIn(Integer idParcela, List<String> estados);
}