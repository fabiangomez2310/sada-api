package com.dssoftware.sada.service;

import com.dssoftware.sada.dto.CompraventaRequest;
import com.dssoftware.sada.dto.CompraventaResponse;
import org.springframework.stereotype.Service;

@Service
public class CompraventaService {

    private final CalculadoraUVBService calculadora;

    public CompraventaService(CalculadoraUVBService calculadora){
        this.calculadora = calculadora;
    }

    public CompraventaResponse calcular (CompraventaRequest request){

            long valorVentaEstablecimiento = request.valorVentaEstablecimiento();

            boolean matricular = request.matricularComprador();

        validarValorCompra(request.valorVentaEstablecimiento());

            long impuestoRegistro =
                    calculadora.calcularImpuestoRegistroCuantia(valorVentaEstablecimiento);

            long retencionDian =
                    calculadora.calcularRetencionDian(valorVentaEstablecimiento);

            long ingresoDocumento =
                    calculadora.calcularValorInscripcionDocumento();

            long matriculaComprador = matricular ?
                    calculadora.calcularRegistroYRenovacionMatriculaMercantil(valorVentaEstablecimiento)
                    + calculadora.calcularValorFormularioRues()
                    + calculadora.calcularValorCertificado("PN")
                    : 0 ;

            long totalCamara = ingresoDocumento + matriculaComprador;

            long totalCompraventa =
                    impuestoRegistro + retencionDian + totalCamara;

            return new CompraventaResponse(
                    valorVentaEstablecimiento,
                    matricular,
                    impuestoRegistro,
                    retencionDian,
                    ingresoDocumento,
                    matriculaComprador,
                    totalCamara,
                    totalCompraventa
            );

    }


    private void validarValorCompra(double valorVenta){
        if(valorVenta<=0) {
            throw new IllegalArgumentException(
                    "El valor de la venta debe ser mayor a cero"
            );
        }
    }
}
