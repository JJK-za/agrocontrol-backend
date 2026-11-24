package com.agrocontrol.labor.infrastructure.adapter.in.web;

import com.agrocontrol.labor.domain.exception.LaborNoEncontradaException;
import com.agrocontrol.labor.domain.model.Labor;
import com.agrocontrol.labor.domain.port.in.ConsultarLaborUseCase;
import com.agrocontrol.labor.domain.port.in.RegistrarLaborUseCase;
import com.agrocontrol.labor.infrastructure.adapter.in.web.dto.CrearLaborRequest;
import com.agrocontrol.labor.infrastructure.adapter.in.web.dto.LaborResponse;
import com.agrocontrol.labor.infrastructure.adapter.in.web.mapper.LaborWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller del módulo dependiente. Solo conoce los Port IN de Labor
 * y el mapper. No conoce SpringDataLaborRepository ni SpringDataCampanaRepository.
 */
@RestController
@RequestMapping("/api/labores")
public class LaborController {

    private final RegistrarLaborUseCase registrarUseCase;
    private final ConsultarLaborUseCase consultarUseCase;
    private final LaborWebMapper mapper;

    public LaborController(RegistrarLaborUseCase registrarUseCase,
                            ConsultarLaborUseCase consultarUseCase,
                            LaborWebMapper mapper) {
        this.registrarUseCase = registrarUseCase;
        this.consultarUseCase = consultarUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<LaborResponse> crear(@Valid @RequestBody CrearLaborRequest request) {
        Labor creada = registrarUseCase.registrar(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(creada));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaborResponse> buscarPorId(@PathVariable Integer id) {
        Labor labor = consultarUseCase.buscarPorId(id)
                .orElseThrow(() -> new LaborNoEncontradaException(id));
        return ResponseEntity.ok(mapper.toResponse(labor));
    }

    @GetMapping("/campana/{campanaId}")
    public List<LaborResponse> listarPorCampana(@PathVariable Integer campanaId) {
        return consultarUseCase.listarPorCampana(campanaId).stream()
                .map(mapper::toResponse)
                .toList();
    }
}
