package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

/**
 * Created by snaphuman on 2/28/16.
 */
public class EstimacionTamano {

    public static Pares probeA(Pares lista) {

        System.out.println("Calculando estimación de tamaño probe A");

        for ( Pair item : lista.listaPares) {

            item.getValue(0);
            item.getValue(1);
        }

        Correlacion correlacion = new Operacion();

        correlacion.calcularCoeficientes(lista);

        Regresion regresion = new Operacion();

        regresion.calcularParametros(lista);

        return lista;
    }

    public void probeB() {

        System.out.println("Calculando estimación de tamaño probe B");
    }
}
