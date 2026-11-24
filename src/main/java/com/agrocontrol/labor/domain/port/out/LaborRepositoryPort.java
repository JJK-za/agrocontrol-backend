package com.agrocontrol.labor.domain.port.out;

import com.agrocontrol.labor.domain.model.Labor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LaborRepositoryPort {

    Labor guardar(Labor labor);

    Optional<Labor> buscarPorId(Integer id);

    List<Labor> listarPorCampanaId(Integer campanaId);

    boolean existeLaborDuplicadaEnCampana(Integer campanaId, String tipo, LocalDate fechaPlan);
}
