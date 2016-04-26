package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import java.util.ArrayList;
import java.util.List;

/**
 * Estadística: Clase que suministra los diferentes métodos
 * que calculan varias operaciones estadísticas
 *
 * @author snaphuman
 */
public class Estadistica implements Operacion, Media {

    /**
     * Calcula los coeficientes y parámetros para los métodos
     * de estimación de tamaño y tiempo a partir de una lista
     * de pares de datos ordenados de tipo Double, los cuales
     * son almacenados en en una lista de tipo Pares.
     *
     * @type Item
     * @param lista Un objeto de tipo Pares  que contiene los
     *              pares de datos ordenados
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
     * Calcula la media aritmética de una lista pares de datos
     * ordenados de tipo Double para producir un par de datos
     * MediaX y MediaY de tipo Pair
     *
     * @type Item
     * @param lista Pares de números double
     * @return Objeto de tipo Pair con el resultado del cálculo
     * para cada elemento
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

    /**
     * Calcula la Media para una lista de números de tipo Double
     *
     * @type Item
     * @param lista Contiene valores de tipo double
     * @return mediaX valor calculado de tipo Double
     */
    public Double calcularMediaLista( List<Double> lista ) {

        double sumX = 0;

        for( Double item : lista ) {

            sumX = sumX + item;
        }

        Double mediaX = sumX / lista.size();

        return mediaX;
    }

    /**
     * Calcula la varianza a partir de una lista de valores de tipo
     * Double que previamente fueon calculados con el logaritmo natural y
     * el promedio de los n valores logarítmicos.
     *
     * @type Item
     * @param lista Contiene valores logaritmicos de tipo Double
     * @param media Promedio de los valores logaritmicos de tipo Double
     * @return var Varianza de tipo double
     */
    public Double calcularVarianza ( List<Double> lista, Double media ) {

        double var = 0;

        for (Double item : lista) {

            var = var + ( Math.pow( ( item - media ), 2 ) / ( lista.size() - 1 ) );
        }

        return var;
    }

    /**
     * Calcula la Desviación Estándard a partir del valor de la varianza
     * de tipo double
     *
     * @type Item
     * @param varianza valor tipo double previamente calculado
     * @return sigma valor con la Desviación Estándard de tipo double
     */
    public Double calcularDesviacionEstandard ( Double varianza ) {

        double sigma = Math.sqrt( varianza );

        return sigma;
    }


    /**
     * Calcula la función gama determinando si el valor entregado
     * es entero o no.
     *
     * @type Item
     * @param valor Double valor para calcular la funcion gamma
     * @return gamma Double con el resultado de la funcion gamma
     */
    public Double calcularGamma (Double valor) {
        System.out.println("calculando Gamma");

        Double gamma = 1.0;

        if (valor%1 != 0) {

            do {

                valor -= 1;
                gamma = gamma * valor;
            } while (valor != 0.5);

            gamma = gamma * Math.sqrt(Math.PI);
        } else {

            do {

                valor -= 1;
                gamma = gamma * valor;
            } while (valor != 1);
        }

        return gamma;
    }

    /**
     * Calcula la Distribución T
     *
     * @type Item
     * @param Wi Double valor de W
     * @param dof Integer Grado de Libertad
     * @param gDof Double Grado de libertad operado con gamma
     * @return rDT Double Resultado Distribucion T
     */
    public Double calcularDistribucionT (Double Wi, Integer dof, Double gDof) {

        Double rDT = gDof * (Math.pow( 1 + (Math.pow( Wi.doubleValue(), 2 ) / dof), -((dof + 1)/2)));

        return rDT;
    }

    /**
     * Obtiene el multiplicador
     *
     * @type Item
     * @param indice int indice de la iteración
     * @param num_seg int num_seg numero de segmentos
     * @return m Integer multiplicador de la integral
     */
    public Integer obtenerMultiplicador (int indice, int num_seg) {

        Integer m = 0;

        // verificar si el indice es inicio/fin,
        // o es par/impar
        if (indice == 0) {
            m = 1;
        } else if (indice == num_seg) {

            m = 1;
        } else if (indice % 2 == 0) {

            m = 2;
        } else if (indice % 2 != 0) {

            m = 4;
        }

        return m;
    }
}
