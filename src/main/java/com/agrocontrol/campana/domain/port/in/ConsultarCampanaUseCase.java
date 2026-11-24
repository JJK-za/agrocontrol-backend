package com.agrocontrol.campana.domain.port.in;

import com.agrocontrol.campana.domain.model.Campana;

import java.util.List;
import java.util.Optional;

/**
 * Port IN: qué puede hacer el sistema respecto a "consultar campañas".
 * El módulo Labor (Capítulo 07) también depende de esta interfaz para
 * comprobar que una campaña exista antes de registrar una labor sobre
 * ella, en lugar de usar el SpringDataCampanaRepository directamente.
 */
public interface ConsultarCampanaUseCase {
    Optional<Campana> buscarPorId(Integer id);
    List<Campana> listar();
}
