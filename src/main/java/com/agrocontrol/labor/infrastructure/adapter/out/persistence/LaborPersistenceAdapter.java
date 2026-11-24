package com.agrocontrol.labor.infrastructure.adapter.out.persistence;

import com.agrocontrol.campana.infrastructure.adapter.out.persistence.entity.CampanaJpaEntity;
import com.agrocontrol.campana.infrastructure.adapter.out.persistence.repository.SpringDataCampanaRepository;
import com.agrocontrol.labor.domain.model.Labor;
import com.agrocontrol.labor.domain.port.out.LaborRepositoryPort;
import com.agrocontrol.labor.infrastructure.adapter.out.persistence.entity.LaborJpaEntity;
import com.agrocontrol.labor.infrastructure.adapter.out.persistence.mapper.LaborPersistenceMapper;
import com.agrocontrol.labor.infrastructure.adapter.out.persistence.repository.SpringDataLaborRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Esta clase (infraestructura) SÍ puede conocer SpringDataCampanaRepository
 * -para pedir una referencia liviana al padre con getReferenceById-, pero
 * el LaborService (aplicación) jamás la ve: eso solo pasa acá adentro.
 */
@Component
public class LaborPersistenceAdapter implements LaborRepositoryPort {

    private final SpringDataLaborRepository repository;
    private final SpringDataCampanaRepository campanaRepository;

    public LaborPersistenceAdapter(SpringDataLaborRepository repository,
                                    SpringDataCampanaRepository campanaRepository) {
        this.repository = repository;
        this.campanaRepository = campanaRepository;
    }

    @Override
    public Labor guardar(Labor dominio) {
        // Referencia LAZY: no dispara ningún SELECT contra campana, solo
        // arma un proxy con el id para que Hibernate arme el FK al guardar.
        CampanaJpaEntity campanaRef = campanaRepository.getReferenceById(dominio.getCampanaId());
        LaborJpaEntity entity = LaborPersistenceMapper.toJpa(dominio, campanaRef);
        LaborJpaEntity guardada = repository.save(entity);
        return LaborPersistenceMapper.toDomain(guardada);
    }

    @Override
    public Optional<Labor> buscarPorId(Integer id) {
        return repository.findById(id).map(LaborPersistenceMapper::toDomain);
    }

    @Override
    public List<Labor> listarPorCampanaId(Integer campanaId) {
        return repository.findByCampana_Id(campanaId).stream()
                .map(LaborPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existeLaborDuplicadaEnCampana(Integer campanaId, String tipo, LocalDate fechaPlan) {
        return repository.existsByCampana_IdAndTipoIgnoreCaseAndFechaPlan(campanaId, tipo, fechaPlan);
    }
}
