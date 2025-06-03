package clasesManejoTurnos;

import clasesPersonas.Paciente;
import clasesPersonas.Medico;
import clasesPersonas.ListaMedicos;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

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
        System.out.printf("\nTurnos del paciente con DNI: "+dniPaciente+ "\n");
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

    public void mostrarUnTurno(Turno t) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.printf("Fecha: %s%n", t.getFecha().format(formatter));
        System.out.printf("motivo: " + t.getMotivo());
        System.out.printf("\npaciente: ");
        System.out.printf("\n - nombre: " + t.getCliente().getNombreYapellido());
        System.out.printf("\n - edad: " + t.getCliente().getEdad());
        System.out.printf("\n - dni: " + t.getCliente().getDni());
        System.out.printf("\n - telefono: " + t.getCliente().getTelefono());
        System.out.printf("\nmedico: ");
        System.out.printf("\n - id: " + t.getMedico().getId());
        System.out.printf("\n - nombre: " + t.getMedico().getNombreYapellido());
        System.out.printf("\n - especialidad: " + t.getMedico().getEspecialidad().toString());
        System.out.printf("\n - edad: " + t.getMedico().getEdad());
        System.out.printf("\n - dni: " + t.getMedico().getDni());
        System.out.printf("\n - telefono: " + t.getMedico().getTelefono());
    }

}
