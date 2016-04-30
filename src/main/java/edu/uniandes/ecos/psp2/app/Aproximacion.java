package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by snaphuman on 4/10/16.
 */
public class Aproximacion {

    /**
     * Calcula la integración para encontrar el valor de X
     * dentro del error aceptable indicado
     *
     * @type Item
     * @param trialX Double Valor de x de prueba
     * @param esperado Double valor esperado
     * @param dof Integer Grado de libertad
     * @return busqueda Pair Resulado de la aproximación con numeros de intentos
     */
    public static Pair<Integer, Double> buscarX ( Double trialX, Double esperado, Integer dof ) {
        Double e = 0.00001;
        Integer num_seg = 10;
        Double resultado;
        NumberFormat df = DecimalFormat.getInstance();
        df.setMaximumFractionDigits( 5 );
        df.setMinimumFractionDigits( 5 );
        df.setRoundingMode( RoundingMode.UP );

        boolean esValido = false;
        double d = 0.5;
        int count = 0;
        do {
            resultado = new PROBE().calcularSimpson( trialX, num_seg ,e, dof );
            Double res = Double.valueOf( df.format( resultado ) );

            if ( ( res - esperado ) == 0 ) {

                esValido = true;
            }
            if ( res > esperado ) {

                trialX -= d;
                esValido = ( Math.abs( res - esperado ) <= e ) ? true : false;
                if ( count >= 1 && esValido == false ) {

                    d = d/2;
                }
            } else if( res < esperado ) {

                trialX += d;
                esValido = ( Math.abs( res - esperado ) <= e ) ? true : false;
                if ( count >= 1 && esValido == false ) {

                    d = d/2;
                }
            }

            count++;
        } while ( !esValido );

        Pair<Integer, Double> busqueda = Pair.with( count, Double.valueOf( df.format( trialX ) ) );

        return busqueda;
    }

    public Double intervaloPrediccion (Pares lista ) {

        Integer Xk = 386;
        int n = lista.listaPares.size();

        Pair<Integer,Double> xTdist = this.buscarX(1.0,0.35,n-2);

        // Requiero evaluar la regresion lineal para obtener los parámetros y coeficientes
        Estadistica regresion = new Estadistica();
        regresion.calcular(lista);
        Double Rxy = Pares.resultadosCoeficientes.get(0).getValue1();
        Double R2 = Pares.resultadosCoeficientes.get(0).getValue0();
        Double B0 = Pares.resultadosParametros.get(0).getValue0();
        Double B1 = Pares.resultadosParametros.get(0).getValue1();

        List<Double> line = new ArrayList<>();
        List<Double> lineX = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Double xi = lista.listaPares.get(i).getValue0();
            Double yi = lista.listaPares.get(i).getValue1();
            Double diffyi = (yi - B0 - (B1*xi));
            line.add(diffyi);
            lineX.add(xi);
        }

        Media media = new Estadistica();
        Double pMedia = media.calcularMediaLista(line);
        Double pMediaX = media.calcularMediaLista(lineX);

        Estadistica varianza = new Estadistica();
        Double pVarianza = varianza.calcularVarianza(line, pMedia);

        Estadistica desviacion = new Estadistica();
        Double sigma = desviacion.calcularDesviacionEstandard(pVarianza);

        System.out.println("Sigma: " + sigma);

        Double sum = 0.0;
        for (int i=0; i < lineX.size(); i++) {
            sum =+ Math.pow((lineX.get(i) - pMediaX),2);

        }
        Double rango1 = xTdist.getValue1().doubleValue() * sigma;
        Double rango2 = Math.sqrt((1 + (1/n) + ( Math.pow((Xk - pMediaX),2)/sum)));

        Double rango = rango1 * rango2;


        System.out.println("rango: " + rango);
        System.out.println("tDist: " + xTdist);

        return rango;
    }

    public Double prediccionMejorada (Integer Xk) {
        Double B0 = Pares.resultadosParametros.get(0).getValue0();
        Double B1 = Pares.resultadosParametros.get(0).getValue1();

        Double Yk = B0 + (B1 * Xk);

        return Yk;
    }
}