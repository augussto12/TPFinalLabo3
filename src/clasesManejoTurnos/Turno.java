package clasesManejoTurnos;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import clasesPersonas.Persona;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Turno {

    private LocalDateTime fecha;
    private Medico medico;
    private Paciente cliente;
    private String motivo;

    public Turno (LocalDateTime fecha, Medico medico, Paciente cliente, String motivo) {
        this.fecha = fecha;
        this.medico = medico;
        this.cliente = cliente;
        this.motivo = motivo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Cita: " + fecha.format(formatter) + ", " + medico + ", " + cliente + ", Motivo: " + motivo;
    }
}