package com.dssoftware.sada.service;

import org.springframework.stereotype.Service;

import com.dssoftware.sada.domain.ConstantesUVB;
import com.dssoftware.sada.util.RedondeoUtil;

@Service
public class CalculadoraUVBService {

    /* ===============================
       MATRÍCULA MERCANTIL (PJ)
       =============================== */

    public long calcularRegistroYRenovacionMatriculaMercantil(double valorActivos) {

        double tarifaAplicada;

        // Hasta 6.500 UVB
        if (valorActivos <= 6_500 * ConstantesUVB.UVB) {
            tarifaAplicada =
                    2 * ConstantesUVB.UVB
                            + 0.7 * ConstantesUVB.UVB * (valorActivos / 1_000_000);
        }
        // Más de 6.500 hasta 25.000 UVB
        else if (valorActivos <= 25_000 * ConstantesUVB.UVB) {
            tarifaAplicada =
                    47.5 * ConstantesUVB.UVB
                            + (0.35 * ConstantesUVB.UVB * (valorActivos - 6_500 * ConstantesUVB.UVB)) / 1_000_000;
        }
        // Más de 25.000 hasta 65.000 UVB
        else if (valorActivos <= 65_000 * ConstantesUVB.UVB) {
            tarifaAplicada =
                    112.25 * ConstantesUVB.UVB
                            + (0.1 * ConstantesUVB.UVB * (valorActivos - 25_000 * ConstantesUVB.UVB)) / 1_000_000;
        }
        // Más de 65.000 hasta 650.000 UVB
        else if (valorActivos <= 650_000 * ConstantesUVB.UVB) {
            tarifaAplicada =
                    152.25 * ConstantesUVB.UVB
                            + (0.045 * ConstantesUVB.UVB * (valorActivos - 65_000 * ConstantesUVB.UVB)) / 1_000_000;
        }
        // Más de 650.000 hasta 2.000.000 UVB
        else if (valorActivos <= 2_000_000 * ConstantesUVB.UVB) {
            tarifaAplicada =
                    415.5 * ConstantesUVB.UVB
                            + (0.025 * ConstantesUVB.UVB * (valorActivos - 650_000 * ConstantesUVB.UVB)) / 1_000_000;
        }
        // Más de 2.000.000 UVB
        else {
            tarifaAplicada =
                    753 * ConstantesUVB.UVB
                            + (0.0125 * ConstantesUVB.UVB * (valorActivos - 2_000_000 * ConstantesUVB.UVB)) / 1_000_000;

            tarifaAplicada = limitarTarifaMaxima(tarifaAplicada);
        }

        return RedondeoUtil.aplicar(tarifaAplicada);
    }

    /* ===============================
       ESTABLECIMIENTOS MISMA JURISDICCIÓN
       =============================== */

    public long calcularRenovacionEstablecimientoMismaJurisdiccion(double valorActivos) {

        double tarifa;

        if (valorActivos <= 6_500 * ConstantesUVB.UVB) tarifa = 4 * ConstantesUVB.UVB;
        else if (valorActivos <= 25_000 * ConstantesUVB.UVB) tarifa = 10 * ConstantesUVB.UVB;
        else if (valorActivos <= 65_000 * ConstantesUVB.UVB) tarifa = 16 * ConstantesUVB.UVB;
        else if (valorActivos <= 650_000 * ConstantesUVB.UVB) tarifa = 22 * ConstantesUVB.UVB;
        else if (valorActivos <= 2_000_000 * ConstantesUVB.UVB) tarifa = 28 * ConstantesUVB.UVB;
        else if (valorActivos <= 10_000_000 * ConstantesUVB.UVB) tarifa = 34 * ConstantesUVB.UVB;
        else tarifa = 40 * ConstantesUVB.UVB;

        return RedondeoUtil.aplicar(tarifa);
    }

    /* ===============================
       ESTABLECIMIENTOS DIFERENTE JURISDICCIÓN
       =============================== */

    public long calcularRenovacionEstablecimientoDiferenteJurisdiccion(double valorActivos) {

        double tarifa;

        if (valorActivos <= 6_500 * ConstantesUVB.UVB) tarifa = 8 * ConstantesUVB.UVB;
        else if (valorActivos <= 25_000 * ConstantesUVB.UVB) tarifa = 20 * ConstantesUVB.UVB;
        else if (valorActivos <= 65_000 * ConstantesUVB.UVB) tarifa = 32 * ConstantesUVB.UVB;
        else if (valorActivos <= 650_000 * ConstantesUVB.UVB) tarifa = 44 * ConstantesUVB.UVB;
        else if (valorActivos <= 2_000_000 * ConstantesUVB.UVB) tarifa = 56 * ConstantesUVB.UVB;
        else if (valorActivos <= 10_000_000 * ConstantesUVB.UVB) tarifa = 68 * ConstantesUVB.UVB;
        else tarifa = 80 * ConstantesUVB.UVB;

        return RedondeoUtil.aplicar(tarifa);
    }

    /* ===============================
       OTROS CONCEPTOS
       =============================== */

    public long calcularValorFormularioRues() {
        return RedondeoUtil.aplicar(ConstantesUVB.UVB * 0.7);
    }

    public long calcularValorCertificado(String tipoCertificado) {

        double valor;

        if ("PN".equals(tipoCertificado)
                || "EST".equals(tipoCertificado)
                || "AGE".equals(tipoCertificado)
                || "SUC".equals(tipoCertificado)) {
            valor = 0.5 * ConstantesUVB.UVB;
        }
        else if ("PJ".equals(tipoCertificado) || "ESP".equals(tipoCertificado)) {
            valor = 1 * ConstantesUVB.UVB;
        }
        else {
            throw new IllegalArgumentException("Tipo de certificado inválido");
        }

        return RedondeoUtil.aplicar(valor);
    }

    public long calcularValorInscripcionDocumento() {
        return RedondeoUtil.aplicar(ConstantesUVB.UVB * 6);
    }

    /* ===============================
       IMPUESTOS
       =============================== */

    public long calcularImpuestoRegistroCuantia(double valorCapSuscrito) {
        return Math.round(
                (valorCapSuscrito * 0.7) / 100
                        + ConstantesUVB.SISTEMATIZACION
        );
    }

    public long calcularImpuestoRegistroSinCuantia() {
        return Math.round(ConstantesUVB.IMPUESTO_REGISTRO_SIN_CUANTIA);
    }


    /**
     * Retención DIAN – 1% sobre el valor del acto
     */
    public long calcularRetencionDian(double valor){
       return Math.round(valor * 0.01);
    }

    /* ===============================
       MÉTODOS PRIVADOS
       =============================== */

    private double limitarTarifaMaxima(double valor) {
        double limite = 1_000 * ConstantesUVB.UVB;
        return valor > limite ? limite : valor;
    }
}
