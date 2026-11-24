package com.agrocontrol.labor.domain.exception;

public class LaborNoEncontradaException extends RuntimeException {

    public LaborNoEncontradaException(Integer id) {
        super("No existe una labor con id " + id);
    }
}