package edu.uniandes.ecos.psp2.app;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by snaphuman on 2/2/16.
 */
public class CalculadoraTest {

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

        double result = Calculadora.calcularDesviacion(lista);
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

        double result = Calculadora.calcularMediaAritmetica(lista);
        assertEquals(550.6, result, precision);
    }

    @Test
    public void verificarLista() {

        double lista[] = new double[2];

        boolean valido = Calculadora.verificarLista(lista);

        assertEquals(true, valido);
    }

}
