package com.dssoftware.sada.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RenovacionRequest(

        @NotNull(message = "El valor de los activos es obligatorio")
        @Min(value = 0, message = "Los activos no pueden ser negativos")
        Long activos,

        @NotNull(message = "El numero de establecimientos en la misma jurisdiccion es obligatorio")
        @Min(value = 0, message = "No puede ser negativo")
        Integer establecimientosMismaJurisdiccion,

        @NotNull(message = "El numero de establecimientos en otra jurisdiccion es obligatorio")
        @Min(value = 0, message = "No puede ser negativo")
        Integer establecimientosOtraJurisdiccion,

        @NotBlank(message = "El tipo de persona es obligatorio")
                @Pattern(
                        regexp = "PN|PJ",
                        message = "El tipo de persona debe ser PN o PJ"
                )
        String tipoPersona
) {
}
