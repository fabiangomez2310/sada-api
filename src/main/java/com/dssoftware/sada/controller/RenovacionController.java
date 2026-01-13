package com.dssoftware.sada.controller;

import com.dssoftware.sada.dto.RenovacionRequest;
import com.dssoftware.sada.dto.RenovacionResponse;
import com.dssoftware.sada.service.RenovacionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/renovacion")
public class RenovacionController {

    private final RenovacionService renovacionService;

    public RenovacionController(RenovacionService renovacionService) {
        this.renovacionService = renovacionService;
    }

    @PostMapping("/calcular")
    public RenovacionResponse calcular(@RequestBody RenovacionRequest request) {

        return renovacionService.calcular(request);
    }
}
