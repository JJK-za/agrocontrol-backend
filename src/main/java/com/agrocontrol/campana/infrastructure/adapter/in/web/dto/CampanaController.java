package com.agrocontrol.campana.infrastructure.adapter.in.web;

import com.agrocontrol.campana.application.CampanaService;
import com.agrocontrol.campana.domain.Campana;
import com.agrocontrol.campana.infrastructure.adapter.in.web.dto.CampanaResponse;
import com.agrocontrol.campana.infrastructure.adapter.in.web.dto.CrearCampanaRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campanas")
public class CampanaController {

    private final CampanaService service;

    public CampanaController(CampanaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CampanaResponse> crear(@Valid @RequestBody CrearCampanaRequest request) {
        Campana campana = service.registrar(request.parcelaId(), request.cultivoId(), request.fechaInicio());
        return ResponseEntity.status(201).body(toResponse(campana));
    }

    @GetMapping
    public List<CampanaResponse> listar(@RequestParam(required = false) String estado) {
        return service.listar().stream()
                .filter(c -> estado == null || c.getEstado().equalsIgnoreCase(estado))
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanaResponse> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(c -> ResponseEntity.ok(toResponse(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    private CampanaResponse toResponse(Campana campana) {
        return new CampanaResponse(
                campana.getId(), campana.getParcelaId(), campana.getCultivoId(),
                campana.getFechaInicio(), campana.getEstado()
        );
    }
}