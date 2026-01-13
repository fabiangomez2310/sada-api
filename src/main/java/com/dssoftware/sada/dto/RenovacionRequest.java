package com.dssoftware.sada.dto;

public record RenovacionRequest(
        long activos,
        int establecimientosMismaJurisdiccion,
        int establecimientosOtraJurisdiccion,
        String tipoPersona
) {
}
