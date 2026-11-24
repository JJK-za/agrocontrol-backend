package com.agrocontrol.labor.domain.port.in;

import com.agrocontrol.labor.domain.model.Labor;

import java.util.List;
import java.util.Optional;

public interface ConsultarLaborUseCase {
    Optional<Labor> buscarPorId(Integer id);
    List<Labor> listarPorCampana(Integer campanaId);
}
