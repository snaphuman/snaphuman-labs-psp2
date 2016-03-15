package edu.uniandes.ecos.psp2.app;

import java.util.List;

/**
 * Created by snaphuman on 2/28/16.
 *
 */
public interface Operacion {

    void calcular( Pares lista );

    Double calcularVarianza (List<Double> lista, Double media);

    Double calcularDesviacionEstandard (Double varianza);
}
