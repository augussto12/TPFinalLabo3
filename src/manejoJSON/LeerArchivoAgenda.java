package manejoJSON;

import clasesManejoTurnos.Agenda;
import clasesManejoTurnos.Turno;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import extras.Especialidades;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class LeerArchivoAgenda {

    public static Agenda LeerArchivo() throws JSONException {

        JSONObject archivo = new JSONObject(JSONUtiles.leer("hospitalAgenda.json"));

        JSONArray turnosJSON = archivo.getJSONArray("turnos");
        List<Turno> turnos = new ArrayList<>();
        for (int i = 0; i < turnosJSON.length(); i++) {

            JSONObject turnojson = turnosJSON.getJSONObject(i);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime fecha = LocalDateTime.parse(turnojson.getString("fecha"), formatter);
            String motivo = turnojson.getString("motivo");
            int idTurno = turnojson.getInt("idTurno");

            JSONObject pacientejson = turnojson.getJSONObject("paciente");
            Paciente paciente = new Paciente(pacientejson.getString("nombre"), pacientejson.getInt("edad"), pacientejson.getLong("dni"), pacientejson.getLong("telefono"), pacientejson.getString("contrasenia"));

            JSONObject medicoJSON = turnojson.getJSONObject("medico");
            String especialidadjson = medicoJSON.getString("especialidad");
            Especialidades especialidad = Especialidades.valueOf(especialidadjson);
            Medico medico = new Medico(medicoJSON.getString("nombre"), medicoJSON.getInt("edad"), medicoJSON.getLong("dni"), medicoJSON.getLong("telefono"), medicoJSON.getString("contrasenia"), especialidad, medicoJSON.getInt("id"));

            Turno turno = new Turno(fecha, medico, paciente, motivo, idTurno);
            turnos.add(turno);

        }

        Agenda agenda = new Agenda("Agenda turnos", turnos);

        return agenda;
    }

    public static boolean mostrarTurnosDeUnMedico(List<Turno> turnos, int idMedico) {
        boolean hay = false;
        for (Turno a : turnos) {
            if (a.getMedico().getId() == idMedico) {
                Agenda.mostrarUnTurno(a);
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("No tiene ningun turno.");
        }
        return hay;
    }

    public static List<Turno> reprogramarTurno(List<Turno> turnos, int idAreprogramar, int idMedico) {
        Iterator<Turno> iterator = turnos.iterator();
        while (iterator.hasNext()) {
            Turno t = iterator.next();
            if (idAreprogramar == t.getIdTurno()) {
                System.out.println("Ingrese la fecha actualizada");
                LocalDateTime fecha = Turno.verificarFecha(turnos, idMedico);
                t.setFecha(fecha);
            }
        }
        return turnos;
    }

    public static List<Turno> manejoReprogramacion(Medico medico, Scanner scan, Agenda agenda) throws JSONException {
        if (mostrarTurnosDeUnMedico(agenda.getAgenda(), medico.getId())) {
            System.out.println("\nIngrese el id del turno a reprogramar: ");
            int idDeTurnoAreprogramar = scan.nextInt();
            agenda.setAgenda(LeerArchivoAgenda.reprogramarTurno(agenda.getAgenda(), idDeTurnoAreprogramar, medico.getId()));
            GrabarJSONAgenda.llenarAgenda(agenda);
        }
        return agenda.getAgenda();
    }

}
