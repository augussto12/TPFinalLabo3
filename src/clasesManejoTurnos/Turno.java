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
    private int idTurno;

    public Turno (LocalDateTime fecha, Medico medico, Paciente cliente, String motivo) {
        this.fecha = fecha;
        this.medico = medico;
        this.cliente = cliente;
        this.motivo = motivo;

    }

    public Turno (LocalDateTime fecha, Medico medico, Paciente cliente, String motivo,int idTurno) {
        this.fecha = fecha;
        this.medico = medico;
        this.cliente = cliente;
        this.motivo = motivo;
        this.idTurno = idTurno;
    }


    public Turno() {
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getIdTurno() {return idTurno;}

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Cita: " + fecha.format(formatter) + ", " + medico + ", " + cliente + ", Motivo: " + motivo;
    }
}