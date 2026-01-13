package com.dssoftware.sada.controller;


import com.dssoftware.sada.dto.ConstitucionRequest;
import com.dssoftware.sada.dto.ConstitucionResponse;
import com.dssoftware.sada.service.ConstitucionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/constitucion")
public class ConstitucionController {

    private final ConstitucionService constitucionService;

    public ConstitucionController(ConstitucionService constitucionService) {
        this.constitucionService = constitucionService;
    }

    @PostMapping("/calcular")
    public ConstitucionResponse calcular(@RequestBody ConstitucionRequest request) {
        return constitucionService.calcular(request);
    }
}

