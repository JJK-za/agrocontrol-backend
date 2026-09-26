package com.agrocontrol.campana.infrastructure.adapter.in.web.dto;

import java.time.LocalDate;

public record CampanaResponse(
        Integer id,
        Integer parcelaId,
        Integer cultivoId,
        LocalDate fechaInicio,
        String estado
) {}