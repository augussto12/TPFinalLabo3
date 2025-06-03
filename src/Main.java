import clasesManejoTurnos.Agenda;
import clasesManejoTurnos.Turno;
import clasesPersonas.ListaMedicos;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import manejoJSON.GrabarJSONAgenda;
import manejoJSON.GrabarJSONPersonas;
import manejoJSON.LeerArchivoAgenda;
import manejoJSON.LeerArchivoPersonas;
import org.json.JSONException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main (String [] args) throws JSONException {

        List<Medico> medicos = new ArrayList<>();
        medicos = LeerArchivoPersonas.llenarlistamedicos();
        List<Paciente> pacientes = new ArrayList<>();

        pacientes = LeerArchivoPersonas.llenarlistaPacientes();

        Scanner scan = new Scanner(System.in);
        //Agenda agenda = new Agenda();
        System.out.printf("\nanipo: ");
        int anio = scan.nextInt();
        scan.nextLine();
        System.out.printf("\nmes: ");
        int mes = scan.nextInt();
        scan.nextLine();
        System.out.printf("\ndia: ");
        int dia = scan.nextInt();
        scan.nextLine();
        System.out.printf("\nhora: ");
        int hora = scan.nextInt();
        scan.nextLine();
        System.out.printf("\nminuto: ");
        int minuto = scan.nextInt();
        scan.nextLine();
        LocalDateTime fecha = LocalDateTime.of(anio,mes,dia,hora,minuto);
        System.out.printf("\nMEDICOS DISPONIBLES:");
        LeerArchivoPersonas.mostrarListaMedicos(medicos);
        System.out.printf("\nid medico:");
        int id = scan.nextInt();
        scan.nextLine();
        Medico medico = ListaMedicos.buscarMedicoPorId(id,medicos);
        System.out.printf("\ningrese su dni: ");
        String dni = scan.nextLine();
        scan.nextLine();
        Paciente paciente = Paciente.buscarPacientePorDNI(dni,pacientes);
        System.out.printf("\n motivo:");
        String motivo = scan.nextLine();
        Turno turno = new Turno(fecha,medico,paciente,motivo);
        Agenda agenda = new Agenda();
        agenda.getAgenda().add(turno);

        GrabarJSONAgenda.llenarAgenda(agenda);

        GrabarJSONPersonas.llenarPersonas();

        agenda = LeerArchivoAgenda.LeerArchivo();
        agenda.mostrarAgenda(agenda);

    }
}