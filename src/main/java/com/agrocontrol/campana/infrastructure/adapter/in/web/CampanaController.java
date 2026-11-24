package com.agrocontrol.campana.infrastructure.adapter.in.web;

import com.agrocontrol.campana.domain.exception.CampanaNoEncontradaException;
import com.agrocontrol.campana.domain.model.Campana;
import com.agrocontrol.campana.domain.port.in.ConsultarCampanaUseCase;
import com.agrocontrol.campana.domain.port.in.RegistrarCampanaUseCase;
import com.agrocontrol.campana.infrastructure.adapter.in.web.dto.CampanaResponse;
import com.agrocontrol.campana.infrastructure.adapter.in.web.dto.CrearCampanaRequest;
import com.agrocontrol.campana.infrastructure.adapter.in.web.mapper.CampanaWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller "limpio": solo conoce los Port IN (interfaces) y el mapper.
 * NUNCA inyecta un JpaRepository ni conoce PostgreSQL.
 * Tampoco tiene try/catch: si algo falla, la excepción sube y la agarra
 * el GlobalExceptionHandler (Capítulo 08).
 */
@RestController
@RequestMapping("/api/campanas")
public class CampanaController {

    private final RegistrarCampanaUseCase registrarUseCase;
    private final ConsultarCampanaUseCase consultarUseCase;
    private final CampanaWebMapper mapper;

    public CampanaController(RegistrarCampanaUseCase registrarUseCase,
                              ConsultarCampanaUseCase consultarUseCase,
                              CampanaWebMapper mapper) {
        this.registrarUseCase = registrarUseCase;
        this.consultarUseCase = consultarUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<CampanaResponse> crear(@Valid @RequestBody CrearCampanaRequest request) {
        Campana creada = registrarUseCase.registrar(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(creada));
    }

    @GetMapping
    public List<CampanaResponse> listar(@RequestParam(required = false) String estado) {
        return consultarUseCase.listar().stream()
                .filter(c -> estado == null || c.getEstado().equalsIgnoreCase(estado))
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanaResponse> buscarPorId(@PathVariable Integer id) {
        Campana campana = consultarUseCase.buscarPorId(id)
                .orElseThrow(() -> new CampanaNoEncontradaException(id));
        return ResponseEntity.ok(mapper.toResponse(campana));
    }
}
