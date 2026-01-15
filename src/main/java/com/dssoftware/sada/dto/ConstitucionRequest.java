package com.dssoftware.sada.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ConstitucionRequest(

        @NotNull(message = "El capital suscrito es obligatorio")
        @Min(value = 1, message = "El capital suscrito debe ser mayor a 0")
        Long capitalSuscrito,

        @NotNull(message = "El capital pagado es obligatorio")
        @Min(value = 1, message = "El capital pagado debe ser mayor a 0")
        Long capitalPagado,

        @NotNull(message = "El numero de accionistas es obligatorio")
        @Min(value = 1, message = "Debe existir al menos un accionista")
        Integer numeroAccionistas,

        @NotNull(message = "Debe indicar si se matricula el establecimiento")
        Boolean matriculaEstablecimiento,

        @NotNull(message = "Debe indicar si existe aporte de establecimiento")
        Boolean aporteEstablecimiento
) {}