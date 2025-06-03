package clasesPersonas;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Paciente extends Persona {

    public Paciente(String nombreYapellido, int edad, String dni, long telefono, String contrasenia) {
        super(nombreYapellido, edad, dni, telefono, contrasenia);
    }

    /*public String getContrasenia() {
            return contrasenia;
        }

        public void setContrasenia(String contrasenia) {
            this.contrasenia = contrasenia;
        }*/
    public static Paciente buscarPacientePorDNI(String dni, List<Paciente> pacientes) {
        Paciente paciente = null;
        for (Paciente p : pacientes) {
            if (Objects.equals(p.getDni(), dni)) {
                paciente = p;
            }
        }
        return paciente;
    }


    public static Paciente pedirDatosDeRegistroAlUsuario(List<Paciente> pacientes) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese su nombre y apellido: ");
        String nombre = scan.nextLine();

        String dni;
        do {
            System.out.println("Ingrese su DNI (funciona como nombre de usuario): ");
            dni = scan.nextLine();

            if (buscarPacientePorDNI(dni, pacientes) != null) {
                System.out.println("El DNI " + dni + " ya está registrado. Por favor, ingrese uno diferente.");
            }
        } while (buscarPacientePorDNI(dni, pacientes) != null);
        System.out.println("Ingrese su contrasenia: ");
        String contrasenia = scan.nextLine();
        System.out.println("Ingrese su edad: ");
        int edad = scan.nextInt();
        scan.nextLine();
        System.out.println("Ingrese su telefono: ");
        long telefono = scan.nextLong();
        scan.nextLine();

        Paciente pacienteNuevo = new Paciente(nombre, edad, dni, telefono, contrasenia);
        return pacienteNuevo;

    }


}
