package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

import java.text.DecimalFormat;
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

    public Pair<Double, Double> calcularSimpson (Double x, Integer num_seg, Double e, Integer dof) {
        System.out.println("Calculando Simpson");

        Double W;
        Operacion distribucionT = new Estadistica();
        Operacion multiplicador = new Estadistica();
        Double rDT = null;
        int m;

        W = x / num_seg;

        // Iteración hasta que alidarError = true,
        // de lo contrario num_seg = num_seg*2
        int i;
        for(i = 0; i < num_seg; i++) {
            System.out.println("Iterando");
            rDT = distribucionT.calcularDistribucionT(W*i, dof);
            m = multiplicador.obtenerMultiplicador(i, num_seg);
        }

        System.out.println(x);
        System.out.println(num_seg);
        System.out.println(e);
        System.out.println(dof);
        return null;
    }

    private Boolean validarError (Pair<Double, Double> tupla, Double e) {

        return null;
    }
}
