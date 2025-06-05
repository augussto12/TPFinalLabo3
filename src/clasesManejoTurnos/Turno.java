package clasesManejoTurnos;

import Exceptions.TurnoNoDisponibleException;
import Validaciones.Validar;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import clasesPersonas.Persona;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Turno {

    private LocalDateTime fecha;
    private Medico medico;
    private Paciente cliente;
    private String motivo;
    private int idTurno;


    public Turno(LocalDateTime fecha, Medico medico, Paciente cliente, String motivo, int idTurno) {
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

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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

    public int getIdTurno() {
        return idTurno;
    }


    public static LocalDateTime llenarFecha(List<Turno> turnos) {
        Scanner scan = new Scanner(System.in);
        boolean fechaValida = false;
        LocalDateTime fecha = null;

        while (!fechaValida) {
            try {
                //Agenda agenda = new Agenda();
                System.out.printf("\nAnio: ");
                int anio = Validar.validarEntero();
                System.out.printf("\nMes: ");
                int mes = Validar.validarEntero();
                System.out.printf("\nDia: ");
                int dia = Validar.validarEntero();
                System.out.printf("\nHora: ");
                int hora = Validar.validarEntero();
                while ((hora < 8) || (hora > 20)) {
                    System.out.println("La clinica atiende de 08:00 a 20:00");
                    hora = Validar.validarEntero();
                }
                System.out.printf(": ");
                int minuto = Validar.validarEntero();
                while ((minuto != 00) && (minuto != 30)) {
                    System.out.println("Se puede sacar turno en hora en punto o y media");
                    minuto = Validar.validarEntero();

                }
                fecha = LocalDateTime.of(anio, mes, dia, hora, minuto);

                if (fecha.isBefore(LocalDateTime.now())) {
                    System.out.println("La fecha no puede ser antes de hoy.");
                } else {
                    fechaValida = true;
                }
            } catch (DateTimeException e) {
                System.out.println("Fecha invalida. Ingrese otra fecha.");
                scan.nextLine();
            }


        }
        return fecha;
    }

    public static boolean fechaDisponibleMedico(LocalDateTime fecha, List<Turno> turnos, int id) {
        for (Turno t : turnos) {
            if (t.getMedico().getId() == id) {
                if (fecha.equals(t.getFecha())) {
                    return false;
                }
            }
        }
        return true;
    }

    public static LocalDateTime verificarFecha(List<Turno> turnos, int id) {
        LocalDateTime fecha = llenarFecha(turnos);

        while (!fechaDisponibleMedico(fecha, turnos, id)) {
            throw new TurnoNoDisponibleException("Turno no disponible, turno ocupado.");
        }
        return fecha;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Cita: " + fecha.format(formatter) + ", " + medico + ", " + cliente + ", Motivo: " + motivo;
    }

}