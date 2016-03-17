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
public class Lista {

    public static List<Pair<Integer, Integer>> datos  = new ArrayList<>();
    public static List<Pair<String, Double>> resultadoTest5  = new ArrayList<>();
}
