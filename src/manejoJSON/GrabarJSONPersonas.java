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

    public static List<Paciente> agregarUnPaciente(Paciente nuevoPaciente) throws JSONException {
        if (nuevoPaciente == null) {
            throw new IngresoInvalidoException("El paciente no puede ser nulo");
        }
        List<Paciente> pacientes = LeerArchivoPersonas.llenarlistaPacientes();
        pacientes.add(nuevoPaciente);
        return pacientes;
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

    public static List<Paciente> registrarPaciente(List<Paciente> pacientes) throws JSONException {
        Paciente paciente = Paciente.pedirDatosDeRegistroAlPaciente(pacientes);
        pacientes = agregarUnPaciente(paciente);
        agregarPacienteAlJSON(paciente);
        return pacientes;
    }


    public static List<Medico> agregarUnMedico(Medico nuevoMedico) throws JSONException {
        if (nuevoMedico == null) {
            throw new IngresoInvalidoException("El medico no puede ser nulo");
        }
        List<Medico> medicos = LeerArchivoPersonas.llenarlistamedicos();
        medicos.add(nuevoMedico);
        return medicos;
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

    public static List<Medico> registrarMedico(List<Medico> medicos) throws JSONException {
        Medico medico = Medico.pedirDatosParaRegistrarMedico(medicos);
        medicos = agregarUnMedico(medico);
        agregarMedicoAlJSON(medico);
        return medicos;
    }

    public static void eliminarMedico(int idAeliminar) throws JSONException {
        List<Persona> personas = LeerArchivoPersonas.llenarPersonas();

        Iterator<Persona> iterator = personas.iterator();
        boolean eliminado = false;

        while (iterator.hasNext()) {
            Persona persona = iterator.next();

            if (persona instanceof Medico) {
                Medico medico = (Medico) persona;

                if (medico.getId() == idAeliminar) {
                    iterator.remove();
                    eliminado = true;
                    System.out.println("\nSe eliminó con éxito al médico con ID: " + idAeliminar);
                    GrabarJSONAgenda.eliminarTurnosDelMedico(idAeliminar);
                    break;
                }
            }
        }

        if (!eliminado) {
            System.out.println("\nNo se encontró un médico con ID: " + idAeliminar);
        }

        grabarPersonas(personas);
    }

    public static void eliminarPaciente(long dniAeliminar) throws JSONException {
        List<Persona> personas = LeerArchivoPersonas.llenarPersonas();
        Iterator<Persona> iterator = personas.iterator();
        boolean eliminado = false;

        while (iterator.hasNext()) {
            Persona persona = iterator.next();

            if (persona instanceof Paciente) {
                Paciente paciente = (Paciente) persona;

                if (paciente.getDni() == (dniAeliminar)) {
                    iterator.remove();
                    eliminado = true;
                    System.out.println("\nSe eliminó con éxito al paciente con dni: " + dniAeliminar);
                    GrabarJSONAgenda.eliminarTurnosDelPaciente(dniAeliminar);
                    break;
                }
            }
        }

        if (!eliminado) {
            System.out.println("\nNo se encontró un médico con ID: " + dniAeliminar);
        }

        grabarPersonas(personas);
    }

}






