package com.agrocontrol.labor.application.service;

import com.agrocontrol.campana.domain.exception.CampanaNoEncontradaException;
import com.agrocontrol.campana.domain.model.Campana;
import com.agrocontrol.campana.domain.port.in.ConsultarCampanaUseCase;
import com.agrocontrol.labor.domain.exception.LaborDuplicadaException;
import com.agrocontrol.labor.domain.model.Labor;
import com.agrocontrol.labor.domain.port.in.ConsultarLaborUseCase;
import com.agrocontrol.labor.domain.port.in.RegistrarLaborUseCase;
import com.agrocontrol.labor.domain.port.out.LaborRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Application Service del módulo dependiente (Labor).
 *
 * PATRÓN CORRECTO (Capítulo 07): para saber si la campaña padre existe,
 * este Service depende de ConsultarCampanaUseCase (el Port IN público
 * del módulo Campana), NUNCA de SpringDataCampanaRepository directamente.
 * Así el módulo Labor jamás toca la tecnología de persistencia de Campana.
 */
@Service
public class LaborService implements RegistrarLaborUseCase, ConsultarLaborUseCase {

    private final LaborRepositoryPort repositoryPort;
    private final ConsultarCampanaUseCase consultarCampanaUseCase;

    public LaborService(LaborRepositoryPort repositoryPort,
                         ConsultarCampanaUseCase consultarCampanaUseCase) {
        this.repositoryPort = repositoryPort;
        this.consultarCampanaUseCase = consultarCampanaUseCase;
    }

    @Override
    @Transactional
    public Labor registrar(Labor nuevaLabor) {
        // 1. validar existencia del padre (usando el Port IN de Campana, no su repositorio)
        Campana campana = consultarCampanaUseCase.buscarPorId(nuevaLabor.getCampanaId())
                .orElseThrow(() -> new CampanaNoEncontradaException(nuevaLabor.getCampanaId()));

        // 2. validar conflicto de negocio (regla de duplicado real del proyecto)
        if (repositoryPort.existeLaborDuplicadaEnCampana(
                nuevaLabor.getCampanaId(), nuevaLabor.getTipo(), nuevaLabor.getFechaPlan())) {
            throw new LaborDuplicadaException(nuevaLabor.getTipo(), nuevaLabor.getFechaPlan());
        }

        // 3. construir dominio final: la parcela de la labor es siempre la
        //    misma parcela de su campaña (no la envía el cliente HTTP,
        //    así evitamos que quede una labor "chueca" apuntando a otra parcela)
        Labor laborCompleta = new Labor(
                null,
                nuevaLabor.getCampanaId(),
                campana.getParcelaId(),
                nuevaLabor.getTipo(),
                nuevaLabor.getFechaPlan(),
                null,
                "PLANIFICADA"
        );

        // 4. guardar por Port OUT
        return repositoryPort.guardar(laborCompleta);
    }

    @Override
    public Optional<Labor> buscarPorId(Integer id) {
        return repositoryPort.buscarPorId(id);
    }

    @Override
    public List<Labor> listarPorCampana(Integer campanaId) {
        return repositoryPort.listarPorCampanaId(campanaId);
    }
}
