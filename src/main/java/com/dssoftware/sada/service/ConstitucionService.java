package com.dssoftware.sada.service;

import com.dssoftware.sada.dto.ConstitucionRequest;
import com.dssoftware.sada.dto.ConstitucionResponse;
import org.springframework.stereotype.Service;

@Service
public class ConstitucionService {

    private final CalculadoraUVBService calculadora;

    public ConstitucionService(CalculadoraUVBService calculadora) {
        this.calculadora = calculadora;
    }

    public ConstitucionResponse calcular(ConstitucionRequest request) {

        long capitalSuscrito = request.capitalSuscrito();
        long capitalPagado = request.capitalPagado();
        int accionistas = request.numeroAccionistas();

        validarDatos(capitalSuscrito, capitalPagado, accionistas);

        // ===============================
        // CÁMARA DE COMERCIO
        // ===============================

        long inscripcionDocumento =
                calculadora.calcularValorInscripcionDocumento();

        long matriculaPJ =
                calculadora.calcularRegistroYRenovacionMatriculaMercantil(capitalPagado);

        long formularioRues =
                calculadora.calcularValorFormularioRues();

        long matriculaEst =
                request.matriculaEstablecimiento()
                        ? calculadora.calcularRenovacionEstablecimientoMismaJurisdiccion(capitalPagado)
                        : 0;

        long aporteEst =
                request.aporteEstablecimiento()
                        ? calculadora.calcularValorInscripcionDocumento()
                        : 0;

        long situacionControl =
                accionistas == 1
                        ? calculadora.calcularValorInscripcionDocumento()
                        : 0;

        long totalCamara =
                inscripcionDocumento +
                        matriculaPJ +
                        formularioRues +
                        matriculaEst +
                        aporteEst +
                        situacionControl;

        // ===============================
        // GOBERNACIÓN
        // ===============================

        long impuestoCuantia =
                calculadora.calcularImpuestoRegistroCuantia(capitalSuscrito);

        long impuestoSinCuantia =
                accionistas == 1
                        ? calculadora.calcularImpuestoRegistroSinCuantia()
                        : 0;

        long totalGobernacion =
                impuestoCuantia + impuestoSinCuantia;

        // ===============================
        // TOTAL GENERAL
        // ===============================

        long totalConstitucion =
                totalCamara + totalGobernacion;

        return new ConstitucionResponse(
                capitalSuscrito,
                capitalPagado,
                accionistas,
                inscripcionDocumento,
                matriculaPJ,
                formularioRues,
                matriculaEst,
                aporteEst,
                situacionControl,
                totalCamara,
                impuestoCuantia,
                impuestoSinCuantia,
                totalGobernacion,
                totalConstitucion
        );
    }

    private void validarDatos(double capitalSuscrito, double capitalPagado, int accionistas) {

        if (capitalSuscrito <= 0)
            throw new IllegalArgumentException("El capital suscrito debe ser mayor a cero");

        if (capitalPagado <= 0)
            throw new IllegalArgumentException("El capital pagado debe ser mayor a cero");

        if (capitalPagado > capitalSuscrito)
            throw new IllegalArgumentException("El capital pagado no puede ser mayor al capital suscrito");

        if (accionistas < 1)
            throw new IllegalArgumentException("Debe existir al menos un accionista");
    }
}
