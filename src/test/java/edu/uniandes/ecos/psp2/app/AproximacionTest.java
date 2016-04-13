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
        Integer dof = 4;
        Integer num_seg = 10;
        Double resultado;
        Double trialX = 1.0;
        Double esperado = 0.495;
        NumberFormat df = DecimalFormat.getInstance();
        df.setMaximumFractionDigits(5);
        df.setMinimumFractionDigits(5);
        df.setRoundingMode(RoundingMode.UP);

        boolean esValido = false;
        double d = 0.5;
        int count = 0;
        do {

            resultado = new PROBE().calcularSimpson(trialX,num_seg,e,dof);
            Double res = Double.valueOf(df.format(resultado));
            System.out.println(res);

            if ((res - esperado) == 0) {

                esValido = true;
            }
            if (res > esperado ) {

                trialX -= d;
                esValido = (Math.abs(res - esperado) <= e) ? true : false;
                if (count >= 1 && esValido == false) {

                    d = d/2;
                }
            } else if(res < esperado) {

                trialX += d;
                esValido = (Math.abs(res - esperado) <= e) ? true : false;
                if (count >= 1 && esValido == false) {

                    d = d/2;
                }
            }

            count++;
        } while (!esValido);

        System.out.println("trialX: " + df.format(trialX));
        System.out.println("Esperado: " + df.format(esperado));
        System.out.println("Resultado: " + df.format(resultado));
        System.out.println("X: " + trialX);
        assertEquals(esperado, resultado, e);
    }
}
