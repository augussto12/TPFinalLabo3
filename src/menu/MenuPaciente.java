package menu;

import Interfaz.MostrarListado;
import Validaciones.Validar;
import clasesManejoTurnos.Agenda;
import clasesManejoTurnos.Turno;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import manejoJSON.GrabarJSONAgenda;
import manejoJSON.LeerArchivoAgenda;
import manejoJSON.LeerArchivoPersonas;
import org.json.JSONException;

import java.util.List;
import java.util.Scanner;

public class MenuPaciente {
    private static Scanner scan = new Scanner(System.in);

    public static void menuUsuario(Paciente paciente) throws JSONException {
        List<Paciente> pacientes = LeerArchivoPersonas.llenarlistaPacientes();
        Agenda agenda = LeerArchivoAgenda.LeerArchivo();

        int opcion = mostrarMenu();
        switch (opcion) {
            case 1:
                MostrarListado listadoMedicos = new Medico();
                listadoMedicos.mostrarLista();
                pausar();
                menuUsuario(paciente);
                break;
            case 2:
                Turno nuevo = Agenda.sacarTurno(paciente);
                GrabarJSONAgenda.guardarEnjsonAgendaConUnTurnoNuevo(nuevo);
                pausar();
                menuUsuario(paciente);
                break;
            case 3:
                Paciente.eliminarTurno(paciente, pacientes, scan, agenda);
                pausar();
                menuUsuario(paciente);
                break;
            case 4:
                Agenda.mostrarTodosMisTurnos(paciente, pacientes, agenda);
                pausar();
                menuUsuario(paciente);
                break;
            case 0:
                MenuPrincipal.menu();
                break;
        }
    }

    private static int mostrarMenu() {
        System.out.println("\n======== MENÚ PACIENTE ========");
        System.out.println("[ 1 ] Ver médicos");
        System.out.println("[ 2 ] Sacar turno");
        System.out.println("[ 3 ] Eliminar turno");
        System.out.println("[ 4 ] Mis turnos");
        System.out.println("[ 0 ] Volver al menú principal");
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