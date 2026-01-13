package com.dssoftware.sada.dto;

public record CompraventaRequest(
        long valorVentaEstablecimiento,
        boolean matricularComprador
) {
}
