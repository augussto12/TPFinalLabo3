package clasesManejoTurnos;

import clasesPersonas.Paciente;
import clasesPersonas.Medico;
import clasesPersonas.ListaMedicos;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Agenda {
    private List<Turno> agenda;
    private ListaMedicos listaMedicos;
    private String nombre;


    public void programarTurno(LocalDateTime fecha, int idMedico, Paciente paciente, String motivo, boolean esAdmin, long dniUsuarioActual) {
        if (fecha.isBefore(LocalDateTime.now())) {
            System.out.printf("\nNo se pueden programar turnos en el pasado.\n");
            return;
        }

        Medico medico = listaMedicos.buscarMedicoPorId(idMedico);
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
    }
    public void cancelarTurno(LocalDateTime fecha, long dniPaciente, boolean esAdmin, long dniUsuarioActual) {
        Turno turnoEncontrado = null;
        for (Turno t : agenda) {
            if (!esAdmin && t.getFecha().equals(fecha) && t.getCliente().getDni() == dniPaciente) {
                turnoEncontrado = t;
                break;
            }
        }

        if (turnoEncontrado == null) {
            System.out.printf("\nTurno no encontrado.\n");
            return;
        }

        if (!esAdmin && turnoEncontrado.getCliente().getDni() != dniUsuarioActual) {
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
    public void listarTurnosPorPaciente(long dniPaciente) {
        boolean hayTurnos = false;
        System.out.printf("\nTurnos del paciente con DNI %d:\n", dniPaciente);
        for (Turno t : agenda) {
            if (t.getCliente().getDni() == dniPaciente) {
                //leer lista turnos de json
                hayTurnos = true;
            }
        }
        if (!hayTurnos) {
            System.out.printf("\nNo hay turnos para este paciente.\n");
        }
    }


    public Agenda(List<Turno> agenda, ListaMedicos listaMedicos) {
        this.agenda = new ArrayList<>();
        this.listaMedicos = new ListaMedicos();
    }

    public Agenda() {
    }

    public List<Turno> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Turno> agenda) {
        this.agenda = agenda;
    }

    public ListaMedicos getListaMedicos() {
        return listaMedicos;
    }

    public void setListaMedicos(ListaMedicos listaMedicos) {
        this.listaMedicos = listaMedicos;
    }
}
