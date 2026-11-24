package com.agrocontrol.labor.domain.exception;

import java.time.LocalDate;

/**
 * Regla de conflicto real del proyecto (tu tabla "labor" no tiene una
 * columna UNIQUE propia, así que en vez de eso se valida esto: no se
 * puede registrar dos veces la misma labor -mismo tipo, misma fecha
 * planificada- dentro de la misma campaña).
 * El GlobalExceptionHandler (Capítulo 08) la traduce a HTTP 409 Conflict.
 */
public class LaborDuplicadaException extends RuntimeException {

    public LaborDuplicadaException(String tipo, LocalDate fechaPlan) {
        super("Ya existe una labor de tipo '" + tipo + "' planificada para el "
                + fechaPlan + " en esa campaña");
    }
}
