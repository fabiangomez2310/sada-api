package com.dssoftware.sada.dto;

public record ConstitucionResponse(
        // Datos base
        long capitalSuscrito,
        long capitalPagado,
        int numeroAccionistas,

        // Cámara de Comercio
        long inscripcionDocumento,
        long matriculaPersonaJuridica,
        long formularioRues,
        long matriculaEstablecimiento,
        long aporteEstablecimiento,
        long situacionControl,
        long totalCamaraComercio,

        // Gobernación
        long impuestoRegistroCuantia,
        long impuestoRegistroSinCuantia,
        long totalGobernacion,

        // Total general
        long totalConstitucion
) {}
