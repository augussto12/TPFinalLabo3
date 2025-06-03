package clasesManejoTurnos;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import clasesPersonas.Persona;

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

    public int getIdTurno() {return idTurno;}


    public static LocalDateTime llenarFecha()
    {
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
        return fecha;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Cita: " + fecha.format(formatter) + ", " + medico + ", " + cliente + ", Motivo: " + motivo;
    }

}