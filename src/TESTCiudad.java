import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class testCiudad {
    public static void main(String[] args) {
        Ciudad[] arrCiudades = new Ciudad[100];
        int longArr = arrCiudades.length;
        final String txtRuta = "F:/Universidad/2-SEGUNDO CUATRIMESTRE TPS/DESARROLLO DE ALGORITMOS/TrabajoFinal/TrabajoFinalDDA/src/ciudades.txt"; // Ruta del archivo                                                                                                                           // archivo
        cargarArrCiudades(arrCiudades, longArr, txtRuta); // Carga el arreglo con el objeto Ciudad
        Ciudad[] copy = Ciudad.copiarArreglo(arrCiudades, longArr); // Hace una copia del arreglo Original
        String letra = verificacionLetra();
        Ciudad.ordenamientoInsercion(copy, letra); // Ordena el arreglo lexicograficamente segun lo pida el usuario
        showText(letra, copy); // Muestra por pantalla el arreglo segun su ordenamiento
        showAbreviaturaNombre(copy); // Dado el nombre de una ciudad generar su abreviatura
    }

    public static void cargarArrCiudades(Ciudad[] arrCiudad, int longArr, String txtRuta) {
        // Modulo que carga a un arreglo las ciudades del archivo de texto
        String lineaTxt;
        int i;
        try {
            BufferedReader buff = new BufferedReader(new FileReader(txtRuta));
            try (Scanner sc = new Scanner(buff)) {
                for (i = 0; i < longArr; i++) {
                    lineaTxt = sc.nextLine();
                    String[] info = lineaTxt.split(";");
                    arrCiudad[i] = new Ciudad(info[0], Integer.parseInt(info[1]), Float.parseFloat(info[2]),
                            Float.parseFloat(info[3]));
                }
                buff.close();
                sc.close();
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nEl archivo no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }

    }

    public static String verificacionLetra() {
        // Verifica si la letra ingresada por el usuario sea valida
        Scanner sc = new Scanner(System.in);
        String metodoTipo;
        boolean incorrecto;
        do {
            System.out.println("Ingrese el metodo de ordenamiento: \n'A' para ordenar de forma asendente."
                    + "\n'D' para ordenar de forma desendente");
            metodoTipo = sc.next();
            if (metodoTipo.equalsIgnoreCase("a") || metodoTipo.equalsIgnoreCase("d")) {
                incorrecto = false;
            } else {
                incorrecto = true;
                System.out.println("ERROR: CARACTER NO VALIDO. \nIntentelo nuevamente.");
            }
        } while (incorrecto);
        return metodoTipo;
    }

    public static void showText(String letra, Ciudad[] arrCiudad) {
        if (letra.equalsIgnoreCase("a")) {
            System.out.println("\nEl arreglo ordenado de forma asendente es:" + "\n");
            showArr(arrCiudad);
        } else {
            System.out.println("\nEl arreglo ordenado de forma desendente es:" + "\n");
            showArr(arrCiudad);
        }
    }

    public static void showAbreviaturaNombre(Ciudad[] arrCiudad) {
        Scanner sc = new Scanner(System.in);
        boolean incorrecto, seguir;
        int numUsuario, largoPalabra, longArr = arrCiudad.length - 1;
        String letraUsuario;
        
        do {
            do{
                System.out.println("Ingrese el numero de la ciudad, segun la posicion del arreglo");
                numUsuario = sc.nextInt();
                if (numUsuario <= longArr) {
                    incorrecto = false;
                    largoPalabra = arrCiudad[numUsuario].getNombre().replaceAll(" ", "").length() - 1;
                    System.out.println(arrCiudad[numUsuario].abreviatura(largoPalabra));
                } else {
                    incorrecto = true;
                    System.out.println("ERROR: NUMERO INVALIDO. Intentelo nuevamente.");
                }
            }while(incorrecto);
                System.out.println("¿Desea ingresar otro numero? " + "S/N");
                letraUsuario = sc.next();
                if (letraUsuario.equalsIgnoreCase("s")) {
                    seguir = true;
                } else if (letraUsuario.equalsIgnoreCase("n")) {
                    seguir = false;
                } else {
                    seguir = true;
                    System.out.println("ERROR: CARACTER INVALIDO. Intentelo nuevamente.");
                }
        } while (incorrecto || seguir);
    }

    public static void showArr(Ciudad[] arrCiudad) {
        // Muestra el arreglo de ciudades por pantalla
        int longArr = arrCiudad.length;
        for (int i = 0; i < longArr; i++) {
            System.out.println(arrCiudad[i].toString());
        }
        System.out.println();
    }

}
