package com.agrocontrol.campana.domain.port.in;

import com.agrocontrol.campana.domain.model.Campana;

/**
 * Port IN: qué puede hacer el sistema respecto a "registrar una campaña".
 * El Controller depende de ESTA interfaz, nunca de la clase concreta.
 */
public interface RegistrarCampanaUseCase {
    Campana registrar(Campana campana);
}
