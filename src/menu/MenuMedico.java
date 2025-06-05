package menu;

import Validaciones.Validar;
import clasesManejoTurnos.Agenda;
import clasesPersonas.Medico;
import manejoJSON.LeerArchivoAgenda;
import org.json.JSONException;

import java.util.Scanner;

public class MenuMedico {
    private static Scanner scan = new Scanner(System.in);

    public static void menuMedico(Medico medico) throws JSONException {
        Agenda agenda = LeerArchivoAgenda.LeerArchivo();
        int opcion = mostrarMenu();

        switch (opcion) {
            case 1:
                LeerArchivoAgenda.mostrarTurnosDeUnMedico(agenda.getAgenda(), medico.getId());
                pausar();
                menuMedico(medico);
                break;
            case 2:
                LeerArchivoAgenda.manejoReprogramacion(medico, scan, agenda);
                pausar();
                menuMedico(medico);
                break;
            case 0:
                MenuPrincipal.menu();
                break;
        }
    }

    private static int mostrarMenu() {
        System.out.println("\n======== MENÚ MÉDICO ========");
        System.out.println("[ 1 ] Ver turnos");
        System.out.println("[ 2 ] Reprogramar turno");
        System.out.println("[ 0 ] Volver al menú principal");
        System.out.println("--------------------");
        System.out.print("Su eleccion: ");
        int opcion = Validar.validarSwitch(2);
        return opcion;
    }

    private static void pausar() {
        System.out.print("\nPresione Enter para continuar...");
        scan.nextLine();
    }
}