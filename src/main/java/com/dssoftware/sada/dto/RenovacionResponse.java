package com.dssoftware.sada.dto;

public record RenovacionResponse(
        double activos,
        String tipoPersona,

        long renovacionPropietario,
        int establecimientosMisma,
        int establecimientosDiferente,

        long renovacionEstablecimientosMisma,
        long renovacionEstablecimientosDiferente,

        long formularioRues,
        long certificado,

        long totalRenovacion,
        long totalPagar,

        double porcentajeSobreActivos
) {
}
