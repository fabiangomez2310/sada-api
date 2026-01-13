package com.dssoftware.sada.dto;

public record ConstitucionRequest(
        long capitalSuscrito,
        long capitalPagado,
        int numeroAccionistas,
        boolean matriculaEstablecimiento,
        boolean aporteEstablecimiento
) {}