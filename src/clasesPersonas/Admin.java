package clasesPersonas;

import Interfaz.MostrarListado;
import Validaciones.Validar;
import manejoJSON.GrabarJSONAgenda;
import manejoJSON.GrabarJSONPersonas;
import manejoJSON.LeerArchivoPersonas;
import menu.MenuAdmin;
import menu.MenuPrincipal;
import org.json.JSONException;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Admin extends Persona {
    public Admin(String nombreYapellido, long dni, String contrasenia) {
        super(nombreYapellido, dni, contrasenia);
    }

    public Admin() {
    }

    public static Admin buscarAdminPorUser(String user, List<Admin> lista) {
        Admin admin = null;
        for (Admin a : lista) {
            if (a.getNombreYapellido().equals(user)) {
                admin = a;
            }
        }
        return admin;
    }

    public static void iniciarSesionAdmin(List<Admin> administradores) throws JSONException {
        Scanner scan = new Scanner(System.in);
        System.out.printf("\ningrese su usuario: ");
        String userAdmin = scan.nextLine();
        Admin admin = Admin.buscarAdminPorUser(userAdmin, administradores);
        if (admin == null) {
            System.out.println("Admin no encontrado.");
            System.out.print("\nPresione Enter para continuar...");
            scan.nextLine();
            MenuPrincipal.menu();
        }
        System.out.printf("\nIngrese su contrasenia: ");
        String contraseniaAdmin = scan.nextLine();
        if (!admin.getContrasenia().equals(contraseniaAdmin)) {
            System.out.println("Contrasenia incorrecta.");
            System.out.print("\nPresione Enter para continuar...");
            scan.nextLine();
            MenuPrincipal.menu();
        }
        MenuAdmin.menuAdmin();
    }


    public static void eliminarMedico(int idAeliminar) throws JSONException {
        List<Persona> personas = LeerArchivoPersonas.llenarPersonas();

        Iterator<Persona> iterator = personas.iterator();
        boolean eliminado = false;

        while (iterator.hasNext()) {
            Persona persona = iterator.next();

            if (persona instanceof Medico) {
                Medico medico = (Medico) persona;

                if (medico.getId() == idAeliminar) {
                    iterator.remove();
                    eliminado = true;
                    System.out.println("\nSe eliminó con éxito al médico con ID: " + idAeliminar);
                    GrabarJSONAgenda.eliminarTurnosDelMedico(idAeliminar);
                    break;
                }
            }
        }

        if (!eliminado) {
            System.out.println("\nNo se encontró un médico con ID: " + idAeliminar);
        }

        GrabarJSONPersonas.grabarPersonas(personas);
    }

    public static void manejoEliminarMedico(List<Medico> medicos) throws JSONException {
        Medico.mostrarTodosLosMedicos(medicos);
        System.out.println("\nIngrese el id del medico a eliminar (tambien se van a eliminar sus turnos):");
        int idAeliminar = Validar.validarEntero();
        eliminarMedico(idAeliminar);
    }

    public static void eliminarPaciente(long dniAeliminar) throws JSONException {
        List<Persona> personas = LeerArchivoPersonas.llenarPersonas();
        Iterator<Persona> iterator = personas.iterator();
        boolean eliminado = false;

        while (iterator.hasNext()) {
            Persona persona = iterator.next();

            if (persona instanceof Paciente) {
                Paciente paciente = (Paciente) persona;

                if (paciente.getDni() == (dniAeliminar)) {
                    iterator.remove();
                    eliminado = true;
                    System.out.println("\nSe eliminó con éxito al paciente con DNI: " + dniAeliminar);
                    GrabarJSONAgenda.eliminarTurnosDelPaciente(dniAeliminar);
                    break;
                }
            }
        }

        if (!eliminado) {
            System.out.println("\nNo se encontró un paciente con DNI: " + dniAeliminar);
        }

        GrabarJSONPersonas.grabarPersonas(personas);
    }

    public static void manejarEliminarPaciente(Scanner scan) throws JSONException {
        MostrarListado listado1 = new Paciente();
        listado1.mostrarLista();
        System.out.println("\nIngrese el dni del paciente a eliminar (tambien se van a eliminar sus turnos):");
        long dniAeliminar = scan.nextLong();
        eliminarPaciente(dniAeliminar);
    }
}
