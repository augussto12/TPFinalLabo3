package clasesPersonas;

public abstract class Persona {
    private String nombreYapellido;
    private int edad ;
    private long dni ;
    private long telefono ;

    public Persona(String nombreYapellido, long telefono, long dni, int edad) {
        this.nombreYapellido = nombreYapellido;
        this.telefono = telefono;
        this.dni = dni;
        this.edad = edad;
    }

    public String getNombreYapellido() {
        return nombreYapellido;
    }

    public void setNombreYapellido(String nombreYapellido) {
        this.nombreYapellido = nombreYapellido;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
