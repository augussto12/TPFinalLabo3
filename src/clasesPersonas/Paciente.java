package clasesPersonas;

import java.util.List;

public class Paciente extends  Persona{
    //String contrasenia;
    public Paciente(String nombreYapellido, long telefono, long dni, int edad) {
        super(nombreYapellido, telefono, dni, edad);
    }

    /*public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }*/
    public static Paciente buscarPacientePorDNI(long dni, List<Paciente> pacientes){
        Paciente paciente = null;
        for (Paciente p : pacientes){
            if (p.getDni() == dni){
                paciente = p;
            }
        }
        return paciente;
    }
}
