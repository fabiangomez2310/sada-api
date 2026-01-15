package com.dssoftware.sada.controller;

import com.dssoftware.sada.dto.CompraventaRequest;
import com.dssoftware.sada.dto.CompraventaResponse;
import com.dssoftware.sada.service.CompraventaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compraventa")
public class CompraventaController {

    private final CompraventaService compraventaService;

    public CompraventaController(CompraventaService compraventaService) {
        this.compraventaService = compraventaService;
    }

    @PostMapping("/calcular")
    public CompraventaResponse calcular(
            @Valid @RequestBody CompraventaRequest request) {
        return compraventaService.calcular(request);
    }
}
