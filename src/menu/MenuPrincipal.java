package menu;

import clasesManejoTurnos.Agenda;
import clasesManejoTurnos.Turno;
import clasesPersonas.ListaMedicos;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import manejoJSON.GrabarJSONAgenda;
import manejoJSON.GrabarJSONPersonas;
import manejoJSON.LeerArchivoAgenda;
import manejoJSON.LeerArchivoPersonas;
import org.json.JSONException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    public static void menu() throws JSONException {
        List<Paciente> pacientes = LeerArchivoPersonas.llenarlistaPacientes();
        List<Medico> medicos = LeerArchivoPersonas.llenarlistamedicos();
        Scanner scan = new Scanner(System.in);
        int eleccion = 0;
        System.out.printf("\n======HOSPITAL========");
        System.out.printf("\n[ 1 ] Si es medico.");
        System.out.printf("\n[ 2 ] Si es un paciente.");
        System.out.printf("\n[ 3 ] Administrador.");
        System.out.printf("\n[ 4 ] Para registrarse como paciente.");
        System.out.printf("\n[ 0 ] Si desea terminar el programa");
        System.out.printf("\n\nSu eleccion: ");
        eleccion = scan.nextInt();
        scan.nextLine();
        switch (eleccion) {
            case 1:
                System.out.printf("\nIngrese su ID: ");
                int idMedico = scan.nextInt();
                scan.nextLine();

                List<Medico> medicos1 = LeerArchivoPersonas.llenarlistamedicos();
                Medico medico = ListaMedicos.buscarMedicoPorId(idMedico, medicos);

                if (medico == null) {
                    System.out.println("\nMédico no encontrado.");
                    System.out.print("\nPresione Enter para continuar...");
                    scan.nextLine();
                    menu();
                }

                System.out.printf("\nIngrese su contraseña: ");
                String contraseniaMedico = scan.nextLine();
                if (!medico.getContrasenia().equals(contraseniaMedico)) {
                    System.out.println("\nContraseña incorrecta.");
                    System.out.print("\nPresione Enter para continuar...");
                    scan.nextLine();
                    menu();
                }

                menuMedico(medico);
                break;
            case 2:
                System.out.printf("\ningrese su DNI: ");
                String dniUsuario = scan.nextLine();


                Paciente paciente = Paciente.buscarPacientePorDNI(dniUsuario, pacientes);
                if (paciente == null) {
                    System.out.println("Paciente no encontrado.");
                    System.out.print("\nPresione Enter para continuar...");
                    scan.nextLine();
                    menu();
                }
                System.out.printf("\nIngrese su contrasenia: ");
                String contrasenia = scan.nextLine();
                if (!paciente.getContrasenia().equals(contrasenia)) {
                    System.out.println("Contrasenia incorrecta.");
                    System.out.print("\nPresione Enter para continuar...");
                    scan.nextLine();
                    menu();
                }
                //chequeo si existe ese user y si la contra es correcta con nombre y dni
                menuUser(paciente);
                break;
            case 3:

                break;

                case 4:
                pacientes = GrabarJSONPersonas.registrarUsuario(pacientes);
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menu();
                break;
        }

    }

    public static void menuMedico(Medico medico) throws JSONException {
        //eliminar turno
        //modificar turno
        //ver turnos propios

        List<Medico> medicos = LeerArchivoPersonas.llenarlistamedicos();
        List<Paciente> pacientes = LeerArchivoPersonas.llenarlistaPacientes();

        Agenda agenda = LeerArchivoAgenda.LeerArchivo();
        Scanner scan = new Scanner(System.in);
        int opcion = 0;
        System.out.println("\n====== MENÚ MÉDICO =======");
        System.out.println("[ 1 ] Ver turnos");
        System.out.println("[ 2 ] Reprogramar turno");
        System.out.println("[ 0 ] Volver al menú principal");
        System.out.printf("\nSu eleccion: ");
        opcion = scan.nextInt();
        scan.nextLine();
        switch (opcion) {
            case 1:
                LeerArchivoAgenda.mostrarTurnosDeUnMedico(agenda.getAgenda(),medico.getNombreYapellido());
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuMedico(medico);
                break;
            case 2:
                LeerArchivoAgenda.mostrarTurnosDeUnMedico(agenda.getAgenda(),medico.getNombreYapellido());
                System.out.println("\nIngrese el id del turno a reprogramar: ");
                int idDeTurnoAreprogramar=scan.nextInt();
                agenda.setAgenda(LeerArchivoAgenda.reprogramarTurno(agenda.getAgenda(),idDeTurnoAreprogramar,medico.getId()));
                GrabarJSONAgenda.llenarAgenda(agenda);
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuMedico(medico);
                break;
            case 0:
                menu();
                break;

        }

    }


    public static void menuUser(Paciente paciente) throws JSONException {
        List<Medico> medicos = LeerArchivoPersonas.llenarlistamedicos();
        List<Paciente> pacientes = LeerArchivoPersonas.llenarlistaPacientes();
        Agenda agenda = new Agenda();
        Scanner scan = new Scanner(System.in);
        int opcion = 0;
        System.out.println("\n====== MENÚ PACIENTE =======");
        System.out.println("[ 1 ] Ver médicos");
        System.out.println("[ 2 ] Sacar turno");
        System.out.println("[ 3 ] Eliminar mi turno");
        System.out.println("[ 4 ] Ver mis turnos");
        System.out.println("[ 0 ] Volver al menú principal");
        System.out.printf("\nSu eleccion: ");
        opcion = scan.nextInt();
        scan.nextLine();


        switch (opcion) {
            case 1:
                LeerArchivoPersonas.mostrarListaMedicos(medicos);
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuUser(paciente);
                break;
            case 2:
                try {
                    Turno nuevo = Agenda.sacarTurno(paciente);
                    GrabarJSONAgenda.guardarEnjsonAgendaConUnTurnoNuevo(nuevo);
                    System.out.print("\nPresione Enter para continuar...");
                    scan.nextLine();
                    menuUser(paciente);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                Agenda.mostrarTodosMisTurnos(paciente,pacientes);
                System.out.println("Id del turno que desea eliminar: ");
                int idAeliminar = scan.nextInt();
                Agenda.eliminarUnTurnoMio(idAeliminar,agenda);
                System.out.printf("\nTurnos actualizados: ");
                Agenda.mostrarTodosMisTurnos(paciente,pacientes);
                menuUser(paciente);
                break;
            case 4:
                Agenda.mostrarTodosMisTurnos(paciente,pacientes);
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuUser(paciente);
                break;
            case 5:
                break;
            case 0:
                menu();
                break;
        }

    }

/*
    public static void menuAdmin() throws JSONException {
        List<Medico> medicos = LeerArchivoPersonas.llenarlistamedicos();
        List<Paciente> pacientes = LeerArchivoPersonas.llenarlistaPacientes();
        Agenda agenda = new Agenda();
        Scanner scan = new Scanner(System.in);
        int opcion = 0;
        System.out.println("\n====== MENU ADMINISTRADOR =======");
        System.out.println("[ 1 ] Ver medicos");
        System.out.println("[ 2 ] Agregar medico");
        System.out.println("[ 3 ] Eliminar medico");
        System.out.println("[ 4 ] Ver pacientes");
        System.out.println("[ 5 ] Agregar paciente");
        System.out.println("[ 6 ] Eliminar paciente");
        System.out.println("[ 7 ] Ver turnos");
        System.out.println("[ 0 ] Volver al menú principal");
        System.out.printf("\nSu eleccion: ");
        opcion = scan.nextInt();
        scan.nextLine();

        switch (opcion) {
            case 1:
                LeerArchivoPersonas.mostrarListaMedicos(medicos);
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuAdmin();
                break;
            case 2:
                try {
                    Turno nuevo = Agenda.sacarTurno(paciente);
                    GrabarJSONAgenda.guardarEnjsonAgendaConUnTurnoNuevo(nuevo);
                    System.out.print("\nPresione Enter para continuar...");
                    scan.nextLine();
                    menuUser(paciente);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                Agenda.mostrarTodosMisTurnos(paciente,pacientes);
                System.out.println("Id del turno que desea eliminar: ");
                int idAeliminar = scan.nextInt();
                Agenda.eliminarUnTurnoMio(idAeliminar,agenda);
                System.out.printf("\nTurnos actualizados: ");
                Agenda.mostrarTodosMisTurnos(paciente,pacientes);
                menuUser(paciente);
                break;
            case 4:
                Agenda.mostrarTodosMisTurnos(paciente,pacientes);
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuUser(paciente);
                break;
            case 5:
                break;
            case 0:
                menu();
                break;
        }

    }



*/

}
