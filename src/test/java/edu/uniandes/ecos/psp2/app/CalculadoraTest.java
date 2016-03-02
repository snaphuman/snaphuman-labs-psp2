package edu.uniandes.ecos.psp2.app;

import org.junit.Test;

/**
 * Created by snaphuman on 2/2/16.
 */
public class CalculadoraTest {

    @Test
    public void deberiaDemostrarInterfaces() {

        Double x = 0.0;
        Double y = 0.0;
        double[] lista = new double[0];

        Correlacion correlacion = new Operacion();
        correlacion.calcularCoeficientes( x , y );

        Regresion regresion = new Operacion();
        regresion.calcularParametros( x , y );

        Desviacion desviacion = new Operacion();
        desviacion.calcularDesviacion(lista);

        Media media = new Operacion();
        media.calcularMedia(lista);
    }

}
