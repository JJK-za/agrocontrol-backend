package com.agrocontrol.labor.infrastructure.adapter.in.web.mapper;

import com.agrocontrol.labor.domain.model.Labor;
import com.agrocontrol.labor.infrastructure.adapter.in.web.dto.CrearLaborRequest;
import com.agrocontrol.labor.infrastructure.adapter.in.web.dto.LaborResponse;
import org.springframework.stereotype.Component;

@Component
public class LaborWebMapper {

    public Labor toDomain(CrearLaborRequest request) {
        return new Labor(request.campanaId(), request.tipo(), request.fechaPlan());
    }

    public LaborResponse toResponse(Labor labor) {
        return new LaborResponse(
                labor.getId(),
                labor.getCampanaId(),
                labor.getParcelaId(),
                labor.getTipo(),
                labor.getFechaPlan(),
                labor.getFechaEjecucion(),
                labor.getEstado()
        );
    }
}
