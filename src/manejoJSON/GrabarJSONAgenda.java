package manejoJSON;

import clasesManejoTurnos.Agenda;
import clasesManejoTurnos.Turno;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GrabarJSONAgenda {
    public static void llenarAgenda (Agenda turnos) throws JSONException {

        JSONObject agenda = new JSONObject();
        JSONArray turnosjson = new JSONArray();

        for (Turno t : turnos.getAgenda()){

            JSONObject turnoJSON = new JSONObject();

            turnoJSON = llenarTurno(t);
            turnosjson.put(turnoJSON);

        }
        agenda.put("turnos",turnosjson);

        JSONUtiles.grabar(agenda,"hospitalAgenda.json");

    }
    public static JSONObject llenarTurno (Turno turno) throws JSONException {

        JSONObject turnoJSON = new JSONObject();
        turnoJSON.put("fecha", turno.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        turnoJSON.put("motivo", turno.getMotivo());

        Medico medico = turno.getMedico();
        JSONObject medicoJSON = new JSONObject();
        medicoJSON.put("id", medico.getId());
        medicoJSON.put("nombre", medico.getNombreYapellido());
        medicoJSON.put("dni", medico.getDni());
        medicoJSON.put("telefono", medico.getTelefono());
        medicoJSON.put("edad", medico.getEdad());
        medicoJSON.put("especialidad", medico.getEspecialidad().name());
        turnoJSON.put("medico", medicoJSON);

        Paciente paciente = turno.getCliente();
        JSONObject pacienteJSON = new JSONObject();
        pacienteJSON.put("nombre", paciente.getNombreYapellido());
        pacienteJSON.put("dni", paciente.getDni());
        pacienteJSON.put("telefono", paciente.getTelefono());
        pacienteJSON.put("edad", paciente.getEdad());
        turnoJSON.put("paciente", pacienteJSON);

        return  turnoJSON;
    }
}
