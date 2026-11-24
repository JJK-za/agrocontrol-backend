package com.agrocontrol.campana.domain.exception;

/**
 * Se lanza cuando alguien pide una campaña por id y esa campaña no existe.
 * El GlobalExceptionHandler (Capítulo 08) la traduce a HTTP 404.
 */
public class CampanaNoEncontradaException extends RuntimeException {

    public CampanaNoEncontradaException(Integer id) {
        super("No existe una campaña con id " + id);
    }
}
