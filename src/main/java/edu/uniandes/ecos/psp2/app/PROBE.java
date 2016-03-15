package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by snaphuman on 3/14/16.
 */
public class PROBE {

    public Pair<String, Boolean> calcularTamanoRelativo(Lista lista) {

        List<Double> tamanoItem = new ArrayList<>();
        List<Double> lnTamanoItem = new ArrayList<>();

        for ( Pair<Integer, Integer> item : lista.datos) {

            DecimalFormat df = new DecimalFormat("#.##");
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

        System.out.println(tamanoItem);
        System.out.println(lnTamanoItem);
        System.out.println(media);
        System.out.println(varianza);
        System.out.println(desviacionEst);
        System.out.println(tamanoRelativo);

        return null;
    }

    private List<Pair<String, Double>> rangosLogaritmicos (Double media, Double desviacionEst) {

        List<Pair<String, Double>> result = new ArrayList<>();

        result.add(Pair.with("VS", 0.0));
        result.add(Pair.with("S", 0.0));
        result.add(Pair.with("M", 0.0));
        result.add(Pair.with("L", 0.0));
        result.add(Pair.with("VL", 0.0));

        return result;
    }

    private List<Pair<String, Double>> puntosMediosRangos (List<Pair<String, Double>> rangosLogaritmicos) {

        List<Pair<String, Double>> result = new ArrayList<>();

        result.add(Pair.with("VS", 0.0));
        result.add(Pair.with("S", 0.0));
        result.add(Pair.with("M", 0.0));
        result.add(Pair.with("L", 0.0));
        result.add(Pair.with("VL", 0.0));

        return result;
    }
}
