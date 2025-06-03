package clasesPersonas;

import java.util.List;
import java.util.Objects;

public class Paciente extends  Persona{

    public Paciente(String nombreYapellido, int edad, String dni, long telefono, String contrasenia) {
        super(nombreYapellido, edad, dni, telefono, contrasenia);
    }

    /*public String getContrasenia() {
            return contrasenia;
        }

        public void setContrasenia(String contrasenia) {
            this.contrasenia = contrasenia;
        }*/
    public static Paciente buscarPacientePorDNI(String dni, List<Paciente> pacientes){
        Paciente paciente = null;
        for (Paciente p : pacientes){
            if (Objects.equals(p.getDni(), dni)){
                paciente = p;
            }
        }
        return paciente;
    }


}
