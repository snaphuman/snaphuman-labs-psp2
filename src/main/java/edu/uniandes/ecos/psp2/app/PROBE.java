package edu.uniandes.ecos.psp2.app;

import org.apache.commons.math3.special.Gamma;
import org.javatuples.Pair;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * PROBE: Clase que permite realizar varios cálculos
 * de estimación
 *
 * @type Part
 * @author snaphuman
 */
public class PROBE {

    private static DecimalFormat df = new DecimalFormat("#.####");

    /**
     * Calcula el tamaño relativo de una lista de valores dados de
     * tipo Lista que tiene dos tipos Integer y retorna un par ordenado
     * cuyos tipos son String y Double. Este método permitirá constuir
     * la tabla con los rangos relativos Very Small (VS), Small (S),
     * Medium (M), Large (L), Very Large (VL).
     *
     * @type Item
     * @param lista Contiene pares ordenados de tipo Integer
     * @return tamanoRelativo
     */
    public List<Pair<String, Double>> calcularTamanoRelativo( Lista lista ) {

        List<Double> tamanoItem = new ArrayList<>();
        List<Double> lnTamanoItem = new ArrayList<>();

        for ( Pair<Integer, Integer> item : lista.datos ) {

            Double x = Double.valueOf(
                    df.format( item.getValue0().doubleValue() /
                            item.getValue1().doubleValue() ) );

            tamanoItem.add( x );
            lnTamanoItem.add( Double.valueOf( df.format( Math.log( x ) ) ) );
        }

        Double media = new Estadistica().calcularMediaLista( lnTamanoItem );

        Double varianza = new Estadistica().calcularVarianza( lnTamanoItem, media );

        Double desviacionEst = new Estadistica().calcularDesviacionEstandard( varianza );

        List<Pair<String, Double>> rangos = this.rangosLogaritmicos( media, desviacionEst );
        List<Pair<String, Double>> tamanoRelativo = this.puntosMediosRangos( rangos );

        return tamanoRelativo;
    }

    /**
     * Provee la lista con los rangos logarítmicos para
     * los valores relativos Very Small (VS), Small (S),
     * Medium (M), Large (L), Very Large (VL).
     *
     * @type Item
     * @param media Promedio de los valores entregados
     * @param sigma Desviación estándard calculada
     * @return resultado
     */
    private List<Pair<String, Double>> rangosLogaritmicos ( Double media, Double sigma ) {

        List<Pair<String, Double>> resultado = new ArrayList<>();

        resultado.add(Pair.with( "VS", media - ( 2 * sigma ) ) );
        resultado.add(Pair.with( "S", media - sigma ) );
        resultado.add(Pair.with( "M", media ) );
        resultado.add(Pair.with( "L", media + sigma ) );
        resultado.add(Pair.with("VL", media + ( 2 * sigma ) ) );

        return resultado;
    }

    /**
     * Provee la lista con los rangos de valores de
     * tamaño relativos Very Small (VS), Small (S),
     * Medium (M), Large (L), Very Large (VL).
     *
     * @type Item
     * @param rangosLogaritmicos de los valores entregados
     * @return resultado
     */
    private List<Pair<String, Double>> puntosMediosRangos ( List<Pair<String, Double>> rangosLogaritmicos ) {

        List<Pair<String, Double>> resultado = new ArrayList<>();

        int i = 0;
        for ( Pair<String, Double> item : rangosLogaritmicos ) {

            resultado.add( item.setAt1( Math.exp(rangosLogaritmicos.get( i ).getValue1() ) ) );
        i++;
        }

        return resultado;
    }

    /**
     * Realiza lals operaciones para calcular la regla de simpson
     *
     * @type Item
     * @param x Double x
     * @param num_seg Integer numero de segmentos
     * @param e Double error aceptable
     * @param dof Integer Grado de libertad
     * @return resultado
     */
    public Double calcularSimpson (Double x, Integer num_seg, Double e, Integer dof) {
        System.out.println("Calculando Simpson");

        Operacion distribucionT = new Estadistica();
        Operacion multiplicador = new Estadistica();
        Operacion gamaDof = new Estadistica();
        Double W;
        Double Fx;
        Double gDof;
        Double simpsonItem;
        List<Double> resultados = new ArrayList<>();
        Boolean esValido = false;
        NumberFormat df = DecimalFormat.getInstance();
        df.setMaximumFractionDigits(5);
        df.setMinimumFractionDigits(5);
        df.setRoundingMode(RoundingMode.FLOOR);
        int m;

        // TODO: verificar que num_seg sea un número par

        // Calcular primera parte de la ecuación gDof
        // should be method
        //gDof = (gamaDof.calcularGamma(((double) dof + 1)/2)) /
        //        (Math.pow((dof * Math.PI), 0.5) *
        //        gamaDof.calcularGamma((double) dof /2));
        double numerador = Gamma.gamma((dof+1.0)/2.0);
        double denominador = Math.pow((dof*Math.PI), 0.5)*Gamma.gamma(dof/2.0);

        gDof = numerador/denominador;

        // Iteración hasta que validarError = true,
        // de lo contrario num_seg = num_seg*2
        System.out.println(gDof);
        do {
            Double simpsonTotal = 0.0;
            W = x / num_seg;

            int i;
            for (i = 0; i <= num_seg; i++) {

                Fx = distribucionT.calcularDistribucionT(W * i, dof, gDof);
                m = multiplicador.obtenerMultiplicador(i, num_seg);
                simpsonItem = (W / 3) * m * Fx;
                simpsonTotal += simpsonItem;
            }

            if (resultados.size() == 0) {

                resultados.add(simpsonTotal);
                num_seg *= 2;
            } else if (resultados.size() == 1 ) {

                resultados.add(simpsonTotal);
            } else if (resultados.size() >= 2) {

                esValido = validarError(resultados, e);
            }

        } while (esValido != true);

        return Double.valueOf(df.format(resultados.get(resultados.size() -1 ).doubleValue()));
    }

    /**
     * Realiza la comparación entre los resultados de la regla de
     * simpson
     *
     * @type Item
     * @param resultados
     * @param e
     * @return
     */
    private Boolean validarError (List<Double> resultados, Double e) {

        NumberFormat df = DecimalFormat.getInstance();
        df.setMaximumFractionDigits(5);
        df.setMinimumFractionDigits(5);
        df.setRoundingMode(RoundingMode.UP);
        System.out.println("Validando");
        int ultimo = resultados.size() - 1;
        int penultimo = resultados.size() -2;

        Double valor1 = Double.valueOf(df.format(resultados.get(penultimo).doubleValue()));
        Double valor2 = Double.valueOf(df.format(resultados.get(ultimo).doubleValue()));

        System.out.println(valor1);
        System.out.println(valor2);
        Boolean esValido = valor1 - valor2 <= e ? true : false;

        System.out.println(esValido);
        return esValido;
    }
}
