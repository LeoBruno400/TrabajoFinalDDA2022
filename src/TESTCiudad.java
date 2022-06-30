import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class testCiudad {
    public static void main(String[] args) {
        Ciudad[] arrCiudades = new Ciudad[100];
        int longArr = arrCiudades.length;
        cargarArrCiudades(arrCiudades, longArr);

        for (int i = 0; i < longArr; i++) {
            System.out.println(arrCiudades[i].toString());
        }
        
    }

    public static void cargarArrCiudades(Ciudad[] arrCiudad, int longArr){
        String lineaTxt;
        final String txtRuta = "F:/Universidad/2-SEGUNDO CUATRIMESTRE TPS/DESARROLLO DE ALGORITMOS/TrabajoFinal/TrabajoFinalDDA/src/ciudades.txt";
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
        } catch (IOException ex){
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }
        
    }
}
