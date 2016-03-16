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



}
