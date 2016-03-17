package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;
import java.util.List;

/**
 * Interface para implementar los métodos
 * que permiten calcular la Media con diferentes
 * tipos de datos
 *
 * @type Part
 * @author snaphuman
 */
public interface Media {

    Pair<Double, Double> calcularMediaPares( Pares lista );

    Double calcularMediaLista( List<Double> lista );
}
