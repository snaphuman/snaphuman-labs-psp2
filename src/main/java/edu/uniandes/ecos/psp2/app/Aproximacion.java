package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by snaphuman on 4/10/16.
 */
public class Aproximacion {

    /**
     * Calcula la integración para encontrar el valor de X
     * dentro del error aceptable indicado
     *
     * @type Item
     * @param trialX
     * @param esperado
     * @param dof
     * @return
     */
    public static Pair<Integer, Double> buscarX ( Double trialX, Double esperado, Integer dof ) {
        Double e = 0.00001;
        Integer num_seg = 10;
        Double resultado;
        NumberFormat df = DecimalFormat.getInstance();
        df.setMaximumFractionDigits( 5 );
        df.setMinimumFractionDigits( 5 );
        df.setRoundingMode( RoundingMode.UP );

        boolean esValido = false;
        double d = 0.5;
        int count = 0;
        do {
            resultado = new PROBE().calcularSimpson( trialX, num_seg ,e, dof );
            Double res = Double.valueOf( df.format( resultado ) );

            if ( ( res - esperado ) == 0 ) {

                esValido = true;
            }
            if ( res > esperado ) {

                trialX -= d;
                esValido = ( Math.abs( res - esperado ) <= e ) ? true : false;
                if ( count >= 1 && esValido == false ) {

                    d = d/2;
                }
            } else if( res < esperado ) {

                trialX += d;
                esValido = ( Math.abs( res - esperado ) <= e ) ? true : false;
                if ( count >= 1 && esValido == false ) {

                    d = d/2;
                }
            }

            count++;
        } while ( !esValido );

        Pair<Integer, Double> busqueda = Pair.with( count, Double.valueOf( df.format( trialX ) ) );

        return busqueda;
    }
}