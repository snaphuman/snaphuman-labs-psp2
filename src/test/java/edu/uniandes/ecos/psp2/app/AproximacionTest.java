package edu.uniandes.ecos.psp2.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by snaphuman on 4/10/16.
 */
public class AproximacionTest {

    @Test
    public void buscarX () {
        Double e = 0.00001;
        Integer dof = 8;
        Integer num_seg = 10;
        Double resultado;
        Double trialX = 1.0;
        Double esperado = 0.4207;

        boolean esValido = false;
        double d = 0.5;
        int count = 0;
        do {

            resultado = new PROBE().calcularSimpson(trialX,num_seg,e,dof);
            if (resultado > esperado ) {

                trialX -= d;
                esValido = (Math.abs(esperado - resultado) < e) ? true : false;
                if (count >= 1 && esValido == false) {

                    d = d/2;
                }
            } else if(resultado < esperado) {

                trialX += d;
                esValido = (Math.abs(esperado - resultado) < e) ? true : false;
                if (count >= 1 && esValido == false) {

                    d = d/2;
                }
            }

            count++;
        } while (!esValido);

        System.out.println("Esperado: " + esperado);
        System.out.println("Resultado: " + resultado);
        assertEquals(esperado, resultado, e);
    }
}
