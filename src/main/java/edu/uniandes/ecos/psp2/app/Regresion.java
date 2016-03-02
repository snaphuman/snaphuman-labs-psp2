package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

/**
 * Created by snaphuman on 2/28/16.
 */
public interface Regresion {

    Pair<Double, Double> calcularParametros(Double x, Double y);
}
