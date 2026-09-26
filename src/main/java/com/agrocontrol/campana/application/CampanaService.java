package com.agrocontrol.campana.application;

import com.agrocontrol.campana.domain.Campana;
import com.agrocontrol.campana.infrastructure.adapter.out.persistence.entity.CampanaJpaEntity;
import com.agrocontrol.campana.infrastructure.adapter.out.persistence.mapper.CampanaPersistenceMapper;
import com.agrocontrol.campana.infrastructure.adapter.out.persistence.repository.SpringDataCampanaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CampanaService {

    private final SpringDataCampanaRepository repository;

    public CampanaService(SpringDataCampanaRepository repository) {
        this.repository = repository;
    }

    public Campana registrar(Integer parcelaId, Integer cultivoId, LocalDate fechaInicio) {
        CampanaJpaEntity entity = new CampanaJpaEntity(parcelaId, cultivoId, fechaInicio, "PLANIFICADA");
        CampanaJpaEntity guardada = repository.save(entity);
        return CampanaPersistenceMapper.toDomain(guardada);
    }

    public List<Campana> listar() {
        return repository.findAll().stream()
                .map(CampanaPersistenceMapper::toDomain)
                .toList();
    }

    public Optional<Campana> buscarPorId(Integer id) {
        return repository.findById(id).map(CampanaPersistenceMapper::toDomain);
    }
}