package menu;

import Interfaz.MostrarListado;
import Validaciones.Validar;
import clasesManejoTurnos.Agenda;
import clasesManejoTurnos.Turno;
import clasesPersonas.*;
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
        System.out.printf("\n[ 3 ] Si es administrador.");
        System.out.printf("\n[ 4 ] Para registrarse como paciente.");
        System.out.printf("\n[ 0 ] Si desea terminar el programa");
        System.out.printf("\n\nSu eleccion: ");
        eleccion = Validar.validarSwitch(4);
        switch (eleccion) {
            case 1:
                System.out.printf("\nIngrese su ID: ");
                int idMedico = scan.nextInt();
                scan.nextLine();

                List<Medico> medicos1 = LeerArchivoPersonas.llenarlistamedicos();
                Medico medico = Medico.buscarMedicoPorId(idMedico, medicos);

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
                long dniUsuario = Validar.validarLong();


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
                menuUser(paciente);
                break;
            case 3:
                List<Admin> admins = LeerArchivoPersonas.llenarListaAdmin();
                System.out.printf("\ningrese su usuario: ");
                String userAdmin = scan.nextLine();
                Admin admin = Admin.buscarAdminPorUser(userAdmin, admins);
                if (admin == null) {
                    System.out.println("Admin no encontrado.");
                    System.out.print("\nPresione Enter para continuar...");
                    scan.nextLine();
                    menu();
                }
                System.out.println("\nIngrese su contrasenia: ");
                String contraseniaAdmin = scan.nextLine();
                if (!admin.getContrasenia().equals(contraseniaAdmin)) {
                    System.out.println("Contrasenia incorrecta.");
                    System.out.print("\nPresione Enter para continuar...");
                    scan.nextLine();
                    menu();
                }
                menuAdmin();
                break;
            case 4:
                pacientes = GrabarJSONPersonas.registrarPaciente(pacientes);
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menu();
                break;
        }

    }

    public static void menuMedico(Medico medico) throws JSONException {
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
        opcion = Validar.validarSwitch(2);
        switch (opcion) {
            case 1:
                LeerArchivoAgenda.mostrarTurnosDeUnMedico(agenda.getAgenda(), medico.getNombreYapellido());
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuMedico(medico);
                break;
            case 2:
                LeerArchivoAgenda.mostrarTurnosDeUnMedico(agenda.getAgenda(), medico.getNombreYapellido());
                System.out.println("\nIngrese el id del turno a reprogramar: ");
                int idDeTurnoAreprogramar = scan.nextInt();
                agenda.setAgenda(LeerArchivoAgenda.reprogramarTurno(agenda.getAgenda(), idDeTurnoAreprogramar, medico.getId()));
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
        opcion = Validar.validarSwitch(4);
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
                Agenda.mostrarTodosMisTurnos(paciente, pacientes);
                System.out.println("Id del turno que desea eliminar: ");
                int idAeliminar = scan.nextInt();
                Agenda.eliminarUnTurnoMio(idAeliminar, agenda);
                System.out.printf("\nTurnos actualizados: ");
                Agenda.mostrarTodosMisTurnos(paciente, pacientes);
                menuUser(paciente);
                break;
            case 4:
                Agenda.mostrarTodosMisTurnos(paciente, pacientes);
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuUser(paciente);
                break;
            case 0:
                menu();
                break;
        }

    }


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
        opcion = Validar.validarSwitch(7);
        switch (opcion) {
            case 1:
                MostrarListado listadoMedicos = new Medico();
                listadoMedicos.mostrarLista();
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuAdmin();
                break;
            case 2:
                medicos = GrabarJSONPersonas.registrarMedico(medicos);
                System.out.println("\nSe agrego correctamente.");
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuAdmin();
                break;
            case 3:
                LeerArchivoPersonas.mostrarTodosLosMedicos(medicos);
                System.out.println("\nIngrese el id del medico a eliminar (tambien se van a eliminar sus turnos):");
                int idAeliminar = Validar.validarEntero();
                GrabarJSONPersonas.eliminarMedico(idAeliminar);
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuAdmin();
                break;
            case 4:
                MostrarListado listado = new Paciente();
                listado.mostrarLista();
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuAdmin();
                break;
            case 5:
                pacientes = GrabarJSONPersonas.registrarPaciente(pacientes);
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuAdmin();
                break;
            case 6:
                MostrarListado listado1 = new Paciente();
                listado1.mostrarLista();
                System.out.println("\nIngrese el dni del paciente a eliminar (tambien se van a eliminar sus turnos):");
                long dniAeliminar = scan.nextLong();
                GrabarJSONPersonas.eliminarPaciente(dniAeliminar);
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuAdmin();
                break;
            case 7:
                Agenda.mostrarTodosLosTurnos();
                System.out.print("\nPresione Enter para continuar...");
                scan.nextLine();
                menuAdmin();
                break;
            case 0:
                menu();
                break;
        }

    }



}
