package com.agrocontrol.campana.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CrearCampanaRequest(
        @NotNull(message = "La parcela es obligatoria")
        Integer parcelaId,

        @NotNull(message = "El cultivo es obligatorio")
        Integer cultivoId,

        @NotNull(message = "La fecha de inicio es obligatoria")
        LocalDate fechaInicio
) {}