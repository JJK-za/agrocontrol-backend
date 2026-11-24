package com.agrocontrol.shared.web;

import com.agrocontrol.campana.domain.exception.CampanaDuplicadaEnParcelaException;
import com.agrocontrol.campana.domain.exception.CampanaNoEncontradaException;
import com.agrocontrol.labor.domain.exception.LaborDuplicadaException;
import com.agrocontrol.labor.domain.exception.LaborNoEncontradaException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CampanaNoEncontradaException.class)
    public ResponseEntity<ApiError> handleCampanaNoEncontrada(
            CampanaNoEncontradaException ex, HttpServletRequest request) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI(), Map.of());
    }

    @ExceptionHandler(LaborNoEncontradaException.class)
    public ResponseEntity<ApiError> handleLaborNoEncontrada(
            LaborNoEncontradaException ex, HttpServletRequest request) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI(), Map.of());
    }

    @ExceptionHandler(CampanaDuplicadaEnParcelaException.class)
    public ResponseEntity<ApiError> handleCampanaDuplicada(
            CampanaDuplicadaEnParcelaException ex, HttpServletRequest request) {
        return build(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI(), Map.of());
    }

    @ExceptionHandler(LaborDuplicadaException.class)
    public ResponseEntity<ApiError> handleLaborDuplicada(
            LaborDuplicadaException ex, HttpServletRequest request) {
        return build(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI(), Map.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidacion(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        return build(HttpStatus.BAD_REQUEST, "Hay campos inválidos en la solicitud",
                request.getRequestURI(), errores);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenerico(
            Exception ex, HttpServletRequest request) {
        log.error("Error no controlado en {}", request.getRequestURI(), ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocurrió un error inesperado en el servidor", request.getRequestURI(), Map.of());
    }

    private ResponseEntity<ApiError> build(HttpStatus status, String message, String path,
                                           Map<String, String> fieldErrors) {
        ApiError error = new ApiError(
                Instant.now(), status.value(), status.getReasonPhrase(), message, path, fieldErrors
        );
        return ResponseEntity.status(status).body(error);
    }
}