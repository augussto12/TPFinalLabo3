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
import java.util.Iterator;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Agenda {
    private List<Turno> agenda;
    private String nombre;

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

    public static void mostrarUnTurno(Turno t) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.printf("Fecha: %s%n", t.getFecha().format(formatter));
        System.out.printf("motivo: " + t.getMotivo());
        System.out.printf("\nid de turno: " + t.getIdTurno());
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
        System.out.printf("\n");
    }

    public static Turno sacarTurno(Paciente paciente) throws JSONException {
        List<Medico> medicos = LeerArchivoPersonas.llenarlistamedicos();
        List<Paciente> pacientes = LeerArchivoPersonas.llenarlistaPacientes();
        Agenda agenda = LeerArchivoAgenda.LeerArchivo();
        int idTurno;
        boolean idRepetido;
        do {
            idTurno = (int) (Math.random() * 1000);
            idRepetido = false;

            for (Turno t : agenda.getAgenda()) {
                if (t.getIdTurno() == idTurno) {
                    idRepetido = true;
                    break;
                }
            }
        } while (idRepetido);

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
        Turno turno = new Turno(fecha, medico, paciente, motivo, idTurno);
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
                System.out.println("\n---------------Turno " + contador + "---------------");
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

    public static void eliminarUnTurnoMio(int idAeliminar, Agenda agenda) throws JSONException {
        agenda = LeerArchivoAgenda.LeerArchivo();
        Iterator<Turno> iterator = agenda.getAgenda().iterator();
        while (iterator.hasNext()) {
            Turno t = iterator.next();
            if (idAeliminar == t.getIdTurno()) {
                iterator.remove();
                System.out.println("\nSe eliminó con éxito");
            }
        }
        GrabarJSONAgenda.llenarAgenda(agenda);
    }

}
