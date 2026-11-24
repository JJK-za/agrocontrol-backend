package com.agrocontrol.labor.domain.port.in;

import com.agrocontrol.labor.domain.model.Labor;

public interface RegistrarLaborUseCase {
    Labor registrar(Labor labor);
}
