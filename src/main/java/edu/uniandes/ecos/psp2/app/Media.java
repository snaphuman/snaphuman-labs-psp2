package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

import java.util.List;

/**
 * Created by snaphuman on 2/29/16.
 *
 */
public interface Media {

    Pair<Double, Double> calcularMediaPares(Pares lista);
    Double calcularMediaLista(List<Double> lista);
}
