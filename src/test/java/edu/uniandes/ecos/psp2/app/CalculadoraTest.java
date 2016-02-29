package edu.uniandes.ecos.psp2.app;

import org.junit.Test;

/**
 * Created by snaphuman on 2/2/16.
 */
public class CalculadoraTest {

    @Test
    public void deberiaDemostrarInterfaces() {

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

        Correlacion correlacion = new Operacion();
        correlacion.calcularCoeficientes();

        Desviacion desviacion = new Operacion();
        desviacion.calcularDesviacion(lista);

        Regresion regresion = new Operacion();
        regresion.calcularParametros();
    }

}
