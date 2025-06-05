package clasesPersonas;

public abstract class Persona {
    private String nombreYapellido;
    private int edad ;
    private long dni ;
    private long telefono ;
    private String contrasenia;

    public Persona(String nombreYapellido, int edad, long dni, long telefono, String contrasenia) {
        this.nombreYapellido = nombreYapellido;
        this.edad = edad;
        this.dni = dni;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
    }

    public Persona(String nombreYapellido, long dni, String contrasenia) {
        this.nombreYapellido = nombreYapellido;
        this.dni = dni;
        this.contrasenia = contrasenia;
    }

    public Persona() {

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

    public String getContrasenia() {return contrasenia; }

    public void setContrasenia(String contrasenia) {this.contrasenia = contrasenia; }
}
