package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

/**
 * Created by snaphuman on 2/28/16.
 */
public class Estimacion {

    public static Pares probe(Pares lista) {

        System.out.println("Calculando estimación de tamaño probe A");
        Pares coeficientesCorrelacion = new Pares();
        Pares parametrosRegresion = new Pares();

        Correlacion correlacion = new Operacion();
        Regresion regresion = new Operacion();

        for ( Pair item : lista.listaPares) {

            Pair<Double, Double> rCorrelacion = correlacion.calcularCoeficientes(
                    (Double) item.getValue(0), (Double) item.getValue(1));

            coeficientesCorrelacion.listaPares.add(rCorrelacion);

            Pair<Double, Double> rRegresion = regresion.calcularParametros(
                    (Double) item.getValue(0), (Double) item.getValue(1));

            parametrosRegresion.listaPares.add(rRegresion);
        }

        return lista;
    }
}
