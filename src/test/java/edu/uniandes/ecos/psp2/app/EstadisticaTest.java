package edu.uniandes.ecos.psp2.app;

import org.javatuples.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by snaphuman on 2/29/16.
 */
public class EstadisticaTest {

    private final static double precision = 0.01;

    @Test
    public void calcularMediaPares() {

        Pares lista = new Pares();
        Media operacion = new Estadistica();

        lista.listaPares.clear();
        lista.listaPares.add( Pair.with( 1.0,1.0 ) );
        lista.listaPares.add( Pair.with( 1.0,1.0 ) );

        Pair<Double, Double> result = operacion.calcularMediaPares(lista);
        assertEquals( 1.0, result.getValue0().doubleValue(), precision );
        assertEquals( 1.0, result.getValue1().doubleValue(), precision );
    }

    @Test
    public void calcularMediaLista() {

        List<Double> lista = new ArrayList<>();
        Media operacion = new Estadistica();

        lista.add( 1.0 );
        lista.add( 1.0 );

        Double result = operacion.calcularMediaLista(lista);
        assertEquals( 1.0, result, precision );
    }

    @Test
    public void calcularTamanoRelativo() {

        Lista lista = new Lista ();
        PROBE estimacion = new PROBE();

        lista.datos.add( Pair.with( 18, 3 ) );
        lista.datos.add( Pair.with( 18, 3 ) );
        lista.datos.add( Pair.with( 25, 3 ) );
        lista.datos.add( Pair.with( 31, 3 ) );
        lista.datos.add( Pair.with( 37, 3 ) );
        lista.datos.add( Pair.with( 82, 5 ) );
        lista.datos.add( Pair.with( 82, 4 ) );
        lista.datos.add( Pair.with( 87, 4 ) );
        lista.datos.add( Pair.with( 89, 4 ) );
        lista.datos.add( Pair.with( 230, 10 ) );
        lista.datos.add( Pair.with( 85, 3 ) );
        lista.datos.add( Pair.with( 87, 3 ) );
        lista.datos.add( Pair.with( 558, 10 ) );

        Lista.resultadoTest5 = estimacion.calcularTamanoRelativo( lista);

        assertEquals( "VS", Lista.resultadoTest5.get(0).getValue0() );
        assertEquals( 4.3954, Lista.resultadoTest5.get(0).getValue1(), precision );
        assertEquals( "S", Lista.resultadoTest5.get(1).getValue0() );
        assertEquals( 8.5083, Lista.resultadoTest5.get(1).getValue1(), precision );
        assertEquals( "M", Lista.resultadoTest5.get(2).getValue0() );
        assertEquals( 16.4697, Lista.resultadoTest5.get(2).getValue1(), precision );
        assertEquals( "L", Lista.resultadoTest5.get(3).getValue0() );
        assertEquals( 31.8807, Lista.resultadoTest5.get(3).getValue1(), precision );
        assertEquals( "VL", Lista.resultadoTest5.get(4).getValue0() );
        assertEquals( 61.7121, Lista.resultadoTest5.get(4).getValue1(), precision );
    }

    @Test
    public void calcularGammaDouble () {
        Double valor = (double) 9 / 2;
        Double gamma = new Double(1.0);
        do {

            valor -= 1;
            gamma = gamma * valor;

        } while (valor != 0.5);

        gamma = gamma * Math.sqrt(Math.PI);

        assertEquals(11.63173, gamma, precision);
    }

    @Test
    public void calcularGammaInteger () {

        Integer valor = 5;
        Integer gamma = 1;

        for (int i = 1; i <= valor ; i++) {
            valor -= 1;
            gamma = gamma * valor;
        }

        assertEquals(24, gamma, precision);

    }

    @Test
    public void obtenerMultiplicador() {

        Integer m = 0;
        Integer num_seg = 10;

        for (int indice = 0; indice <= num_seg; indice++) {

            // verificar si el indice es inicio/fin,
            // o es par/impar
            if (indice == 0) {
                m = 1;
            } else if (indice == num_seg) {

                m = 1;
            } else if (indice % 2 == 0) {

                m = 2;
            } else if (indice % 2 != 0) {

                m = 4;
            }

            System.out.println("M: " +  m);
        }

    }

    @Test
    public void calcularSimpson() {

        PROBE simpson = new PROBE();

        Double test1 = simpson.calcularSimpson(1.1, 10, 0.00001, 9);
        Double test2 = simpson.calcularSimpson(1.1812, 10, 0.00001, 9);
        Double test3 = simpson.calcularSimpson(2.750, 30, 0.00001, 9);
        System.out.println(test1);
        assertEquals(0.35006, test1, precision );
        System.out.println(test2);
        assertEquals(0.36757, test2, precision );
        System.out.println(test3);
        assertEquals(0.49500, test3, precision );
    }

    @Test
    public void calcularSignificancia() {
        Pares lista = new Pares();
        lista.listaPares.add( Pair.with( 18.0, 3.0 ) );
        lista.listaPares.add( Pair.with( 18.0, 3.0 ) );
        lista.listaPares.add( Pair.with( 25.0, 3.0 ) );
        lista.listaPares.add( Pair.with( 31.0, 3.0 ) );
        lista.listaPares.add( Pair.with( 37.0, 3.0 ) );
        lista.listaPares.add( Pair.with( 82.0, 5.0 ) );
        lista.listaPares.add( Pair.with( 82.0, 4.0 ) );
        lista.listaPares.add( Pair.with( 87.0, 4.0 ) );
        lista.listaPares.add( Pair.with( 89.0, 4.0 ) );
        lista.listaPares.add( Pair.with( 230.0, 10.0 ) );
        lista.listaPares.add( Pair.with( 85.0, 3.0 ) );
        lista.listaPares.add( Pair.with( 87.0, 3.0 ) );
        lista.listaPares.add( Pair.with( 558.0, 10.0 ) );

        Estadistica significancia = new Estadistica();

        significancia.calcularSignificancia(lista);
    }

}
