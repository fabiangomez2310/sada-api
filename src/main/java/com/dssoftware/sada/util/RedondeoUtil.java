package com.dssoftware.sada.util;

import com.dssoftware.sada.domain.ConstantesUVB;


/**
 * Utilidad de redondeo según Decreto 1074 de 2015
 * Art. 2.2.2.46.1.10
 */
public class RedondeoUtil {

    private RedondeoUtil() {}


    /**
     * Aplica el redondeo normativo:
     * - Hasta 3% del SMMLV: redondeo a centenas
     * - Superior al 3% del SMMLV: redondeo a miles
     */
    public static long aplicar(double valor) {
        double tope = (ConstantesUVB.SMMLV * 3)/100;

        if (valor<=tope){
            return Math.round(valor/100) * 100;
        } else {
            return Math.round(valor / 1_000) * 1_000;
        }

    }
}
