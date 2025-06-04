package manejoJSON;

import clasesManejoTurnos.Agenda;
import clasesManejoTurnos.Turno;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import clasesPersonas.Persona;
import extras.Especialidades;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GrabarJSONAgenda {
    public static void llenarAgenda(Agenda turnos) throws JSONException {


        JSONObject agenda = new JSONObject();
        JSONArray turnosjson = new JSONArray();

        for (Turno t : turnos.getAgenda()) {

            JSONObject turnoJSON = new JSONObject();

            turnoJSON = llenarTurno(t);
            turnosjson.put(turnoJSON);

        }
        agenda.put("turnos", turnosjson);

        JSONUtiles.grabar(agenda, "hospitalAgenda.json");

    }

    public static JSONObject llenarTurno(Turno turno) throws JSONException {

        JSONObject turnoJSON = new JSONObject();
        turnoJSON.put("fecha", turno.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        turnoJSON.put("motivo", turno.getMotivo());
        turnoJSON.put("idTurno", turno.getIdTurno());

        Medico medico = turno.getMedico();
        JSONObject medicoJSON = new JSONObject();
        medicoJSON.put("id", medico.getId());
        medicoJSON.put("nombre", medico.getNombreYapellido());
        medicoJSON.put("dni", medico.getDni());
        medicoJSON.put("telefono", medico.getTelefono());
        medicoJSON.put("edad", medico.getEdad());
        medicoJSON.put("especialidad", medico.getEspecialidad().name());
        medicoJSON.put("contrasenia", medico.getContrasenia());
        turnoJSON.put("medico", medicoJSON);

        Paciente paciente = turno.getCliente();
        JSONObject pacienteJSON = new JSONObject();
        pacienteJSON.put("nombre", paciente.getNombreYapellido());
        pacienteJSON.put("dni", paciente.getDni());
        pacienteJSON.put("telefono", paciente.getTelefono());
        pacienteJSON.put("edad", paciente.getEdad());
        pacienteJSON.put("contrasenia", paciente.getContrasenia());
        turnoJSON.put("paciente", pacienteJSON);

        return turnoJSON;
    }

    public static Agenda agregarUnTurno(Turno turno) throws JSONException {
        JSONObject archivo = new JSONObject(JSONUtiles.leer("hospitalAgenda.json"));

        Agenda turnos = new Agenda();
        JSONArray listadoTurnosJSON = archivo.getJSONArray("turnos");

        for (int i = 0; i < listadoTurnosJSON.length(); i++) {
            JSONObject turnoJSON = listadoTurnosJSON.getJSONObject(i);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime fecha = LocalDateTime.parse(turnoJSON.getString("fecha"), formatter);
            String motivo = turnoJSON.getString("motivo");
            int idTurno = turnoJSON.getInt("idTurno");

            JSONObject pacienteJSON = turnoJSON.getJSONObject("paciente");
            Paciente paciente = new Paciente(pacienteJSON.getString("nombre"), pacienteJSON.getInt("edad"), pacienteJSON.getString("dni"), pacienteJSON.getLong("telefono"), pacienteJSON.getString("contrasenia"));

            JSONObject medicoJSON = turnoJSON.getJSONObject("medico");
            String especialidadJSON = medicoJSON.getString("especialidad");
            Especialidades especialidad = Especialidades.valueOf(especialidadJSON);
            Medico medico = new Medico(medicoJSON.getString("nombre"), medicoJSON.getInt("edad"), medicoJSON.getString("dni"), medicoJSON.getLong("telefono"), medicoJSON.getString("contrasenia"), especialidad, medicoJSON.getInt("id"));

            Turno turnoNuevo = new Turno(fecha, medico, paciente, motivo, idTurno);
            turnos.getAgenda().add(turnoNuevo);

        }

        turnos.getAgenda().add(turno);

        return turnos;
    }

    public static void guardarEnjsonAgendaConUnTurnoNuevo(Turno turno) throws JSONException {
        Agenda agenda = agregarUnTurno(turno);
        llenarAgenda(agenda);
    }

    public static void eliminarTurnosDelPaciente(String dniPaciente) throws JSONException {
        Agenda agenda = LeerArchivoAgenda.LeerArchivo();
        int tiene = 0;
        for (Turno t : agenda.getAgenda()) {
            if (t.getCliente().getDni().equals(dniPaciente)) {
                Agenda.eliminarUnTurnoMio(t.getIdTurno(), agenda);
                tiene++;
            }
        }
        if(tiene == 0){
            System.out.println("El paciente no tiene turnos.");
        }
    }

    public static void eliminarTurnosDelMedico(int idMedico) throws JSONException {
        Agenda agenda = LeerArchivoAgenda.LeerArchivo();
        int tiene = 0;
        for (Turno t : agenda.getAgenda()) {
            if (t.getMedico().getId() == idMedico) {
                Agenda.eliminarUnTurnoMio(t.getIdTurno(), agenda);
                tiene++;
            }
        }
        if(tiene == 0){
            System.out.println("El medico no tiene turnos.");
        }
    }
}
