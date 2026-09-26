package com.agrocontrol.campana.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CrearCampanaRequest(
        @NotNull(message = "La parcela es obligatoria")
        Long parcelaId,

        @NotNull(message = "El cultivo es obligatorio")
        Long cultivoId,

        @NotNull(message = "La fecha de inicio es obligatoria")
        LocalDate fechaInicio
) {}