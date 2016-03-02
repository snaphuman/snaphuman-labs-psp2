package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

/**
 * Created by snaphuman on 2/29/16.
 */
public class Operacion implements Regresion, Correlacion, Desviacion, Media{

    public Pair<Double, Double> calcularCoeficientes(Double x, Double y) {

        Pair<Double, Double> resultado = Pair.with(0.0,0.0);

        System.out.println("Calculando Coeficientes");

        return resultado;
    }

    public Pair<Double,Double> calcularParametros(Double x, Double y) {

        System.out.println("Calculando Parametros");

        return null;
    }

    /**
     * Calcula la desviación estandard de una lista de valores dados.
     *
     * @param lista Array de números eneros
     * @return sigma Double con el resultado del cálculo
     */
    public double calcularDesviacion(double[] lista) {

        System.out.println("Calculando Desviacion");

        double avg = this.calcularMedia(lista);
        double sum = 0;
        double sigma;

        for (int i = 0; i < lista.length; i++) {

            sum = sum + Math.pow((lista[i] - avg), 2);
        }

        sigma = Math.sqrt(sum / (lista.length - 1));

        return sigma;
    }

    /**
     * Calcula la media aritmética de una lista de valores dados.
     *
     * @param lista Array de números eneros
     * @return media Double con el resultado del cálculo
     */
    public double calcularMedia(double[] lista) {

        System.out.println("Calculando Media");

        double sum = 0;
        double media = 0;

        for(int i = 0; i<lista.length; i++) {
            sum = sum + lista[i];
        }

        media = sum / lista.length;

        return media;
    }

}
