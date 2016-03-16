package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snaphuman on 2/2/16.
 */
public class MainTest {

    @Test
    public void deberiaDemostrarInterfaces() {

        Pares lista = new Pares();
        lista.listaPares.add(Pair.with( 1.0,1.0 ) );
        lista.listaPares.add(Pair.with( 1.0,1.0 ) );

        List<Double> datos = new ArrayList<>();
        Double varianza = new Double( 0 );

        Media media = new Estadistica();
        media.calcularMediaPares( lista );

        Operacion operacion = new Estadistica();
        operacion.calcular( lista );
        operacion.calcularDesviacionEstandard( varianza );
        operacion.calcularVarianza(datos, media.calcularMediaLista(datos));
    }
}
