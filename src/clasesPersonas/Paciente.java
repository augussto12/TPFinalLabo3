package clasesPersonas;

import Interfaz.MostrarListado;
import manejoJSON.JSONUtiles;
import manejoJSON.LeerArchivoPersonas;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Paciente extends Persona implements MostrarListado {

    public Paciente(String nombreYapellido, int edad, String dni, long telefono, String contrasenia) {
        super(nombreYapellido, edad, dni, telefono, contrasenia);
    }

    public Paciente() {
        super();
    }

    public static Paciente buscarPacientePorDNI(String dni, List<Paciente> pacientes) {
        Paciente paciente = null;
        for (Paciente p : pacientes) {
            if (Objects.equals(p.getDni(), dni)) {
                paciente = p;
            }
        }
        return paciente;
    }


    public static Paciente pedirDatosDeRegistroAlPaciente(List<Paciente> pacientes) {

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

    @Override
    public void mostrarLista() {
        List<Paciente> pacientes = LeerArchivoPersonas.llenarlistaPacientes();
        int contador = 0;
        for (Paciente p : pacientes) {
            contador++;
            System.out.printf("\n------Paciente " + contador + "--------");
            System.out.printf("\nNombre: " + p.getNombreYapellido());
            // System.out.printf("\ncontrasenia: " + p.getContrasenia());
            System.out.printf("\nEdad: " + p.getEdad());
            System.out.printf("\nTelefono: " + p.getTelefono());
            System.out.printf("\nDni: " + p.getNombreYapellido());
        }
    }


}
