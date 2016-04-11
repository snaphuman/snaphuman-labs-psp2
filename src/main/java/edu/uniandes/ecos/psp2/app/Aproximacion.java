package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

/**
 * Created by snaphuman on 4/10/16.
 */
public class Aproximacion {

    public static Pair<Integer, Double> buscarX (Double trialX, Double esperado) {
        Double e = 0.00001;
        Integer dof = 9;
        Integer num_seg = 10;
        Double resultado;

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

        Pair<Integer, Double> busqueda = Pair.with(count, resultado);

        return busqueda;
    }
}
