package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by snaphuman on 2/29/16.
 */
public class Estadistica implements Operacion, Media {

    /**
     * Created by snaphuman on 2/29/16.
     *
     * @type Item
     */
    public void calcular( Pares lista ) {

        List<Triplet<Double, Double, Double>> tabla = new ArrayList<>();
        Integer n = new Integer( lista.listaPares.size() );
        Double x;
        Double y;
        Double xy;
        Double x2;
        Double y2;
        Double xSum = 0.0;
        Double ySum = 0.0;
        Double xySum = 0.0;
        Double x2Sum = 0.0;
        Double y2Sum = 0.0;
        Double xAvg;
        Double yAvg;

        for ( Pair<Double, Double> item : lista.listaPares ) {

            x = item.getValue0();
            y = item.getValue1();

            x2 = Math.pow( x, 2 );
            xy = x * y;
            y2 = Math.pow( y, 2 );

            tabla.add( Triplet.with( x2, xy, y2 ) );

            xSum = xSum + x;
            ySum = ySum + y;
            xySum = xySum + xy;
            x2Sum = x2Sum + x2;
            y2Sum = y2Sum + y2;
        }

        xAvg = this.calcularMediaPares( lista ).getValue0();
        yAvg = this.calcularMediaPares( lista ).getValue1();

        Double parametroB1 = ( xySum - ( n * xAvg * yAvg ) ) /
                ( x2Sum - ( n * Math.pow( xAvg, 2 ) ) );

        Double parametroB0 = yAvg - ( parametroB1 * xAvg );

        Double correlacionR1 = ( ( n * xySum  ) - ( xSum * ySum ) ) /
                ( Math.sqrt( ( n * x2Sum - Math.pow( xSum,2 ) ) *  ( n* y2Sum - Math.pow( ySum,2 ) ) ) );

        Double correlacionR2 = Math.pow( correlacionR1, 2 );

        Pares.resultadosCoeficientes.add( Pair.with( correlacionR2, correlacionR1 ) );
        Pares.resultadosParametros.add( Pair.with( parametroB0, parametroB1 ) );
    }

    /**
     * Calcula la media aritmética de una lista de valores dados.
     *
     * @type Item
     * @param lista Pares de números double
     * @return Pair con el resultado del cálculo para cada elemento
     */
    public Pair<Double, Double> calcularMediaPares( Pares lista ) {

        double sumX = 0;
        double sumY = 0;

        for( Pair<Double, Double> item : lista.listaPares ) {

            sumX = sumX + item.getValue0();
            sumY = sumY + item.getValue1();
        }

        Double mediaX = sumX / lista.listaPares.size();
        Double mediaY = sumY / lista.listaPares.size();

        return Pair.with( mediaX, mediaY );
    }

    public Double calcularMediaLista( List<Double> lista ) {

        double sumX = 0;

        for( Double item : lista ) {

            sumX = sumX + item;
        }

        Double mediaX = sumX / lista.size();

        return mediaX;
    }

    public Double calcularVarianza ( List<Double> lista, Double media ) {

        double var = 0;

        for (Double item : lista) {

            var = var + ( Math.pow( ( item - media ),2 ) / ( lista.size() - 1 ) );
        }

        return var;
    }

    public Double calcularDesviacionEstandard ( Double varianza ) {

        double sigma = Math.sqrt( varianza );

        return sigma;
    }
}
