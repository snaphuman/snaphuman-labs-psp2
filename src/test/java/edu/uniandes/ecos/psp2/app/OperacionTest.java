package edu.uniandes.ecos.psp2.app;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by snaphuman on 2/29/16.
 */
public class OperacionTest {

    private final static double precision = 0.05;

    @Test
    public void calcularDesviacion() {

        double lista[] = new double[10];

        lista[0] = 160;
        lista[1] = 591;
        lista[2] = 114;
        lista[3] = 229;
        lista[4] = 230;
        lista[5] = 270;
        lista[6] = 128;
        lista[7] = 1657;
        lista[8] = 624;
        lista[9] = 1503;

        Operacion operacion = new Operacion();
        double result = operacion.calcularDesviacion(lista);
        assertEquals(572.03, result, precision);
    }

    @Test
    public void calcularMediaAritmetica() {

        double lista[] = new double[10];

        lista[0] = 160;
        lista[1] = 591;
        lista[2] = 114;
        lista[3] = 229;
        lista[4] = 230;
        lista[5] = 270;
        lista[6] = 128;
        lista[7] = 1657;
        lista[8] = 624;
        lista[9] = 1503;

        Operacion operacion = new Operacion();
        double result = operacion.calcularMedia(lista);
        assertEquals(550.6, result, precision);
    }
}
