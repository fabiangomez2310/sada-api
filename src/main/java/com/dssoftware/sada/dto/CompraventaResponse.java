package com.dssoftware.sada.dto;

public record CompraventaResponse(

        //datos base
        double valorVentaEstablecimiento,
        boolean matricularComprador,

        // Gobernacion
        long impuestoRegistro,

        // DIAN
        long retencionDian,

        // Camara de Comercio
        long ingresoDocumento,
        long matriculaComprador,
        long totalCamaraComercio,

        // Total final
        long totalCompraventa
) {
}
