package edu.uniandes.ecos.psp2.app;

import org.junit.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.junit.Assert.assertEquals;

/**
 * Created by snaphuman on 4/10/16.
 */
public class AproximacionTest {

    @Test
    public void buscarX () {
        Double e = 0.00001;
        Double errorActual = 0.0;
        Integer dof = 4;
        Integer num_seg = 10;
        Double resultado = 0.0;
        Double trialX = 1.0;
        Double esperado = 0.495;
        int maxIter = 100;
        NumberFormat df = DecimalFormat.getInstance();
        df.setMaximumFractionDigits(5);
        df.setMinimumFractionDigits(5);
        df.setRoundingMode(RoundingMode.UP);
        boolean nuevoNegativo = false;
        boolean viejoNegativo = false;

        boolean esValido = false;
        double d = 0.5;

        for (int count = 0; count <= maxIter; count ++) {
            resultado = new PROBE().calcularSimpson(trialX,num_seg,e,dof);
            System.out.println(resultado);
            Double res = Double.valueOf(df.format(resultado));

            errorActual = esperado - resultado;
            nuevoNegativo = errorActual < 0;

            if(Math.abs(errorActual) > e) {

                if (nuevoNegativo != viejoNegativo) {
                    d /= 2;
                    d = -d;
                }
            } else {
                break;
            }
            viejoNegativo = nuevoNegativo;

            trialX += d;
        }

        System.out.println("trialX: " + df.format(trialX));
        System.out.println("Esperado: " + df.format(esperado));
        System.out.println("Resultado: " + df.format(resultado));
        System.out.println("X: " + trialX);
        assertEquals(esperado, resultado, e);
    }
}
