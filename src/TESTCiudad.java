import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class testCiudad {
    public static void main(String[] args) {
        Ciudad[] arrCiudades = new Ciudad[100];
        int longArr = arrCiudades.length;
        final String txtRuta = "F:/Universidad/2-SEGUNDO CUATRIMESTRE TPS/DESARROLLO DE ALGORITMOS/TrabajoFinal/TrabajoFinalDDA/src/ciudades.txt"; //Ruta del archivo
        cargarArrCiudades(arrCiudades, longArr, txtRuta); // Carga el arreglo con el objeto Ciudad
        Ciudad[] copy = Ciudad.copiarArreglo(arrCiudades); // Hace una copia del arreglo Original  
        //String letra = verificacionLetra();      
        //Ciudad.ordenamientoInsercion(copy, letra);// Ordena el arreglo lexicograficamente segun lo pida el usuario
        showAbreviatura(copy);

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
                    arrCiudad[i] = new Ciudad(info[0], Integer.parseInt(info[1]), Float.parseFloat(info[2]), Float.parseFloat(info[3]));
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

    public static String verificacionLetra(){
        //Verifica si la letra ingresada por el usuario sea valida
        Scanner sc = new Scanner(System.in);
        String metodoTipo;
        boolean incorrecto;
        do {
            System.out.println("Ingrese el metodo de ordenamiento: \n'a' para ordenar de forma asendente." + "\n'd' para ordenar de forma desendente");
            metodoTipo = sc.next();
            if (metodoTipo.equalsIgnoreCase("a") || metodoTipo.equalsIgnoreCase("d")) {
                incorrecto = false;
            } else {
                incorrecto = true;
                System.out.println("ERROR: CARACTER NO VALIDO. \nIntentelo nuevamente.");
            }
        } while (incorrecto);
        sc.close();
        return metodoTipo;
    }

    public static void showArr(Ciudad[] arrCiudad) {
        //Muestra el arreglo de ciudades por pantalla
        int longArr = arrCiudad.length;
        for (int i = 0; i < longArr; i++) {
            System.out.println(arrCiudad[i].toString());
        }
    }

    public static void showAbreviatura(Ciudad[] arrCiudad){
        int i, largo, longArr = arrCiudad.length;
        String nombre;
        for (i = 0; i < longArr; i++) {
            nombre = arrCiudad[i].getNombre();
            largo = nombre.length()-1;
            System.out.println(largo);
            System.out.println(arrCiudad[i].abreviatura(largo));
        }
    }
}
