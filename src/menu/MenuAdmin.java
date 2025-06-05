package menu;

import Interfaz.MostrarListado;
import Validaciones.Validar;
import clasesManejoTurnos.Agenda;
import clasesPersonas.Admin;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import manejoJSON.LeerArchivoPersonas;
import org.json.JSONException;

import java.util.List;
import java.util.Scanner;

public class MenuAdmin {
    private static Scanner scan = new Scanner(System.in);

    public static void menuAdmin() throws JSONException {
        List<Medico> medicos = LeerArchivoPersonas.llenarlistamedicos();
        List<Paciente> pacientes = LeerArchivoPersonas.llenarlistaPacientes();
        Agenda agenda = new Agenda();
        int opcion = mostrarMenu();
        switch (opcion) {
            case 1:
                MostrarListado listadoMedicos = new Medico();
                listadoMedicos.mostrarLista();
                pausar();
                menuAdmin();
                break;
            case 2:
                Medico.registrarMedico(medicos);
                pausar();
                menuAdmin();
                break;
            case 3:
                Admin.manejoEliminarMedico(medicos);
                pausar();
                menuAdmin();
                break;
            case 4:
                MostrarListado listado = new Paciente();
                listado.mostrarLista();
                pausar();
                menuAdmin();
                break;
            case 5:
                Paciente.registrarPaciente(pacientes);
                pausar();
                menuAdmin();
                break;
            case 6:
                Admin.manejarEliminarPaciente(scan);
                pausar();
                menuAdmin();
                break;
            case 7:
                Agenda.mostrarTodosLosTurnos();
                pausar();
                menuAdmin();
                break;
            case 0:
                MenuPrincipal.menu();
                break;
        }
    }

    private static int mostrarMenu() {
        System.out.println("\n======== MENÚ ADMIN ========");
        System.out.println("[ 1 ] Ver médicos");
        System.out.println("[ 2 ] Agregar médico");
        System.out.println("[ 3 ] Eliminar médico");
        System.out.println("[ 4 ] Ver pacientes");
        System.out.println("[ 5 ] Agregar paciente");
        System.out.println("[ 6 ] Eliminar paciente");
        System.out.println("[ 7 ] Ver turnos");
        System.out.println("[ 0 ] Volver al menú principal");
        System.out.println("--------------------");
        System.out.print("Su eleccion: ");
        int opcion = Validar.validarSwitch(7);
        return opcion;
    }

    private static void pausar() {
        System.out.print("\nPresione Enter para continuar...");
        scan.nextLine();
    }
}

