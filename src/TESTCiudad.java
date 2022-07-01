import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class testCiudad {
    public static void main(String[] args) {
        Ciudad[] arrCiudades = new Ciudad[100];
        int longArr = arrCiudades.length;
        // Ruta del archivo
        final String txtRuta = "F:/Universidad/2-SEGUNDO CUATRIMESTRE TPS/DESARROLLO DE ALGORITMOS/TrabajoFinal/TrabajoFinalDDA/src/ciudades.txt";
        cargarArrCiudades(arrCiudades, longArr, txtRuta); // Carga el arreglo con el objeto Ciudad
        menu(arrCiudades);
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

    public static void menu(Ciudad[] arrCiudad) {
        Scanner sc = new Scanner(System.in);
        int opNum, cantCiudades, opcionArreglo;
        boolean opcion, salir = false, incorrecto, incorrecto2;
        Ciudad[] copy = Ciudad.copiarArreglo(arrCiudad, arrCiudad.length);
        do {
            System.out.println();
            System.out.println("----------------------------------------Menu----------------------------------------" +
                    "\nOpcion 1: Mostrar por pantalla el arreglo original" +
                    "\nOpcion 2: Copiar el arreglo con una cantidad de ciudades" +
                    "\nOpcion 3: Ordena lexicograficamente y muestra por pantalla el arreglo COPIA " +
                    "\nOpcion 4: Generar abreviatura de una ciudad" +
                    "\nOpcion 5: Salir" +
                    "\n------------------------------------------------------------------------------------\n");
            opNum = sc.nextInt();
            if (opNum == 1 || opNum == 2 || opNum == 3 || opNum == 4 || opNum == 5) {
                opcion = true;
                switch (opNum) {
                    case 1:
                        // Muestra el arreglo original (Segun ciudades.txt)
                        showArr(arrCiudad);
                        break;
                    case 2:
                        // Copia el arreglo original con menos o igual cantidad de ciudades
                        System.out.println("¿Cuantas ciudades desea cargar?" +
                                "\nADVERTENCIA: El numero a ingresar tiene que ser menor a la cantidad de ciudades ya existentes (100max)");
                        cantCiudades = sc.nextInt();
                        do {
                            if (cantCiudades <= arrCiudad.length) {
                                incorrecto = false;
                                // Hace una copia del arreglo Original
                                copy = Ciudad.copiarArreglo(arrCiudad, cantCiudades);
                                showArr(copy);
                            } else {
                                incorrecto = true;
                                System.out.println("ERROR: NUMERO INVALIDO. Intentelo Nuevamente");
                            }
                        } while (incorrecto);
                        break;
                    case 3:
                        String letra = verificacionLetra();
                        Ciudad.ordenamientoInsercion(copy, letra);
                        showText(copy, letra);
                        break;
                    case 4:
                        System.out.println("1-Arreglo Original" + "\n2-Arreglo Copia");
                        opcionArreglo = sc.nextInt();
                        do {
                            if (opcionArreglo == 1) {
                                incorrecto2 = false;
                                // Dado el nombre de una ciudad generar su abreviatura
                                showAbreviaturaNombre(arrCiudad);
                            } else if (opcionArreglo == 2) {
                                incorrecto2 = false;
                                showAbreviaturaNombre(copy);
                            } else {
                                incorrecto2 = true;
                                System.out.println("ERROR. NUMERO INVALIDO. Intentelo Nuevamente");
                            }
                        } while (incorrecto2);
                        break;
                    case 5:
                        salir = true;
                        break;
                }
            } else {
                opcion = false;
                System.out.println("ERROR. NUMERO INVALIDO. Intentelo Nuevamente");
            }
        } while (opcion && !salir);
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

    public static void showText(Ciudad[] arrCiudad, String letra) {
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
            do {
                System.out
                        .println("Ingrese el numero de la ciudad que desea abreviar, segun su posicion en el arreglo");
                numUsuario = sc.nextInt() - 1;
                if (numUsuario <= longArr) {
                    incorrecto = false;
                    largoPalabra = arrCiudad[numUsuario].getNombre().replaceAll(" ", "").length() - 1;
                    System.out.println(arrCiudad[numUsuario].abreviatura(largoPalabra));
                } else {
                    incorrecto = true;
                    System.out.println("ERROR: NUMERO INVALIDO. Intentelo Nuevamente.");
                }
            } while (incorrecto);
            System.out.println("¿Desea ingresar otro numero? " + "S/N");
            letraUsuario = sc.next();
            if (letraUsuario.equalsIgnoreCase("s")) {
                seguir = true;
            } else if (letraUsuario.equalsIgnoreCase("n")) {
                seguir = false;
            } else {
                seguir = true;
                System.out.println("ERROR: CARACTER INVALIDO. Intentelo Nuevamente.");
            }
        } while (incorrecto || seguir);
    }

    public static void showArr(Ciudad[] arrCiudad) {
        // Muestra el arreglo de ciudades por pantalla
        int longArr = arrCiudad.length;
        for (int i = 0; i < longArr; i++) {
            System.out.println("Posicion: " + (i + 1) + " " + arrCiudad[i].toString());
        }
        System.out.println();
    }

}