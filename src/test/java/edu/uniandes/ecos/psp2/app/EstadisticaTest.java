package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by snaphuman on 2/29/16.
 */
public class EstadisticaTest {

    private final static double precision = 0.05;

    @Test
    public void calcularMediaAritmetica() {

        Pares lista = new Pares();
        Media operacion = new Estadistica();

        lista.listaParesMedia.add( Pair.with( 1.0,1.0 ) );
        lista.listaParesMedia.add( Pair.with(1.0,1.0 ) );

        Pair<Double, Double> result = operacion.calcularMediaPares( lista );
        assertEquals( 1.0, result.getValue1(), precision );
    }
}
