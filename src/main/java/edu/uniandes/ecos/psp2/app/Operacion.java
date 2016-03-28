package edu.uniandes.ecos.psp2.app;

import java.util.List;

/**
 * Interface para implementar los métodos
 * que permiten calcular operaciones estadísticas
 *
 * @type Part
 * @author snaphuman
 */
public interface Operacion {

    void calcular( Pares lista );

    Double calcularVarianza (List<Double> lista, Double media);

    Double calcularDesviacionEstandard (Double varianza);

    Integer calcularGammaInteger ( Integer valor );

    Double calcularGammaDouble ( Double valor );

    Double calcularDistribucionT (Integer num_seg, Integer dof);

    Integer obtenerMultiplicador ( Integer key, Integer value );
}
