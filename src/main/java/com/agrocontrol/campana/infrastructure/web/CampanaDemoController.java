package com.agrocontrol.campana.infrastructure.web;

import com.agrocontrol.campana.application.CampanaDemoResponse;
import com.agrocontrol.campana.application.CampanaDemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/campanas")
public class CampanaDemoController {

    private final CampanaDemoService service;

    public CampanaDemoController(CampanaDemoService service) {
        this.service = service;
    }

    @GetMapping("/demo")
    public CampanaDemoResponse demo() {
        return service.obtenerDemo();
    }
}