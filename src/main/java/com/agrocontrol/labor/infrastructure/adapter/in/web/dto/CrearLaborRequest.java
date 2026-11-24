package com.agrocontrol.labor.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CrearLaborRequest(
        @NotNull(message = "La campaña es obligatoria")
        Integer campanaId,

        @NotBlank(message = "El tipo de labor es obligatorio")
        String tipo,

        @NotNull(message = "La fecha planificada es obligatoria")
        LocalDate fechaPlan
) {}
