public class Ciudad {

    //Atributos
    private String nombre;
    private int poblacion;
    private float latitud;
    private float longitud;

    //Constructoras
    public Ciudad(String elNombre, int laPoblacion, float laLatitud, float laLongitud) {
        this.nombre = elNombre;
        this.poblacion = laPoblacion;
        this.latitud = laLatitud;
        this.longitud = laLongitud;
    }

    //Constructoras con valores default
    public Ciudad(String elNombre) {
        this.nombre = elNombre;
        this.poblacion = 0;
        this.latitud = 0;
        this.longitud = 0;
    }

    //Observadoras
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
    
    //Modificadoras
    public void setPoblacion(int laPoblacion){
        this.poblacion = laPoblacion;
    }
    
    public void setLatitud(float laLatitud){
        this.latitud = laLatitud;
    }
    
    public void setLongitud(float laLongitud){
        this.longitud = laLongitud;
    }
    
    //Propias del tipo
    public boolean equals(Ciudad objeto){
        return (this.nombre.equalsIgnoreCase(objeto.getNombre()));
    }
    
    public String toString(){
        String cadena = "Nombre: " + this.getNombre() + "Poblacion: " + this.getPoblacion() + "Latitud: " + this.getLongitud() + "Longitud: " + this.getLongitud();
        return cadena;
    }

    public int compareTo(Ciudad city2) {
        return this.nombre.compareTo(city2.getNombre());
    }
}
