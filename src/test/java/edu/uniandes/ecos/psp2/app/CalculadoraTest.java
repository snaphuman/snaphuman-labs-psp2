package edu.uniandes.ecos.psp2.app;

import org.junit.Test;

/**
 * Created by snaphuman on 2/2/16.
 */
public class CalculadoraTest {

    @Test
    public void deberiaDemostrarInterfaces() {

        Pares pares = new Pares();
        double[] lista = new double[0];

        Correlacion correlacion = new Operacion();
        correlacion.calcularCoeficientes( pares );

        Regresion regresion = new Operacion();
        regresion.calcularParametros( pares );

        Desviacion desviacion = new Operacion();
        desviacion.calcularDesviacion(lista);

        Media media = new Operacion();
        media.calcularMedia(lista);
    }

}
