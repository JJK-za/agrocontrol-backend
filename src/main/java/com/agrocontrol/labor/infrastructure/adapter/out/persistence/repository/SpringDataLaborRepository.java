package com.agrocontrol.labor.infrastructure.adapter.out.persistence.repository;

import com.agrocontrol.labor.infrastructure.adapter.out.persistence.entity.LaborJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SpringDataLaborRepository extends JpaRepository<LaborJpaEntity, Integer> {

    List<LaborJpaEntity> findByCampana_Id(Integer campanaId);

    boolean existsByCampana_IdAndTipoIgnoreCaseAndFechaPlan(Integer campanaId, String tipo, LocalDate fechaPlan);
}
