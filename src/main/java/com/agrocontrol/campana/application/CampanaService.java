package com.agrocontrol.campana.application;

import com.agrocontrol.campana.domain.Campana;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CampanaService {

    private final List<Campana> elementos = new ArrayList<>();
    private final AtomicLong secuencia = new AtomicLong(0);

    public Campana registrar(Long parcelaId, Long cultivoId, LocalDate fechaInicio) {
        Campana campana = new Campana(secuencia.incrementAndGet(), parcelaId, cultivoId, fechaInicio);
        elementos.add(campana);
        return campana;
    }

    public List<Campana> listar() {
        return List.copyOf(elementos);
    }

    public Optional<Campana> buscarPorId(Long id) {
        return elementos.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }
}