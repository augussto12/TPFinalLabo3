package clasesManejoTurnos;

import clasesPersonas.Paciente;
import clasesPersonas.Medico;
import clasesPersonas.ListaMedicos;
import manejoJSON.GrabarJSONAgenda;
import manejoJSON.LeerArchivoAgenda;
import manejoJSON.LeerArchivoPersonas;
import org.json.JSONException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Agenda {
    private List<Turno> agenda;
    private String nombre;

    /*public void programarTurno(LocalDateTime fecha, int idMedico, Paciente paciente, String motivo, boolean esAdmin, long dniUsuarioActual) {
        if (fecha.isBefore(LocalDateTime.now())) {
            System.out.printf("\nNo se pueden programar turnos en el pasado.\n");
            return;
        }

        Medico medico = listaMedicos.buscarMedicoPorId(idMedico,listaMedicos);
        if (medico == null) {
            System.out.printf("\nMédico con ID %d no encontrado.\n", idMedico);
            return;
        }

        for (Turno t : agenda) {
            if (t.getMedico().getId() == idMedico && t.getFecha().equals(fecha)) {
                System.out.printf("\nEl médico ya tiene un turno en esa fecha.\n");
                return;
            }
        }

        if (paciente.getDni() != dniUsuarioActual) {
            System.out.printf("\nSolo puedes programar turnos para ti mismo.\n");
            return;
        }

        Turno turno = new Turno(fecha, medico, paciente, motivo);
        agenda.add(turno);
        System.out.printf("\nTurno programado exitosamente.\n");
        // grabar con json
    }*/
    public void cancelarTurno(LocalDateTime fecha, long dniPaciente, boolean esAdmin, String dniUsuarioActual) {
        Turno turnoEncontrado = null;
        for (Turno t : agenda) {
            if (!esAdmin && t.getFecha().equals(fecha) && t.getCliente().getDni().equals(dniPaciente)) {
                turnoEncontrado = t;
                break;
            }
        }

        if (turnoEncontrado == null) {
            System.out.printf("\nTurno no encontrado.\n");
            return;
        }

        if (!esAdmin && !turnoEncontrado.getCliente().getDni().equals(dniUsuarioActual)) {
            System.out.printf("\nSolo puedes cancelar tus propios turnos.\n");
            return;
        }

        agenda.remove(turnoEncontrado);
        System.out.printf("\nTurno cancelado exitosamente.\n");
        // grabar con json
    }

    public void verTurnos() {
        //leer json
    }

    public void listarTurnosPorPaciente(String dniPaciente) {
        boolean hayTurnos = false;
        System.out.printf("\nTurnos del paciente con DNI: " + dniPaciente + "\n");
        for (Turno t : agenda) {
            if (t.getCliente().getDni().equals(dniPaciente)) {
                //leer lista turnos de json
                hayTurnos = true;
            }
        }
        if (!hayTurnos) {
            System.out.printf("\nNo hay turnos para este paciente.\n");
        }
    }


    public Agenda() {
        agenda = new ArrayList<Turno>();
    }

    public Agenda(String nombre, List<Turno> agenda) {
        this.nombre = nombre;
        this.agenda = agenda;
    }

    public List<Turno> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Turno> agenda) {
        this.agenda = agenda;
    }

    public void mostrarAgenda(Agenda agenda) {
        System.out.printf(agenda.nombre);
        mostrarTurnos(agenda.getAgenda());
    }

    public void mostrarTurnos(List<Turno> turnos) {
        int contador = 0;
        for (Turno t : turnos) {
            contador++;
            System.out.println("\n------turno " + contador + "-----");
            mostrarUnTurno(t);
        }
    }

    public static void mostrarUnTurno(Turno t) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.printf("Fecha: %s%n", t.getFecha().format(formatter));
        System.out.printf("motivo: " + t.getMotivo());
        System.out.printf("\nPaciente: ");
        System.out.printf("\n - Nombre: " + t.getCliente().getNombreYapellido());
        System.out.printf("\n - Edad: " + t.getCliente().getEdad());
        System.out.printf("\n - Dni: " + t.getCliente().getDni());
        System.out.printf("\n - Telefono: " + t.getCliente().getTelefono());
        System.out.printf("\nMedico: ");
        System.out.printf("\n - Id: " + t.getMedico().getId());
        System.out.printf("\n - Nombre: " + t.getMedico().getNombreYapellido());
        System.out.printf("\n - Especialidad: " + t.getMedico().getEspecialidad().toString());
        System.out.printf("\n - Edad: " + t.getMedico().getEdad());
        System.out.printf("\n - Dni: " + t.getMedico().getDni());
        System.out.printf("\n - Telefono: " + t.getMedico().getTelefono());
    }

    public static Turno sacarTurno(Paciente paciente) throws JSONException {
        List<Medico> medicos = LeerArchivoPersonas.llenarlistamedicos();
        List<Paciente> pacientes = LeerArchivoPersonas.llenarlistaPacientes();

        Scanner scan = new Scanner(System.in);
        System.out.println("Fecha para cuando quiere sacar turno");
        //Agenda agenda = new Agenda();
        System.out.printf("\nAnio: ");
        int anio = scan.nextInt();
        scan.nextLine();
        System.out.printf("\nMes: ");
        int mes = scan.nextInt();
        scan.nextLine();
        System.out.printf("\nDia: ");
        int dia = scan.nextInt();
        scan.nextLine();
        System.out.printf("\nHora: ");
        int hora = scan.nextInt();
        scan.nextLine();
        System.out.printf("\nMinuto: ");
        int minuto = scan.nextInt();
        scan.nextLine();
        LocalDateTime fecha = LocalDateTime.of(anio, mes, dia, hora, minuto);
        System.out.printf("\nMEDICOS DISPONIBLES:");
        LeerArchivoPersonas.mostrarListaMedicos(medicos);
        System.out.printf("\nId medico:");
        int id = scan.nextInt();
        scan.nextLine();
        Medico medico = ListaMedicos.buscarMedicoPorId(id, medicos);
        System.out.printf("\n Motivo de consulta:");
        String motivo = scan.nextLine();
        Turno turno = new Turno(fecha, medico, paciente, motivo);
        return turno;
    }

    public static Paciente encontrarPaciente(String dni, List<Paciente> pacientes) {
        Paciente pacienteEncontrado = null;
        for (Paciente p : pacientes) {
            if (p.getDni().equals(dni)) {
                pacienteEncontrado = p;
            }
        }
        return pacienteEncontrado;
    }

    public static void mostrarTurnosPropios(Paciente paciente, List<Turno> listaTurnos) {
        int contador = 1;
        for (Turno t : listaTurnos) {
            if (t.getCliente().getDni().equals(paciente.getDni())) {
                System.out.println("---Turno " + contador + "---");
                mostrarUnTurno(t);
                contador++;
            }
        }

    }

    public static void mostrarTodosMisTurnos(Paciente paciente, List<Paciente> pacientes) throws JSONException {
        Agenda turnos = LeerArchivoAgenda.LeerArchivo();
        List<Turno> listadoTurnos = turnos.getAgenda();
        Paciente pacienteEncontrado = Agenda.encontrarPaciente(paciente.getDni(), pacientes);
        Agenda.mostrarTurnosPropios(pacienteEncontrado, listadoTurnos);
    }
}
