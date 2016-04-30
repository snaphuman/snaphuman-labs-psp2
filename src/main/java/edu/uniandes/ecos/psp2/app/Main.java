package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;
import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edu.uniandes.ecos.psp2.app.JsonUtil.json;
import static edu.uniandes.ecos.psp2.app.JsonUtil.toJson;
import static spark.Spark.*;

/**
 * Calculadora: Permite ejecutar varios métodos
 * de estimación que utilizan varias operaciones
 * estadísticas, almacenar los sets de datos ordenados
 * para presentarlos en la web.
 *
 * @author Fabián Hernández
 * @version 3.0 01/27/16
 */
public class Main {

    /**
     * Provee los métodos http para enviar los
     * resulados de los cálculos en la web en
     * la ruta indicada
     *
     * @type Items
     * @param args String argumentos
     */
    public static void main( String[] args ) {

        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation( "/public" );

        test1();
        test2();
        test3();
        test4();
        test5();

        Map<String, String> map = new HashMap<>();
        Map<String, List> resultadosTaller3 = new HashMap<>();
        Map<String, List> resultadosTaller4 = new HashMap<>();
        Map<String, String> taller5 = new HashMap<>();
        Map<String, String> taller6 = new HashMap<>();
        Map<String, String> taller7 = new HashMap<>();

        map.put( "titulo", "Calculadora estadística" );

        resultadosTaller3.put( "Cx", Pares.resultadosCoeficientes );
        resultadosTaller3.put( "Px", Pares.resultadosParametros );

        resultadosTaller3.put( "pEst", proxyEstimado() );
        resultadosTaller4.put( "tRelativo", Lista.resultadoTest5);

        taller5.put("titulo", "Calcular Regla de Simpson");

        taller6.put("titulo", "Encontrar valor de X");

        taller7.put("titulo", "Calcular proceso");

        get( "/", ( req, res ) -> new ModelAndView( map, "index" ),
                new JadeTemplateEngine());
        get( "/promedio", ( req, res ) -> new ModelAndView( map, "promedio" ),
                new JadeTemplateEngine());
        get( "/regresion-correlacion", ( req, res ) ->
                new ModelAndView( resultadosTaller3, "estadistica" ),
                new JadeTemplateEngine());
        get( "/tamano-relativo", ( req, res ) ->
                new ModelAndView( resultadosTaller4, "tamano-relativo"),
                new JadeTemplateEngine());

        get( "/regla-simpson", ( req, res ) ->
                new ModelAndView( taller5, "regla-simpson" ),
                new JadeTemplateEngine());

        post( "regla-simpson/calcular", ( req, res ) -> {

            PROBE simpson = new PROBE();
            Integer num_seg = null;
            Integer dof = null;
            Double x = null;
            Double error = null;
            Double resultado;

            // TODO: Validar tipos de datos en valores de usuario
            num_seg = Integer.parseInt( req.queryParams( "num_seg" ) );
            dof = Integer.parseInt( req.queryParams( "dof" ) );
            x = Double.parseDouble( req.queryParams( "x" ) );
            error = Double.parseDouble( req.queryParams("error") );

            resultado = simpson.calcularSimpson( x, num_seg, error, dof );

            return resultado;
        });

        get( "/encontrar-x", ( req, res ) ->
                new ModelAndView( taller6, "encontrar-x" ),
                new JadeTemplateEngine());

        post( "/encontrar-x/buscar", ( req, res ) -> {
            Aproximacion buscar = new Aproximacion();
            Double trialX = 0.0;
            Double p = 0.0;
            Integer dof = 0;
            Pair<Integer, Double> resultado;

            trialX = Double.parseDouble( req.queryParams( "trialX" ) );
            p = Double.parseDouble( req.queryParams( "p" ) );
            dof = Integer.parseInt( req.queryParams( "dof" ) );

            resultado = buscar.buscarX( trialX, p, dof );

            Double r = resultado.getValue1();
            Integer c = resultado.getValue0();
            System.out.println( c );
            return r;
        });

        get( "/historico-proceso", ( req, res ) ->
                new ModelAndView( taller7, "historico-proceso"),
                new JadeTemplateEngine());

        post( "/historico-proceso/calcular", ( req, res ) -> {

            Estadistica significancia = new Estadistica();
            Aproximacion rango = new Aproximacion();
            Aproximacion prediccion = new Aproximacion();
            Map<String, Double> respuesta = new HashMap<>();
            Pares lista = new Pares();

            String datos = req.queryParams("datos");
            String[] items = datos.split(";");

            for (String item : items) {
                String[] value = item.split(",");
                Double valor0 = Double.valueOf(value[0]).doubleValue();
                Double valor1 = Double.valueOf(value[1]).doubleValue();
                lista.listaPares.add(Pair.with(valor0, valor1));
            }

            Double sig =  significancia.calcularSignificancia(lista);
            Double Rxy = Pares.resultadosCoeficientes.get(0).getValue1();
            Double R2 = Pares.resultadosCoeficientes.get(0).getValue0();
            Double B0 = Pares.resultadosParametros.get(0).getValue0();
            Double B1 = Pares.resultadosParametros.get(0).getValue1();
            Double r = rango.intervaloPrediccion(lista);
            Double yK = prediccion.prediccionMejorada(386);

            respuesta.put("significancia", sig);
            respuesta.put("rxy", Rxy);
            respuesta.put("R2", R2);
            respuesta.put("B0", B0);
            respuesta.put("B1", B1);
            respuesta.put("rango", r);
            respuesta.put("yk", yK);

            res.type("application/json");
            res.body(toJson(respuesta));

            return res.body();
        }, json());

    }

