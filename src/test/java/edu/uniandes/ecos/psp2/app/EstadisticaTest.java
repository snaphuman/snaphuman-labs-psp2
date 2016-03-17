package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by snaphuman on 2/29/16.
 */
public class EstadisticaTest {

    private final static double precision = 0.05;

    @Test
    public void calcularMediaPares() {

        Pares lista = new Pares();
        Media operacion = new Estadistica();

        lista.listaPares.add( Pair.with( 1.0,1.0 ) );
        lista.listaPares.add( Pair.with( 1.0,1.0 ) );

        Pair<Double, Double> result = operacion.calcularMediaPares(lista);
        assertEquals( 1.0, result.getValue0().doubleValue(), precision );
        assertEquals( 1.0, result.getValue1().doubleValue(), precision );
    }

    @Test
    public void calcularMediaLista() {

        List<Double> lista = new ArrayList<>();
        Media operacion = new Estadistica();

        lista.add( 1.0 );
        lista.add( 1.0 );

        Double result = operacion.calcularMediaLista(lista);
        assertEquals( 1.0, result, precision );
    }

    @Test
    public void calcularTamanoRelativo() {

        Lista lista = new Lista ();
        PROBE estimacion = new PROBE();

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

        assertEquals( "VS", Lista.resultadoTest5.get(0).getValue0() );
        assertEquals( 4.3954, Lista.resultadoTest5.get(0).getValue1(), precision );
        assertEquals( "S", Lista.resultadoTest5.get(1).getValue0() );
        assertEquals( 8.5083, Lista.resultadoTest5.get(1).getValue1(), precision );
        assertEquals( "M", Lista.resultadoTest5.get(2).getValue0() );
        assertEquals( 16.4697, Lista.resultadoTest5.get(2).getValue1(), precision );
        assertEquals( "L", Lista.resultadoTest5.get(3).getValue0() );
        assertEquals( 31.8807, Lista.resultadoTest5.get(3).getValue1(), precision );
        assertEquals( "VL", Lista.resultadoTest5.get(4).getValue0() );
        assertEquals( 61.7121, Lista.resultadoTest5.get(4).getValue1(), precision );
    }

}
