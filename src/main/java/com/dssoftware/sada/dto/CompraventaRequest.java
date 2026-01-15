package com.dssoftware.sada.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
public record CompraventaRequest(

        @NotNull(message = "El valor de la venta es obligatorio")
        @Min(value = 1, message = "El valor de la venta debe ser mayor a cero")
        Long valorVentaEstablecimiento,
        boolean matricularComprador
) {
}
