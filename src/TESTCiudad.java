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
        boolean invalido, salir = false, incorrecto, incorrecto2;
        // Cargo el arreglo copia por si el usuario omite cargarlo
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
                invalido = false;
                switch (opNum) {
                    case 1:
                        // Muestra el arreglo original (Segun ciudades.txt)
                        System.out.println("\nEl arreglo original es:");
                        showArr(arrCiudad);
                        break;
                    case 2:
                        // Copia el arreglo original con menos o igual cantidad de ciudades
                        do {
                            System.out.println("\n¿Cuantas ciudades desea cargar?" +
                                    "\nADVERTENCIA: El numero a ingresar tiene que ser menor a la cantidad de ciudades ya existentes (100max)\n");
                            cantCiudades = sc.nextInt();
                            if (cantCiudades <= arrCiudad.length) {
                                incorrecto = false;
                                // Hace una copia del arreglo Original
                                // Los valores de la primera copia se pisan
                                copy = Ciudad.copiarArreglo(arrCiudad, cantCiudades);
                                System.out.println("\nEl arreglo con " + cantCiudades + " ciudades es:");
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
                        do {
                            System.out.println("\n1-Arreglo Original" + "\n2-Arreglo Copia\n");
                            opcionArreglo = sc.nextInt();
                            if (opcionArreglo == 1) {
                                incorrecto2 = false;
                                // Dado el nombre de una ciudad generar su abreviatura
                                System.out.println("\nEl arreglo original es:");
                                showArr(arrCiudad);
                                showAbreviaturaNombre(arrCiudad);
                            } else if (opcionArreglo == 2) {
                                incorrecto2 = false;
                                System.out.println("\nEl arreglo copia es:");
                                showArr(copy);
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
                invalido = true;
                System.out.println("\nERROR. NUMERO INVALIDO. Intentelo Nuevamente");
            }
        } while (invalido || !salir);
    }

    public static String verificacionLetra() {
        // Verifica si la letra ingresada por el usuario sea valida
        Scanner sc = new Scanner(System.in);
        String metodoTipo;
        boolean incorrecto;
        do {
            System.out.println("\nIngrese el metodo de ordenamiento: \n'A' para ordenar de forma asendente."
                    + "\n'D' para ordenar de forma desendente\n");
            metodoTipo = sc.next();
            metodoTipo = metodoTipo.toLowerCase();
            if (metodoTipo.equals("a") || metodoTipo.equals("d")) {
                incorrecto = false;
            } else {
                incorrecto = true;
                System.out.println("\nERROR: CARACTER NO VALIDO. \nIntentelo Nuevamente.");
            }
        } while (incorrecto);
        return metodoTipo;
    }

    public static void showText(Ciudad[] arrCiudad, String letra) {
        if (letra.equalsIgnoreCase("a")) {
            System.out.println("\nEl arreglo ordenado de forma asendente es:");
            showArr(arrCiudad);
        } else {
            System.out.println("\nEl arreglo ordenado de forma desendente es:");
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
                System.out.println("\nIngrese el numero de la ciudad que desea abreviar, segun su posicion en el arreglo\n");
                numUsuario = sc.nextInt() - 1; // ´Para lo que el usuario es posicion 1, para nosotros seria posicion 0
                if (numUsuario <= longArr) {
                    incorrecto = false;
                    largoPalabra = arrCiudad[numUsuario].getNombre().replaceAll(" ", "").length() - 1;
                    System.out
                            .println("\nLa abreviacion es: " + arrCiudad[numUsuario].abreviatura(largoPalabra) + "\n");
                } else {
                    incorrecto = true;
                    System.out.println("\nERROR: NUMERO INVALIDO. Intentelo Nuevamente. \n");
                }
            } while (incorrecto);

            do {
                System.out.println("¿Desea ingresar otro  numero? " + "S/N" + "\n");
                letraUsuario = sc.next();
                if (letraUsuario.equalsIgnoreCase("s")) {
                    seguir = false;
                    incorrecto = true;
                } else if (letraUsuario.equalsIgnoreCase("n")) {
                    seguir = false;
                } else {
                    seguir = true;
                    System.out.println("\nERROR: CARACTER INVALIDO. Intentelo Nuevamente.");
                }
            } while (seguir);

        } while (incorrecto || seguir);
    }

    public static void showArr(Ciudad[] arrCiudad) {
        // Muestra el arreglo de ciudades por pantalla
        int longArr = arrCiudad.length;
        System.out.println();
        for (int i = 0; i < longArr; i++) {
            System.out.println("Posicion: " + (i + 1) + " " + arrCiudad[i].toString());
        }
        System.out.println();
    }

}