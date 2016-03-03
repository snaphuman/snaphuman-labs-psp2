package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;
import org.junit.Test;

/**
 * Created by snaphuman on 2/2/16.
 */
public class CalculadoraTest {

    @Test
    public void deberiaDemostrarInterfaces() {

        Pares lista = new Pares();

        lista.listaPares.add(Pair.with( 1.0,1.0 ) );
        lista.listaPares.add(Pair.with( 1.0,1.0 ) );

        Operacion operacion = new Estadistica();
        operacion.calcular( lista );

        Media media = new Estadistica();
        media.calcularMedia( lista );
    }
}
