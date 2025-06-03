package menu;

import clasesPersonas.ListaMedicos;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import manejoJSON.LeerArchivoPersonas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    public void menu() {
        List<Paciente> pacientes = LeerArchivoPersonas.llenarlistaPacientes();
        List<Medico> medicos = LeerArchivoPersonas.llenarlistamedicos();
        Scanner scan = new Scanner(System.in);
        int eleccion = 0;
        System.out.printf("\n======HOSPITAL========");
        System.out.printf("\n[ 1 ] Si es administrador.");
        System.out.printf("\n[ 2 ] Si es un paciente.");
        System.out.printf("\n[ 0 ] Si desea terminar el programa");
        System.out.printf("\n\nSu eleccion: ");
        eleccion = scan.nextInt();
        switch (eleccion) {
            case 1:
                //chequeo si existe ese admin y si la contra es correcta
                //menuAdmin();
                break;
            case 2:
                String dniUsuario = scan.nextLine();
                Paciente paciente = Paciente.buscarPacientePorDNI(dniUsuario, pacientes);
                if (paciente == null) {
                    System.out.println("Paciente no encontrado.");
                }
                String contrasenia = scan.nextLine();
                if (!paciente.getContrasenia().equals(contrasenia)) {
                    System.out.println("Contrasenia incorrecta.");
                }
                //chequeo si existe ese user y si la contra es correcta con nombre y dni
                menuUser(paciente);
                break;
        }

    }

    public static void menuAdmin() {
        //eliminar turno
        //modificar turno
        //ver turnos propios
    }

    public static void menuUser(Paciente paciente) {
        List<Medico> medicos = LeerArchivoPersonas.llenarlistamedicos();
        Scanner scan = new Scanner(System.in);
        int opcion = 0;
        System.out.println("\n====== MENÚ PACIENTE =======");
        System.out.println("[1] Ver médicos");
        System.out.println("[2] Sacar turno");
        System.out.println("[3] Eliminar mi turno");
        System.out.println("[4] Ver mis turnos");
        //System.out.println("[5] Modificar mi turno");
        System.out.println("[0] Volver al menú principal");
        opcion = scan.nextInt();

        switch (opcion) {
            case 1:
                ListaMedicos.listarMedicos(medicos);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
        //agregar turno
        //eliminar SU turno
        //modificar SU turno

    }

}
