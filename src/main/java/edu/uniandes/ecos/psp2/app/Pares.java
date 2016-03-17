package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Pares: Clase que define varias listas con pares de datos
 * ordenados cuyo Tipos varían dependiendo la aplicación
 * en donde se almacenan datos a analizar o los resultados
 * que serán presentados en la web.
 *
 * @type Part
 * @author snaphuman
 */
public class Pares {

    public static List<Pair<Double, Double>> listaPares =
            new ArrayList<>();

    public static List<Pair<Double, Double>> listaParesMedia =
            new ArrayList<>();

    public static List<Pair<Double, Double>> resultadosCoeficientes =
            new ArrayList<>();

    public static List<Pair<Double, Double>> resultadosParametros =
            new ArrayList<>();

}
