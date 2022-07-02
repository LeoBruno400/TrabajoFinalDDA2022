public class Ciudad {

    // Atributos
    private String nombre;
    private int poblacion;
    private float latitud;
    private float longitud;

    // Constructoras
    public Ciudad(String elNombre, int laPoblacion, float laLatitud, float laLongitud) {
        this.nombre = elNombre;
        this.poblacion = laPoblacion;
        this.latitud = laLatitud;
        this.longitud = laLongitud;
    }

    // Constructoras con valores default
    public Ciudad(String elNombre) {
        this.nombre = elNombre;
        this.poblacion = 0;
        this.latitud = 0;
        this.longitud = 0;
    }

    // Observadoras
    public String getNombre() {
        return this.nombre;
    }

    public int getPoblacion() {
        return this.poblacion;
    }

    public float getLatitud() {
        return this.latitud;
    }

    public float getLongitud() {
        return this.longitud;
    }

    // Modificadoras
    public void setPoblacion(int laPoblacion) {
        this.poblacion = laPoblacion;
    }

    public void setLatitud(float laLatitud) {
        this.latitud = laLatitud;
    }

    public void setLongitud(float laLongitud) {
        this.longitud = laLongitud;
    }

    // Propias del tipo
    public boolean equals(Ciudad objeto) {
        return (this.nombre.equalsIgnoreCase(objeto.getNombre()));
    }

    public String toString() {
        String cadena = "Ciudad: " + this.getNombre() + " |" + " Poblacion: " + this.getPoblacion() + " |"
                + " Latitud: " + this.getLongitud() + " |" + " Longitud: " + this.getLongitud() + " |";
        return cadena;
    }

    public int compareToCiudad(Ciudad city2) {
        return this.nombre.compareTo(city2.getNombre());
        // Retorna -1 si esta antes
        // Retorna 1 si esta despues
        // Retorna 0 si son iguales

        // Ejemplo:
        // a.compareTo(b), Retorna -1 porque "a" esta antes que "b"
        // b.compareTo(a), Retorna 1 porque "b" esta despues que "a"
        // b.compareTo(b), Retorna 0 porque son iguales
    }

    public static Ciudad[] copiarArreglo(Ciudad[] arrCiudad, int longArr) {
        Ciudad[] copia = new Ciudad[longArr]; //El arreglo entra por parametro por si el usuario decide copiar menos ciudades
        System.arraycopy(arrCiudad, 0, copia, 0, longArr);
        return copia;
    }

    public static void ordenamientoInsercion(Ciudad[] arrCiudad, String ordenTipo) {
        int i, j;
        Ciudad aux;
        int n = arrCiudad.length;

        if (ordenTipo.equals("a")) { // Metodo de ordenamiento por Insercion Original
            for (i = 1; i < n; i++) {
                aux = arrCiudad[i];
                j = i;
                while (j > 0 && arrCiudad[j - 1].compareToCiudad(aux) > 0) {
                    arrCiudad[j] = arrCiudad[j - 1];
                    j--;
                }
                arrCiudad[j] = aux;
            }
        } else { // Metodo de ordenamiento por Insercion Invertida
            for (i = 1; i < n; i++) {
                aux = arrCiudad[i];
                j = i;
                while (j > 0 && arrCiudad[j - 1].compareToCiudad(aux) < 0) {
                    arrCiudad[j] = arrCiudad[j - 1];
                    j--;
                }
                arrCiudad[j] = aux;
            }
        }
    }

    public String abreviatura(int posicion) {
        String newCadena = "";
        String nombre = this.getNombre().replaceAll(" ", "");
        char caracter;

        if (posicion >= 0) {
            caracter = nombre.toLowerCase().charAt(posicion); //Para evitar comparar vocales en mayuscula
            if (caracter == 'a' || caracter == 'e' || caracter == 'i' || caracter == 'o' || caracter == 'u') {
                newCadena = abreviatura(posicion - 1);
            } else {
                newCadena = abreviatura(posicion - 1) + nombre.charAt(posicion);
            }
        }
        return newCadena;
    }
}
