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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class Calculadora {

    /**
     * Recibe los datos ingresados por el usuario a través de la
     * linea de comandos
     *
     * @type Item
     * @param args
     * @return No hay valor de retorno
     */
    public static void main( String[] args ) {

        staticFileLocation( "/public" );

        test1();
        test2();
        test3();
        test4();

        Map<String, String> map = new HashMap<>();
        Map<String, List> resultados = new HashMap<>();

        map.put( "titulo", "Calculadora estadística" );

        resultados.put( "Cx", Pares.resultadosCoeficientes );
        resultados.put( "Px", Pares.resultadosParametros );
        resultados.put( "pEst", proxyEstimado() );

        get( "/", ( req, res ) -> new ModelAndView( map, "index" ), new JadeTemplateEngine());
        get( "/promedio", ( req, res ) -> new ModelAndView( map, "promedio" ), new JadeTemplateEngine());
        get( "/regresion-correlacion", ( req, res ) ->
                new ModelAndView( resultados, "estadistica" ), new JadeTemplateEngine());
    }

    /**
     * Created by snaphuman
     *
     * @type Item
     */
    public static void test1 () {

        Pares lista = new Pares();

        lista.listaPares.clear();

        lista.listaPares.add( Pair.with( 130.0, 186.0 ) );
        lista.listaPares.add( Pair.with( 650.0, 699.0 ) );
        lista.listaPares.add( Pair.with( 99.0, 132.0 ) );
        lista.listaPares.add( Pair.with( 150.0, 272.0 ) );
        lista.listaPares.add( Pair.with( 128.0, 291.0 ) );
        lista.listaPares.add( Pair.with( 302.0, 331.0 ) );
        lista.listaPares.add( Pair.with( 95.0, 199.0 ) );
        lista.listaPares.add( Pair.with( 945.0, 1890.0 ) );
        lista.listaPares.add( Pair.with( 368.0, 788.0 ) );
        lista.listaPares.add( Pair.with( 961.0, 1601.0 ) );

        Operacion operacion = new Estadistica();
        operacion.calcular( lista );
    }

    /**
     * Created by snaphuman
     *
     * @type Item
     */
    public static void test2 () {

        Pares lista = new Pares();

        lista.listaPares.clear();

        lista.listaPares.add( Pair.with( 130.0, 15.0 ) );
        lista.listaPares.add( Pair.with( 650.0, 69.9 ) );
        lista.listaPares.add( Pair.with( 99.0, 6.5 ) );
        lista.listaPares.add( Pair.with( 150.0, 22.4 ) );
        lista.listaPares.add( Pair.with( 128.0, 28.4 ) );
        lista.listaPares.add( Pair.with( 302.0, 65.9 ) );
        lista.listaPares.add( Pair.with( 95.0, 19.4 ) );
        lista.listaPares.add( Pair.with( 945.0, 198.7 ) );
        lista.listaPares.add( Pair.with( 368.0, 38.8 ) );
        lista.listaPares.add( Pair.with( 961.0, 138.2 ) );

        Operacion operacion = new Estadistica();
        operacion.calcular( lista );
    }

    /**
     * Created by snaphuman
     *
     * @type Item
     */
    public static void test3 () {

        Pares lista = new Pares ();

        lista.listaPares.clear();

        lista.listaPares.add( Pair.with( 163.0, 186.0 ) );
        lista.listaPares.add( Pair.with( 765.0, 699.0 ) );
        lista.listaPares.add( Pair.with( 141.0, 132.0 ) );
        lista.listaPares.add( Pair.with( 166.0, 272.0 ) );
        lista.listaPares.add( Pair.with( 137.0, 291.0 ) );
        lista.listaPares.add( Pair.with( 335.0, 331.0 ) );
        lista.listaPares.add( Pair.with( 136.0, 199.0 ) );
        lista.listaPares.add( Pair.with( 1206.0, 189.0 ) );
        lista.listaPares.add( Pair.with( 433.0, 788.0 ) );
        lista.listaPares.add( Pair.with( 1130.0, 1601.0 ) );

        Operacion operacion = new Estadistica();
        operacion.calcular( lista );
    }

    /**
     * Created by snaphuman
     *
     * @type Item
     */
    public static void test4 () {

        Pares lista = new Pares ();

        lista.listaPares.clear();

        lista.listaPares.add( Pair.with( 163.0, 15.0 ) );
        lista.listaPares.add( Pair.with( 765.0, 69.9 ) );
        lista.listaPares.add( Pair.with( 141.0, 6.5 ) );
        lista.listaPares.add( Pair.with( 166.0, 22.4 ) );
        lista.listaPares.add( Pair.with( 137.0, 28.4 ) );
        lista.listaPares.add( Pair.with( 335.0, 65.9 ) );
        lista.listaPares.add( Pair.with( 136.0, 19.4 ) );
        lista.listaPares.add( Pair.with( 1206.0, 198.7 ) );
        lista.listaPares.add( Pair.with( 433.0, 38.8 ) );
        lista.listaPares.add( Pair.with( 1130.0, 138.2 ) );

        Operacion operacion = new Estadistica();
        operacion.calcular( lista );
    }

    /**
     * Created by snaphuman
     *
     * @type Item
     */
    public static List<Double> proxyEstimado () {

        List<Double> pEstimado = new ArrayList<>();

        for ( Pair<Double, Double> item : Pares.resultadosParametros ) {

            Double yk = item.getValue0() + ( item.getValue1() * 386 );
            pEstimado.add( yk );
        }

        return pEstimado;
    }
}
