package com.agrocontrol.campana.infrastructure.adapter.in.web.dto;

import java.time.LocalDate;

public record CampanaResponse(
        Long id,
        Long parcelaId,
        Long cultivoId,
        LocalDate fechaInicio,
        String estado
) {}
