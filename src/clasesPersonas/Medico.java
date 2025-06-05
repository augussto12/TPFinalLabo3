package clasesPersonas;

import Interfaz.MostrarListado;
import Validaciones.Validar;
import clasesManejoTurnos.Turno;
import extras.Especialidades;
import manejoJSON.LeerArchivoPersonas;
import menu.MenuPrincipal;
import org.json.JSONException;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Medico extends Persona implements MostrarListado {
    private Especialidades especialidad;
    private int id;


    public Medico(String nombreYapellido, int edad, long dni, long telefono, String contrasenia, Especialidades especialidad, int id) {
        super(nombreYapellido, edad, dni, telefono, contrasenia);
        this.especialidad = especialidad;
        this.id = id;
    }

    public Medico() {

    }

    public Especialidades getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidades especialidad) {
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Medico buscarMedicoPorDNI(long dni, List<Medico> medicos) {
        Medico medico = null;
        for (Medico m : medicos) {
            if (m.getDni() == dni) {
                medico = m;
            }
        }
        return medico;
    }


    public static Medico pedirDatosParaRegistrarMedico(List<Medico> medicos) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese su nombre y apellido: ");
        String nombre = Validar.validarString();

        long dni;
        do {
            System.out.println("Ingrese su DNI (funciona como nombre de usuario): ");
            dni = Validar.validarLong();

            if (buscarMedicoPorDNI(dni, medicos) != null) {
                System.out.println("El DNI " + dni + " ya está registrado. Por favor, ingrese uno diferente.");
            }
        } while (buscarMedicoPorDNI(dni, medicos) != null);

        int idMedico;
        boolean idRepetido;
        do {
            idMedico = (int) (Math.random() * 1000);
            idRepetido = false;

            for (Medico m : medicos) {
                if (m.getId() == idMedico) {
                    idRepetido = true;
                    break;
                }
            }
        } while (idRepetido);

        System.out.println("Ingrese su contrasenia: ");
        String contrasenia = scan.nextLine();
        System.out.println("Ingrese su edad: ");
        int edad = Validar.validarEntero();
        System.out.println("Ingrese su telefono: ");
        long telefono = Validar.validarLong();
        Especialidades especialidad = null;
        System.out.printf("\n[ 1 ] Cardiologia");
        System.out.printf("\n[ 2 ] Pediatria");
        System.out.printf("\n[ 3 ] Clinica");
        System.out.printf("\n[ 4 ] Neurologia");
        System.out.printf("\nsu eleccion:");
        int eleccion = Validar.validarSwitch(4);
        switch (eleccion) {
            case 1:
                especialidad = Especialidades.CARDIOLOGIA;
                break;
            case 2:
                especialidad = Especialidades.PEDIATRIA;
                break;
            case 3:
                especialidad = Especialidades.CLINICA;
                break;
            case 4:
                especialidad = Especialidades.NEUROLOGIA;
                break;
        }
        Medico medicoNuevo = new Medico(nombre, edad, dni, telefono, contrasenia, especialidad, idMedico);
        return medicoNuevo;
    }

    @Override
    public void mostrarLista() {
        List<Medico> medicos = LeerArchivoPersonas.llenarlistamedicos();
        Medico.mostrarListaMedicos(medicos);
    }

    public static Medico buscarMedicoPorId(int id, List<Medico> medicos) {
        Medico medico = null;
        for (Medico m : medicos) {
            if (m.getId() == id) {
                medico = m;
            }
        }
        return medico;
    }

    public static Medico pedirDatosDeInicioDeSesion(List<Medico> medicos) throws JSONException {
        Scanner scan = new Scanner(System.in);
        System.out.printf("\nIngrese su ID: ");
        int idMedico = scan.nextInt();
        scan.nextLine();

        List<Medico> medicos1 = LeerArchivoPersonas.llenarlistamedicos();
        Medico medico = Medico.buscarMedicoPorId(idMedico, medicos);

        if (medico == null) {
            System.out.println("\nMédico no encontrado.");
            System.out.print("\nPresione Enter para continuar...");
            scan.nextLine();
            MenuPrincipal.menu();
        }

        System.out.printf("\nIngrese su contraseña: ");
        String contraseniaMedico = scan.nextLine();
        if (!medico.getContrasenia().equals(contraseniaMedico)) {
            System.out.println("\nContraseña incorrecta.");
            System.out.print("\nPresione Enter para continuar...");
            scan.nextLine();
            MenuPrincipal.menu();
        }
        return medico;
    }

    public static void mostrarListaMedicos(List<Medico> medicos) {
        int contador = 0;
        Scanner scan = new Scanner(System.in);
        int opcion = 0;
        System.out.println("[ 1 ] Ver medicos por especialidad");
        System.out.println("[ 2 ] Ver todos los medicos");
        System.out.printf("\nSu eleccion: ");
        opcion = Validar.validarSwitch(2);
        switch (opcion) {
            case 1:
                mostrarPorEspecialidad(medicos);
                break;
            case 2:
                mostrarTodosLosMedicos(medicos);
                break;
        }
    }

    public static void mostrarPorEspecialidad(List<Medico> medicos) {
        Scanner scan = new Scanner(System.in);
        int contador = 0;
        Especialidades especialidad = null;
        System.out.println("[ 1 ] Cardiologos");
        System.out.println("[ 2 ] Pediatras");
        System.out.println("[ 3 ] Clinicos");
        System.out.println("[ 4 ] Neurologos");
        System.out.printf("\nSu eleccion: ");
        int opcion = Validar.validarSwitch(4);

        switch (opcion) {
            case 1:
                especialidad = Especialidades.CARDIOLOGIA;
                break;
            case 2:
                especialidad = Especialidades.PEDIATRIA;
                break;
            case 3:
                especialidad = Especialidades.CLINICA;
                break;
            case 4:
                especialidad = Especialidades.NEUROLOGIA;
                break;
            default:
        }


        for (Medico medico : medicos) {
            if (medico.getEspecialidad() == especialidad) {
                contador++;
                mostrarUnMedico(medico, contador);
            }
        }
    }

    public static void mostrarTodosLosMedicos(List<Medico> medicos) {
        int contador = 0;

        for (Medico m : medicos) {
            contador++;
            mostrarUnMedico(m, contador);
        }
    }


    public static void mostrarUnMedico(Medico medico, int contador) {
        System.out.printf("\n------medico " + contador + "--------");
        System.out.printf("\nNombre: " + medico.getNombreYapellido());
        System.out.printf("\nEdad: " + medico.getEdad());
        System.out.printf("\nTelefono: " + medico.getTelefono());
        System.out.printf("\nEspecialidad: " + medico.getEspecialidad());
        System.out.printf("\nId: " + medico.getId());
    }
}
