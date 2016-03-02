package edu.uniandes.ecos.psp2.app;
/**
 * Calculadora.java
 * Propósito: Calcula el promedio y la desviacion estándard de una lista de valores dada.
 *
 * @author Fabián Hernández
 * @version 1.0 01/27/16
 * @licence GNU/GPL v3
 */

import org.javatuples.Pair;

import java.util.*;

public class Calculadora {

    /**
     * Recibe los datos ingresados por el usuario a través de la
     * linea de comandos
     *
     * @param args
     * @return No hay valor de retorno
     */
    public static void main(String[] args) {

        // Composición del mensaje de bienvenida
        String $msg;

        $msg = "Bienvenido a la calculadora estadística. \n";
        $msg += "\n";
        $msg += "Este programa le permitirá definir una lista de valores cuya longitud \n";
        $msg += "será indicada por usted, una vez definidos los valores podrá elegir \n";
        $msg += "el tipo de operación estadística que desea realizar sobre los valores \n";
        $msg += "\n";

        System.out.println($msg);

        // Proceso de entrada de datos
        Scanner in = new Scanner(System.in);

        $msg = "Por favor ingrese el numero que define la cantidad de valores a ingresar o \n";
        $msg += "escriba la palabra 'tabla1' o 'tabla2' si quiere utilizar los valores predeterminados \n";
        $msg += "> ";

        System.out.print($msg);

        // Expresión regular para validar las entradas (default|numeros)
        String $regex = "(?i)\\btabla1\\b|(?i)\\btabla2\\b|[0-9]{1,2}";

        while (!in.hasNext($regex)) {

            $msg = "Debe escribir un número entero o la palabra tabla1 o tabla2 \n";
            $msg += "> ";
            System.out.println($msg);
            in.nextLine();
        }

        String n = in.next($regex);

        double[] valores;

        if (n.contains("tabla1")) {

            System.out.println("El valor ingresado es: " + n + "\n");

            // Define valores predeterminados para realizar calculos
            List<Integer> defaults = Arrays.asList(160, 591, 114, 229, 230, 270, 128, 1657, 624, 1503);
            valores = new double[defaults.size()];

            for (int i = 0; i < defaults.size(); i++) {
                valores[i] = defaults.get(i);
            }

        } else if(n.contains("tabla2")) {

            System.out.println("El valor ingresado es: " + n + "\n");

            // Define valores predeterminados para realizar calculos
            List<Double> defaults = Arrays.asList(15.0, 69.9, 6.5, 22.4, 28.4, 65.9, 19.4, 198.7, 38.8, 138.2);
            valores = new double[defaults.size()];

            for (int i = 0; i < defaults.size(); i++) {
                valores[i] = defaults.get(i);
            }
        } else  {

            double v = Double.parseDouble(n);
            System.out.println("El valor ingresado es: " + (int) v + "\n");

            // Array que contiene la lista de valores ingresados por el
            // usuario para realizar el calculo
            valores = new double[(int) v];

            for (int i = 0; i < v; i++) {

                $msg = "Ingrese el número " + i + ": \n";
                $msg += "> ";
                System.out.println($msg);

                while (!in.hasNextDouble()) {
                    $msg = "Debe escribir un número \n";
                    $msg += "> ";
                    System.out.println($msg);
                    in.nextLine();
                }

                double num = in.nextDouble();
                valores[i] = num;
            }
        }

        System.out.println("La lista de valores para ejecutar la operación es: " + Arrays.toString(valores) + "\n");

        // Composición del mensaje para la selección de la operación
        $msg = "Las siguientes son las operaciónes estadísticas que puede realizar \n";
        $msg += "[1] Desviación Estándard \n";
        $msg += "[2] Media Aritmética \n";
        $msg += "[3] Calcular ambas \n";
        $msg += "[4] Salir del programa \n";
        $msg += "Ingrese su selección [1], [2], [3] o [4]: \n";
        $msg += "> ";

        System.out.println($msg);

        int op = in.nextInt();

        if (op == 1) {
            System.out.println("Usted ha seleccionado Desviación Estandard \n");

            Operacion operacion = new Operacion();
            double resultado = operacion.calcularDesviacion(valores);;

            System.out.println("El resultado del cálculo es: " + resultado);
        }
        else if(op == 2) {
            System.out.println("Usted ha seleccionado Media Aritmética \n");

            Operacion operacion = new Operacion();
            double resultado = operacion.calcularMedia(valores);

            System.out.println("El resultado del cálculo es: " + resultado);
        }
        else if (op == 3) {
            System.out.println("Usted ha seleccionado calcular ambas \n");

            Operacion operacion = new Operacion();
            double desviación = operacion.calcularDesviacion(valores);;
            double media = operacion.calcularMedia(valores);

            $msg = "La desviación estandar es: " + desviación + "\n";
            $msg += "La media aritmetica es: " + media;

            System.out.println($msg);
        }
        else if (op == 4) {
            // TODO: metodo para futuras operaciones. ej: guardad log en base de datos
            System.out.println("Ha elegido salir \n");
        } else {
            // TODO: preguntar nuevamente al usuario su elección
            System.out.println("El valor no existe \n");
        }

        in.close();
    }

    public static void test1 () {

        Pares lista = new Pares();

        lista.listaPares.add( new Pair<Double, Double>( 130.0, 186.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 650.0, 699.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 99.0, 132.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 150.0, 272.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 128.0, 291.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 302.0, 331.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 95.0, 199.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 945.0, 1890.0 ) );
        lista.listaPares.add( new Pair<Double, Double>( 368.0, 788.0 ) );

        EstimacionTamano.probeA( lista );
    }

}
