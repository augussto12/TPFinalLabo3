package clasesPersonas;

import Interfaz.MostrarListado;
import Validaciones.Validar;
import clasesManejoTurnos.Agenda;
import manejoJSON.JSONUtiles;
import manejoJSON.LeerArchivoPersonas;
import menu.MenuPrincipal;
import org.json.JSONException;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Paciente extends Persona implements MostrarListado {

    public Paciente(String nombreYapellido, int edad, long dni, long telefono, String contrasenia) {
        super(nombreYapellido, edad, dni, telefono, contrasenia);
    }

    public Paciente() {
        super();
    }

    public static Paciente buscarPacientePorDNI(long dni, List<Paciente> pacientes) {
        Paciente paciente = null;
        for (Paciente p : pacientes) {
            if (p.getDni() == dni) {
                paciente = p;
            }
        }
        return paciente;
    }


    public static Paciente pedirDatosDeRegistroAlPaciente(List<Paciente> pacientes) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese su nombre y apellido: ");
        String nombre = Validar.validarString();

        long dni;
        do {
            System.out.println("Ingrese su DNI (funciona como nombre de usuario): ");
            dni = Validar.validarLong();

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

    public static Paciente encontrarPaciente(long dni, List<Paciente> pacientes) {
        Paciente pacienteEncontrado = null;
        for (Paciente p : pacientes) {
            if (dni == p.getDni()) {
                pacienteEncontrado = p;
            }
        }
        return pacienteEncontrado;
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
            System.out.printf("\nDni: " + p.getDni());
        }
    }

    public static Paciente iniciarSesionPaciente(List<Paciente> pacientes) throws JSONException {
        Scanner scan = new Scanner(System.in);

        System.out.printf("\ningrese su DNI: ");
        long dniUsuario = Validar.validarLong();
        Paciente paciente = Paciente.buscarPacientePorDNI(dniUsuario, pacientes);
        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            System.out.print("\nPresione Enter para continuar...");
            scan.nextLine();
            MenuPrincipal.menu();
        }
        System.out.printf("\nIngrese su contrasenia: ");
        String contrasenia = scan.nextLine();
        if (!paciente.getContrasenia().equals(contrasenia)) {
            System.out.println("Contrasenia incorrecta.");
            System.out.print("\nPresione Enter para continuar...");
            scan.nextLine();
            MenuPrincipal.menu();
        }
        return paciente;
    }

    public static void eliminarTurno(Paciente paciente, List<Paciente> pacientes,Scanner scan,Agenda agenda) throws JSONException {
        Agenda.mostrarTodosMisTurnos(paciente, pacientes);
        System.out.println("Id del turno que desea eliminar: ");
        int idAeliminar = scan.nextInt();
        Agenda.eliminarUnTurnoMio(idAeliminar, agenda);
        System.out.printf("\nTurnos actualizados: ");
        Agenda.mostrarTodosMisTurnos(paciente, pacientes);
    }

}
