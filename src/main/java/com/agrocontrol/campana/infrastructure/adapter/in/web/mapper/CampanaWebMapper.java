package com.agrocontrol.campana.infrastructure.adapter.in.web.mapper;

import com.agrocontrol.campana.domain.model.Campana;
import com.agrocontrol.campana.infrastructure.adapter.in.web.dto.CampanaResponse;
import com.agrocontrol.campana.infrastructure.adapter.in.web.dto.CrearCampanaRequest;
import org.springframework.stereotype.Component;

/**
 * Traduce entre lo que llega/sale por HTTP (DTOs) y el dominio.
 * El Controller nunca arma un Campana "a mano": se lo pide a este mapper.
 */
@Component
public class CampanaWebMapper {

    public Campana toDomain(CrearCampanaRequest request) {
        return new Campana(request.parcelaId(), request.cultivoId(), request.fechaInicio());
    }

    public CampanaResponse toResponse(Campana campana) {
        return new CampanaResponse(
                campana.getId(),
                campana.getParcelaId(),
                campana.getCultivoId(),
                campana.getFechaInicio(),
                campana.getFechaFin(),
                campana.getEstado()
        );
    }
}