    /**
     * Created by snaphuman
     *
     * @type Item
     */
    public static void test1 () {

        Pares lista = new Pares();
        Operacion operacion = new Estadistica();

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

        operacion.calcular( lista );
    }

    /**
     * Created by snaphuman
     *
     * @type Item
     */
    public static void test2 () {

        Pares lista = new Pares();
        Operacion operacion = new Estadistica();

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

        operacion.calcular( lista );
    }

    /**
     * Created by snaphuman
     *
     * @type Item
     */
    public static void test3 () {

        Pares lista = new Pares ();
        Operacion operacion = new Estadistica();

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

        operacion.calcular( lista );
    }

    /**
     * Created by snaphuman
     *
     * @type Item
     */
    public static void test4 () {

        Pares lista = new Pares ();
        Operacion operacion = new Estadistica();

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

        operacion.calcular( lista );
    }

    /**
     * Created by snaphuman
     *
     * @type Item
     */
    public static void test5() {

        Lista lista = new Lista ();
        PROBE estimacion = new PROBE();
        Lista resultadoTest5 = new Lista();

        lista.datos.add( Pair.with( 18, 3 ) );
        lista.datos.add( Pair.with( 18, 3 ) );
        lista.datos.add( Pair.with( 25, 3 ) );
        lista.datos.add( Pair.with( 31, 3 ) );
        lista.datos.add( Pair.with( 37, 3 ) );
        lista.datos.add( Pair.with( 82, 5 ) );
        lista.datos.add( Pair.with( 82, 4 ) );
        lista.datos.add( Pair.with( 87, 4 ) );
        lista.datos.add( Pair.with( 89, 4 ) );
        lista.datos.add( Pair.with( 230, 10 ) );
        lista.datos.add( Pair.with( 85, 3 ) );
        lista.datos.add( Pair.with( 87, 3 ) );
        lista.datos.add( Pair.with( 558, 10 ) );

        Lista.resultadoTest5 = estimacion.calcularTamanoRelativo( lista);
    }

    /**
     * Created by snaphuman
     *
     * @type Item
     * @return pEstimado List
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
