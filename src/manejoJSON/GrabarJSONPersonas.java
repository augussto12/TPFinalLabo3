package manejoJSON;

import Exceptions.IngresoInvalidoException;
import Exceptions.IngresoInvalidoException;
import clasesManejoTurnos.Turno;
import clasesPersonas.Admin;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import clasesPersonas.Persona;
import extras.Especialidades;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GrabarJSONPersonas {

    public static void grabarPersonas(List<Persona> lista) throws JSONException {

        JSONArray archivo = new JSONArray();

        for (Persona p : lista) {
            JSONObject personajson = new JSONObject();

            if (p instanceof Medico) {

                personajson.put("id", ((Medico) p).getId());
                personajson.put("telefono", p.getTelefono());
                personajson.put("nombre", p.getNombreYapellido());
                personajson.put("edad", p.getEdad());
                personajson.put("dni", p.getDni());
                personajson.put("contrasenia", p.getContrasenia());
                personajson.put("especialidad", ((Medico) p).getEspecialidad());

            } else if (p instanceof Paciente) {

                personajson.put("telefono", p.getTelefono());
                personajson.put("nombre", p.getNombreYapellido());
                personajson.put("edad", p.getEdad());
                personajson.put("contrasenia", p.getContrasenia());
                personajson.put("dni", p.getDni());

            } else {
                personajson.put("contrasenia", p.getContrasenia());
                personajson.put("nombre", p.getNombreYapellido());
                personajson.put("dni", p.getDni());
            }
            archivo.put(personajson);
        }
        JSONUtiles.grabar(archivo, "hospitalPersonas.json");
    }


    public static void agregarPacienteAlJSON(Paciente paciente) {
        JSONObject personaJSON = new JSONObject();

        try {
            personaJSON.put("nombre", paciente.getNombreYapellido());
            personaJSON.put("contrasenia", paciente.getContrasenia());
            personaJSON.put("dni", paciente.getDni());
            personaJSON.put("telefono", paciente.getTelefono());
            personaJSON.put("edad", paciente.getEdad());

            JSONArray listadoPersonas = new JSONArray(JSONUtiles.leer("hospitalPersonas.json"));
            listadoPersonas.put(personaJSON);

            JSONUtiles.grabar(listadoPersonas, "hospitalPersonas.json");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


    public static void agregarMedicoAlJSON(Medico medico) {
        JSONObject personaJSON = new JSONObject();

        try {
            personaJSON.put("nombre", medico.getNombreYapellido());
            personaJSON.put("contrasenia", medico.getContrasenia());
            personaJSON.put("dni", medico.getDni());
            personaJSON.put("telefono", medico.getTelefono());
            personaJSON.put("edad", medico.getEdad());
            personaJSON.put("id", medico.getId());
            personaJSON.put("especialidad", medico.getEspecialidad().name());

            JSONArray listadoMedicos = new JSONArray(JSONUtiles.leer("hospitalPersonas.json"));
            listadoMedicos.put(personaJSON);

            JSONUtiles.grabar(listadoMedicos, "hospitalPersonas.json");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


}






