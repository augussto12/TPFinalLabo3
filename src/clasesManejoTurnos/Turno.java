package clasesManejoTurnos;

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
                System.out.println("Fecha para cuando quiere sacar turno");
                //Agenda agenda = new Agenda();
                System.out.printf("\nAnio: ");
                int anio = validarEntero();
                System.out.printf("\nMes: ");
                int mes = validarEntero();
                System.out.printf("\nDia: ");
                int dia = validarEntero();
                System.out.printf("\nHora: ");
                int hora = validarEntero();
                while ((hora < 8) || (hora > 20)) {
                    System.out.println("La clinica atiende de 08:00 a 20:00");
                    hora = validarEntero();
                }
                System.out.printf(": ");
                int minuto = validarEntero();
                while ((minuto != 00) && (minuto != 30)) {
                    System.out.println("Se puede sacar turno en hora en punto o y media");
                    minuto = validarEntero();

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


    public static int validarEntero() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingresa un número entero.");
            scan.next();
        }
        int valor = scan.nextInt();
        scan.nextLine();
        return valor;
    }
//CHEQUEAR QUE ESTE BIEN LA VALIDACION DE STRING
    public static String validarString() {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) { // Si detecta un entero, lo rechaza
            System.out.println("Entrada inválida. Por favor, ingresa solo texto.");
            scan.next(); // Descartar la entrada incorrecta
        }
        return scan.nextLine(); // Capturar la entrada válida
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
            System.out.println("Turno no disponible, turno ocupado.");
            fecha = llenarFecha(turnos);
        }
        return fecha;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Cita: " + fecha.format(formatter) + ", " + medico + ", " + cliente + ", Motivo: " + motivo;
    }

}