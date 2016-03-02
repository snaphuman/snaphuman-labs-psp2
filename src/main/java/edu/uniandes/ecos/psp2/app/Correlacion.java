package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

/**
 * Created by snaphuman on 2/28/16.
 */
public interface Correlacion {

     Pair<Double, Double> calcularCoeficientes(Double x, Double y);
}
