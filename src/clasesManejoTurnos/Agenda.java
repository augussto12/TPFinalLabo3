package clasesManejoTurnos;

import Exceptions.IngresoInvalidoException;
import Exceptions.TurnoNoDisponibleException;
import Validaciones.Validar;
import clasesPersonas.Paciente;
import clasesPersonas.Medico;
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
        /// ////////////////////
        System.out.printf("\nMEDICOS DISPONIBLES:\n");
        Medico.mostrarListaMedicos(medicos);
        System.out.printf("\nIngrese el Id del medico que quiere ver:");
        int id = Validar.validarEntero();
        Medico medico = Medico.buscarMedicoPorId(id, medicos);
        if (medico == null) {
            throw new IngresoInvalidoException("Médico con ID " + id + " no encontrado.");
        }
        System.out.println("Fecha para cuando quiere sacar turno");
        LocalDateTime fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                fecha = Turno.verificarFecha(agenda.getAgenda(), id);
                fechaValida = true;
            } catch (TurnoNoDisponibleException e) {
                System.out.println(e.getMessage());
                System.out.println("Ingrese otra fecha");
            }
        }

        System.out.printf("\nMotivo de consulta:");
        String motivo = Validar.validarString();
        Turno turno = new Turno(fecha, medico, paciente, motivo, idTurno);
        return turno;
    }


    public static int mostrarTurnosPropios(Paciente paciente, List<Turno> listaTurnos) {
        int contador = 1;
        for (Turno t : listaTurnos) {
            if (t.getCliente().getDni() == (paciente.getDni())) {
                System.out.println("\n---------------Turno " + contador + "---------------");
                mostrarUnTurno(t);
                contador++;
            }
        }
        if(contador<=1)
        {
            System.out.println("\n---------------No tiene turnos pendientes---------------");
        }
        return contador;

    }

    public static int mostrarTodosMisTurnos(Paciente paciente, List<Paciente> pacientes, Agenda agenda) throws JSONException {
        List<Turno> listadoTurnos = agenda.getAgenda();
        Paciente pacienteEncontrado = Paciente.encontrarPaciente(paciente.getDni(), pacientes);
        int contador=0;
        if(pacienteEncontrado!=null)
        {
            contador=Agenda.mostrarTurnosPropios(pacienteEncontrado, listadoTurnos);
        }
        return contador;
    }

    public static void eliminarUnTurnoMio(int idAeliminar, Agenda agenda) throws JSONException {
        boolean exito = false;
        Iterator<Turno> iterator = agenda.getAgenda().iterator();
        while (iterator.hasNext()) {
            Turno t = iterator.next();
            if (idAeliminar == t.getIdTurno()) {
                iterator.remove();
                System.out.println("\nSe eliminó con éxito el turno");
                GrabarJSONAgenda.llenarAgenda(agenda);
                exito = true;
            }
        }
        if (!exito) {
            System.out.println("No hay ningun turno con ese ID.");
        }
    }

    public static void mostrarTodosLosTurnos() throws JSONException {
        Agenda agenda = LeerArchivoAgenda.LeerArchivo();
        int contador = 1;
        List<Turno> listaTurnos = agenda.getAgenda();
        for (Turno t : listaTurnos) {
            System.out.println("\n---------------Turno " + contador + "---------------");
            mostrarUnTurno(t);
            contador++;
        }
    }
}
