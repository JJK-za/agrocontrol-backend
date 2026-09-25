package com.agrocontrol.campana.application;

import org.springframework.stereotype.Service;

@Service
public class CampanaDemoService {

    public CampanaDemoResponse obtenerDemo() {
        return new CampanaDemoResponse(
                1L,
                "Parcela 10",
                "Maíz",
                "ACTIVA"
        );
    }
}