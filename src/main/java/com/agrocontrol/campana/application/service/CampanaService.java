package com.agrocontrol.campana.application.service;

import com.agrocontrol.campana.domain.exception.CampanaDuplicadaEnParcelaException;
import com.agrocontrol.campana.domain.model.Campana;
import com.agrocontrol.campana.domain.port.in.ConsultarCampanaUseCase;
import com.agrocontrol.campana.domain.port.in.RegistrarCampanaUseCase;
import com.agrocontrol.campana.domain.port.out.CampanaRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Application Service: implementa los Port IN y es el único que conoce
 * el Port OUT. No sabe nada de JPA, PostgreSQL ni HTTP.
 */
@Service
public class CampanaService implements RegistrarCampanaUseCase, ConsultarCampanaUseCase {

    private final CampanaRepositoryPort repositoryPort;

    public CampanaService(CampanaRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    @Transactional
    public Campana registrar(Campana campana) {
        // Regla de negocio (RN-01 simplificada): no se puede abrir una
        // campaña nueva en una parcela que ya tiene una campaña abierta.
        if (repositoryPort.existeCampanaNoFinalizadaEnParcela(campana.getParcelaId())) {
            throw new CampanaDuplicadaEnParcelaException(campana.getParcelaId());
        }
        return repositoryPort.guardar(campana);
    }

    @Override
    public Optional<Campana> buscarPorId(Integer id) {
        return repositoryPort.buscarPorId(id);
    }

    @Override
    public List<Campana> listar() {
        return repositoryPort.listarTodas();
    }
}
