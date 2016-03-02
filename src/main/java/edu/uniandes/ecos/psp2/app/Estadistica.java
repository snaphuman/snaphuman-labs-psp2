package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

/**
 * Created by snaphuman on 2/29/16.
 */
public class Estadistica implements Regresion, Correlacion, Media{

    public static Pares coeficientesCorrelacion = new Pares();
    public static Pares parametrosRegresion = new Pares();

    public void calcularCoeficientes(Pares lista) {

        Double sumaXY = new Double(0.0);
        Double sumaX = new Double(0.0);
        Double sumaY = new Double(0.0);
        Integer n = new Integer(lista.listaPares.size());

        System.out.println("Calculando Coeficientes");

        for ( Pair item : lista.listaPares ) {

            Double x = new Double( ( Double ) item.getValue( 0 ));
            Double y = new Double( ( Double ) item.getValue( 1 ));

            sumaXY = sumaXY + ( x * y );
            sumaX = sumaX + x;
            sumaY = sumaY + y;
        }

        Double correlacionR1 = ( n * sumaXY ) - ( sumaX * sumaY ) /
                        Math.sqrt( n * Math.pow( sumaX, 2 ) - Math.pow( sumaX, 2 ) *
                        n * Math.pow( sumaY, 2 ) - Math.pow( sumaY, 2 ) );

        Double correlacionR2 = Math.pow( correlacionR1, 2 );

        coeficientesCorrelacion.listaPares.add( Pair.with( correlacionR1, correlacionR2 ) );
    }

    public void calcularParametros(Pares lista) {

        Double sumaXY = new Double( 0.0 );
        Double sumaX = new Double( 0.0 );
        Integer n = new Integer( lista.listaPares.size() );

        System.out.println("Calculando Parametros");

        for ( Pair item : lista.listaPares ) {

            Double x = new Double( ( Double ) item.getValue( 0 ));
            Double y = new Double( ( Double ) item.getValue( 1 ));

            sumaXY = sumaXY + (x * y);
            sumaX = sumaX + x;
        }

        Double promedioX = ( Double ) this.calcularMedia( lista ).getValue( 0 );
        Double promedioY = ( Double ) this.calcularMedia( lista ).getValue( 1 );

        Double parametroB1 = sumaXY - ( n * promedioX * promedioY ) /
                            Math.pow( sumaX, 2 ) - ( Math.pow( n*promedioX, 2 ) );

        Double parametroB0 = promedioY - ( parametroB1 * promedioX );

        parametrosRegresion.listaPares.add( Pair.with(parametroB0, parametroB1 ) );

    }

    /**
     * Calcula la desviación estandard de una lista de valores dados.
     *
     * @param lista Array de números eneros
     * @return sigma Double con el resultado del cálculo
     */
    //public double calcularDesviacion(double[] lista) {

    //    System.out.println("Calculando Desviacion");

    //    double avg = this.calcularMedia(lista);
    //    double sum = 0;
    //    double sigma;

    //    for (int i = 0; i < lista.length; i++) {

    //        sum = sum + Math.pow((lista[i] - avg), 2);
    //    }

    //    sigma = Math.sqrt(sum / (lista.length - 1));

    //    return sigma;
    //}

    /**
     * Calcula la media aritmética de una lista de valores dados.
     *
     * @param lista Array de números eneros
     * @return media Double con el resultado del cálculo
     */
    public Pair<Double, Double> calcularMedia(Pares lista) {

        System.out.println("Calculando Media");

        double sumX = 0;
        double sumY = 0;

        for( Pair item : lista.listaPares ) {
            sumX = sumX + (Double) item.getValue(0);
            sumY = sumY + (Double) item.getValue(0);
        }

        Double mediaX = sumX / lista.listaPares.size();
        Double mediaY = sumY / lista.listaPares.size();

        return Pair.with(mediaX, mediaY);
    }

}
