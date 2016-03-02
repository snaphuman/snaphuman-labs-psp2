package edu.uniandes.ecos.psp2.app;
/**
 * Calculadora.java
 * Propósito: Calcula el promedio y la desviacion estándard de una lista de valores dada.
 *
 * @author Fabián Hernández
 * @version 1.0 01/27/16
 * @licence GNU/GPL v3
 */

import org.javatuples.Pair;
import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class Calculadora {

    /**
     * Recibe los datos ingresados por el usuario a través de la
     * linea de comandos
     *
     * @param args
     * @return No hay valor de retorno
     */
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();

        map.put("titulo", "Calculadora estadística");

        get("/", (req, res) -> new ModelAndView(map, "index"), new JadeTemplateEngine());
    }

    public static void test1 () {

        Pares lista = new Pares();

        lista.listaPares.add( new Pair<Double, Double>( 130.0, 186.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 650.0, 699.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 99.0, 132.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 150.0, 272.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 128.0, 291.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 302.0, 331.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 95.0, 199.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 945.0, 1890.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 368.0, 788.0 ) );

        Estimacion.probe( lista );
    }

    public static void test2 () {

        Pares lista = new Pares();

        lista.listaPares.add( Pair.with( 130.0, 15.0 ) );
        lista.listaPares.add( Pair.with( 650.0, 69.9 ) );
        lista.listaPares.add( Pair.with( 99.0, 6.5 ) );
        lista.listaPares.add( Pair.with( 150.0, 22.4 ) );
        lista.listaPares.add( Pair.with( 128.0, 28.4 ) );
        lista.listaPares.add( Pair.with( 302.0, 65.9 ) );
        lista.listaPares.add( Pair.with( 95.0, 19.4 ) );
        lista.listaPares.add( Pair.with( 945.0, 198.7 ) );
        lista.listaPares.add( Pair.with( 368.0, 38.8 ) );

        Estimacion.probe( lista );
    }

    public static void test3 () {

        Pares lista = new Pares ();

        lista.listaPares.add( Pair.with( 163.0, 186.0 ) );
        lista.listaPares.add( Pair.with( 765.0, 699.0 ) );
        lista.listaPares.add( Pair.with( 141.0, 132.0 ) );
        lista.listaPares.add( Pair.with( 166.0, 272.0 ) );
        lista.listaPares.add( Pair.with( 137.0, 291.0 ) );
        lista.listaPares.add( Pair.with( 335.0, 331.0 ) );
        lista.listaPares.add( Pair.with( 136.0, 199.0 ) );
        lista.listaPares.add( Pair.with( 1206.0, 189.0 ) );
        lista.listaPares.add( Pair.with( 433.0, 788.0 ) );

        Estimacion.probe( lista );
    }

    public static void test4 () {

        Pares lista = new Pares ();

        lista.listaPares.add( Pair.with( 163.0, 15.0 ) );
        lista.listaPares.add( Pair.with( 765.0, 69.9 ) );
        lista.listaPares.add( Pair.with( 141.0, 6.5 ) );
        lista.listaPares.add( Pair.with( 166.0, 22.4 ) );
        lista.listaPares.add( Pair.with( 137.0, 28.4 ) );
        lista.listaPares.add( Pair.with( 335.0, 65.9 ) );
        lista.listaPares.add( Pair.with( 136.0, 19.4 ) );
        lista.listaPares.add( Pair.with( 1206.0, 198.7 ) );
        lista.listaPares.add( Pair.with( 433.0, 38.8 ) );

        Estimacion.probe( lista );
    }

}
