package com.agrocontrol.campana.domain.port.out;

import com.agrocontrol.campana.domain.model.Campana;

import java.util.List;
import java.util.Optional;

/**
 * Port OUT: qué necesita el núcleo del exterior (de la persistencia),
 * sin saber que esa persistencia es JPA/PostgreSQL.
 * Esta interfaz vive en el dominio; quien la implementa (el Adapter)
 * vive en infraestructura.
 */
public interface CampanaRepositoryPort {

    Campana guardar(Campana campana);

    Optional<Campana> buscarPorId(Integer id);

    List<Campana> listarTodas();

    /**
     * Regla de negocio (RN-01 simplificada): ¿ya hay una campaña
     * PLANIFICADA o ACTIVA en esta parcela?
     */
    boolean existeCampanaNoFinalizadaEnParcela(Integer idParcela);
}
