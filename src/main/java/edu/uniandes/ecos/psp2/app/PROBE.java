package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by snaphuman on 3/14/16.
 */
public class PROBE {

    private static DecimalFormat df = new DecimalFormat("#.####");

    public List<Pair<String, Double>> calcularTamanoRelativo(Lista lista) {

        List<Double> tamanoItem = new ArrayList<>();
        List<Double> lnTamanoItem = new ArrayList<>();

        for ( Pair<Integer, Integer> item : lista.datos) {

            Double x = Double.valueOf(
                    df.format(item.getValue0().doubleValue() /
                            item.getValue1().doubleValue()));

            tamanoItem.add(x);
            lnTamanoItem.add(Double.valueOf(df.format(Math.log(x))));
        }

        Double media = new Estadistica().calcularMediaLista(lnTamanoItem);

        Double varianza = new Estadistica().calcularVarianza(lnTamanoItem, media);

        Double desviacionEst = new Estadistica().calcularDesviacionEstandard(varianza);

        List<Pair<String, Double>> rangos = this.rangosLogaritmicos(media, desviacionEst);
        List<Pair<String, Double>> tamanoRelativo = this.puntosMediosRangos(rangos);

        return tamanoRelativo;
    }

    private List<Pair<String, Double>> rangosLogaritmicos (Double media, Double sigma) {

        List<Pair<String, Double>> resultado = new ArrayList<>();

        resultado.add(Pair.with( "VS", media - ( 2 * sigma ) ) );
        resultado.add(Pair.with( "S", media - sigma ) );
        resultado.add(Pair.with( "M", media ) );
        resultado.add(Pair.with( "L", media + sigma ) );
        resultado.add(Pair.with("VL", media + ( 2 * sigma ) ) );

        return resultado;
    }

    private List<Pair<String, Double>> puntosMediosRangos (List<Pair<String, Double>> rangosLogaritmicos) {

        List<Pair<String, Double>> resultado = new ArrayList<>();

        int i = 0;
        for (Pair<String, Double> item : rangosLogaritmicos) {

            resultado.add(item.setAt1(Math.exp(rangosLogaritmicos.get(i).getValue1())));
        i++;
        }

        return resultado;
    }
}
