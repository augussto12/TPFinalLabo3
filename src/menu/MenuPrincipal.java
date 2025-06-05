package menu;

import Validaciones.Validar;
import clasesManejoTurnos.Agenda;
import clasesPersonas.Admin;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import manejoJSON.LeerArchivoPersonas;
import org.json.JSONException;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    private static Scanner scan = new Scanner(System.in);

    public static void menu() throws JSONException {
        List<Paciente> pacientes = LeerArchivoPersonas.llenarlistaPacientes();
        List<Medico> medicos = LeerArchivoPersonas.llenarlistamedicos();
        List<Admin> administradores = LeerArchivoPersonas.llenarListaAdmin();

        int opcion = mostrarMenu();
        switch (opcion) {
            case 1:
                Medico medico = Medico.pedirDatosDeInicioDeSesion(medicos);
                pausar();
                MenuMedico.menuMedico(medico);
                break;
            case 2:
                Paciente paciente = Paciente.iniciarSesionPaciente(pacientes);
                pausar();
                MenuPaciente.menuUsuario(paciente);
                break;
            case 3:
                Admin.iniciarSesionAdmin(administradores);
                break;
            case 4:
                Paciente.registrarPaciente(pacientes);
                pausar();
                menu();
                break;
            case 0:
                System.out.println("\nSaliendo del programa...");
                scan.close();
                System.exit(0);
                break;
        }
    }

    private static int mostrarMenu() {
        System.out.println("\n======== MENÚ PRINCIPAL ========");
        System.out.println("[ 1 ] Si es médico");
        System.out.println("[ 2 ] Si es paciente");
        System.out.println("[ 3 ] Si es administrador");
        System.out.println("[ 4 ] Registrarse");
        System.out.println("[ 0 ] Salir");
        System.out.println("--------------------");
        System.out.print("Su eleccion: ");
        int opcion = Validar.validarSwitch(4);
        return opcion;
    }

    private static void pausar() {
        System.out.print("\nPresione Enter para continuar...");
        scan.nextLine();
    }
}