package com.agrocontrol.campana.infrastructure.adapter.out.persistence.repository;

import com.agrocontrol.campana.infrastructure.adapter.out.persistence.entity.CampanaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCampanaRepository extends JpaRepository<CampanaJpaEntity, Integer> {
}