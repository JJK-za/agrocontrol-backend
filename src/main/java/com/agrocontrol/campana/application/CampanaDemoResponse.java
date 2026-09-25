package com.agrocontrol.campana.application;

public record CampanaDemoResponse(
        Long id,
        String parcela,
        String cultivo,
        String estado
) {}