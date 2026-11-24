package com.agrocontrol.labor.infrastructure.adapter.in.web.dto;

import java.time.LocalDate;

public record LaborResponse(
        Integer id,
        Integer campanaId,
        Integer parcelaId,
        String tipo,
        LocalDate fechaPlan,
        LocalDate fechaEjecucion,
        String estado
) {}
