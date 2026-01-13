package com.dssoftware.sada.service;

import com.dssoftware.sada.dto.RenovacionRequest;
import com.dssoftware.sada.dto.RenovacionResponse;
import org.springframework.stereotype.Service;

@Service
public class RenovacionService {

    private final CalculadoraUVBService calculadora;

    public RenovacionService(CalculadoraUVBService calculadora) {
        this.calculadora = calculadora;
    }

    public RenovacionResponse calcular(RenovacionRequest request) {

        long activos = request.activos();
        int establecimientosMisma = request.establecimientosMismaJurisdiccion();
        int establecimientosDiferente = request.establecimientosOtraJurisdiccion();
        String tipoPersona = request.tipoPersona();

        if (activos <= 0) {
            throw new IllegalArgumentException("Los activos deben ser mayores a cero");
        }

        long renovacionPropietario =
                calculadora.calcularRegistroYRenovacionMatriculaMercantil(activos);

        long renovacionMisma =
                calculadora.calcularRenovacionEstablecimientoMismaJurisdiccion(activos)
                        * establecimientosMisma;

        long renovacionDiferente =
                calculadora.calcularRenovacionEstablecimientoDiferenteJurisdiccion(activos)
                        * establecimientosDiferente;

        long formularioRues =
                calculadora.calcularValorFormularioRues();

        long certificado =
                calculadora.calcularValorCertificado(tipoPersona);

        long totalRenovacion =
                renovacionPropietario +
                        renovacionMisma +
                        renovacionDiferente;

        long totalPagar =
                totalRenovacion +
                        formularioRues +
                        certificado;

        long porcentajeSobreActivos = totalRenovacion * 100 / activos;

        return new RenovacionResponse(
                activos,
                tipoPersona,
                renovacionPropietario,
                establecimientosMisma,
                establecimientosDiferente,
                renovacionMisma,
                renovacionDiferente,
                formularioRues,
                certificado,
                totalRenovacion,
                totalPagar,
                porcentajeSobreActivos
        );
    }
}
