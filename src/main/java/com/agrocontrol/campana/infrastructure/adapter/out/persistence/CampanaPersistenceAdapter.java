package com.agrocontrol.campana.infrastructure.adapter.out.persistence;

import com.agrocontrol.campana.domain.model.Campana;
import com.agrocontrol.campana.domain.port.out.CampanaRepositoryPort;
import com.agrocontrol.campana.infrastructure.adapter.out.persistence.entity.CampanaJpaEntity;
import com.agrocontrol.campana.infrastructure.adapter.out.persistence.mapper.CampanaPersistenceMapper;
import com.agrocontrol.campana.infrastructure.adapter.out.persistence.repository.SpringDataCampanaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Esta es la ÚNICA clase de todo el módulo Campana que conoce
 * SpringDataCampanaRepository (JPA). Implementa el Port OUT del dominio.
 */
@Component
public class CampanaPersistenceAdapter implements CampanaRepositoryPort {

    private static final List<String> ESTADOS_NO_FINALIZADOS = List.of("PLANIFICADA", "ACTIVA");

    private final SpringDataCampanaRepository repository;

    public CampanaPersistenceAdapter(SpringDataCampanaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Campana guardar(Campana dominio) {
        CampanaJpaEntity entity = CampanaPersistenceMapper.toEntity(dominio);
        CampanaJpaEntity guardada = repository.save(entity);
        return CampanaPersistenceMapper.toDomain(guardada);
    }

    @Override
    public Optional<Campana> buscarPorId(Integer id) {
        return repository.findById(id).map(CampanaPersistenceMapper::toDomain);
    }

    @Override
    public List<Campana> listarTodas() {
        return repository.findAll().stream()
                .map(CampanaPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existeCampanaNoFinalizadaEnParcela(Integer idParcela) {
        return repository.existsByIdParcelaAndEstadoIn(idParcela, ESTADOS_NO_FINALIZADOS);
    }
}
