package com.agrocontrol.campana.domain.exception;

/**
 * Regla de negocio (versión simplificada de RN-01): una parcela no puede
 * tener dos campañas "abiertas" (PLANIFICADA o ACTIVA) al mismo tiempo.
 * El GlobalExceptionHandler (Capítulo 08) la traduce a HTTP 409 Conflict.
 */
public class CampanaDuplicadaEnParcelaException extends RuntimeException {

    public CampanaDuplicadaEnParcelaException(Integer idParcela) {
        super("Ya existe una campaña PLANIFICADA o ACTIVA en la parcela " + idParcela);
    }
}
