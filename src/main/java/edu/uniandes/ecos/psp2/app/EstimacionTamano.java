package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

/**
 * Created by snaphuman on 2/28/16.
 */
public class EstimacionTamano {

    public static Pares probeA(Pares lista) {

        System.out.println("Calculando estimación de tamaño probe A");
        Pares coeficientesCorrelacion = new Pares();

        Correlacion correlacion = new Operacion();

        for ( Pair item : lista.listaPares) {

            Pair<Double, Double> rCorrelacion = correlacion.calcularCoeficientes((
                    Double) item.getValue(0), (Double) item.getValue(1));

            coeficientesCorrelacion.listaPares.add(rCorrelacion);

        }

        return lista;
    }

    public void probeB() {

        System.out.println("Calculando estimación de tamaño probe B");
    }
}
