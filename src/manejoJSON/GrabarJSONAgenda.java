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
        turnoJSON.put("Fecha", turno.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        turnoJSON.put("Motivo", turno.getMotivo());

        Medico medico = turno.getMedico();
        JSONObject medicoJSON = new JSONObject();
        medicoJSON.put("Id", medico.getId());
        medicoJSON.put("Nombre", medico.getNombreYapellido());
        medicoJSON.put("Dni", medico.getDni());
        medicoJSON.put("Telefono", medico.getTelefono());
        medicoJSON.put("Edad", medico.getEdad());
        medicoJSON.put("Especialidad", medico.getEspecialidad().name());
        medicoJSON.put("Contrasenia",medico.getContrasenia());
        turnoJSON.put("Medico", medicoJSON);

        Paciente paciente = turno.getCliente();
        JSONObject pacienteJSON = new JSONObject();
        pacienteJSON.put("Nombre", paciente.getNombreYapellido());
        pacienteJSON.put("Dni", paciente.getDni());
        pacienteJSON.put("Telefono", paciente.getTelefono());
        pacienteJSON.put("Edad", paciente.getEdad());
        pacienteJSON.put("Contrasenia",paciente.getContrasenia());
        turnoJSON.put("Paciente", pacienteJSON);

        return  turnoJSON;
    }
}
