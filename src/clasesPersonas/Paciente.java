package clasesPersonas;

import Exceptions.IngresoInvalidoException;
import Interfaz.MostrarListado;
import Validaciones.Validar;
import clasesManejoTurnos.Agenda;
import manejoJSON.GrabarJSONAgenda;
import manejoJSON.GrabarJSONPersonas;
import manejoJSON.JSONUtiles;
import manejoJSON.LeerArchivoPersonas;
import menu.MenuPrincipal;
import org.json.JSONException;

import java.util.Iterator;
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
        System.out.printf("\nIngrese su nombre y apellido: ");
        String nombre = Validar.validarString();

        long dni;
        do {
            System.out.printf("\nIngrese su DNI (funciona como nombre de usuario): ");
            dni = Validar.validarLong();

            if (buscarPacientePorDNI(dni, pacientes) != null) {
                System.out.println("\nEl DNI " + dni + " ya está registrado. Por favor, ingrese uno diferente: ");
            }
        } while (buscarPacientePorDNI(dni, pacientes) != null);
        System.out.printf("\nIngrese su contrasenia: ");
        String contrasenia = scan.nextLine();
        System.out.printf("\nIngrese su edad: ");
        int edad = scan.nextInt();
        scan.nextLine();
        System.out.printf("\nIngrese su telefono: ");
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

    public static void eliminarTurno(Paciente paciente, List<Paciente> pacientes, Scanner scan, Agenda agenda) throws JSONException {
        int contador=Agenda.mostrarTodosMisTurnos(paciente, pacientes, agenda);
        if (contador>1)
        {
            System.out.printf("\nId del turno que desea eliminar");
            int idAeliminar = Validar.validarEntero();
            Agenda.eliminarUnTurnoMio(idAeliminar, agenda);
        }
    }

    public static void agregarUnPacienteALaLista(Paciente nuevoPaciente, List<Paciente> pacientes) throws JSONException {
        if (nuevoPaciente == null) {
            throw new IngresoInvalidoException("El paciente no puede ser nulo");
        }
        pacientes.add(nuevoPaciente);
    }

    public static void registrarPaciente(List<Paciente> pacientes) throws JSONException {
        Paciente paciente = Paciente.pedirDatosDeRegistroAlPaciente(pacientes);
        Paciente.agregarUnPacienteALaLista(paciente, pacientes);
        GrabarJSONPersonas.agregarPacienteAlJSON(paciente);
        System.out.println("\nSe agrego correctamente.");
    }




}
