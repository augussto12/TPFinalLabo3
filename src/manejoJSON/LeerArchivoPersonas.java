package manejoJSON;

import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import clasesPersonas.Persona;
import extras.Especialidades;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LeerArchivoPersonas {

    public static List<Medico> llenarlistamedicos() {
        List<Medico> listaMedicos = new ArrayList<>();
        try {
            JSONArray archivo = new JSONArray(JSONUtiles.leer("hospitalPersonas.json"));
            List<Medico> medicos = new ArrayList<>();

            for (int i = 0; i < archivo.length(); i++) {

                JSONObject personajson = archivo.getJSONObject(i);
                int id = 0;
                if(personajson.has("id")){

                    id = personajson.getInt("id");
                    long telefono = personajson.getLong("telefono");
                    String nombre = personajson.getString("nombre");
                    int edad = personajson.getInt("edad");
                    long dni = personajson.getLong("dni");
                    String especialidadjson = personajson.getString("especialidad");

                    Especialidades especialidad = Especialidades.valueOf(especialidadjson);

                    Medico m = new Medico(nombre, telefono, dni, edad,especialidad, id);
                    medicos.add(m);
                }

            }
            listaMedicos = medicos;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return listaMedicos;
    }
    public static List<Paciente> llenarlistaPacientes () {
        List<Paciente> listaPacientes = new ArrayList<>();
        try {
            JSONArray archivo = new JSONArray(JSONUtiles.leer("hospitalPersonas.json"));
            List<Paciente> pacientes = new ArrayList<>();

            for (int i = 0; i < archivo.length(); i++) {
                JSONObject personajson = archivo.getJSONObject(i);
               int id = 0;
                if (!personajson.has("id")) {

                    long telefono = personajson.getLong("telefono");
                    String nombre = personajson.getString("nombre");
                    int edad = personajson.getInt("edad");
                    long dni = personajson.getLong("dni");
                    //String contrasenia = personajson.getString("contrasenia");

                    Paciente p = new Paciente(nombre, telefono, dni, edad);
                    pacientes.add(p);
                }
                listaPacientes = pacientes;
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return listaPacientes;
    }

    public static void mostrarListaMedicos (List<Medico> medicos) {
        int contador = 0;
        for (Medico p : medicos) {
            contador++;
            System.out.printf("\n------medico " + contador + "--------");
            System.out.printf("\nnombre: " + p.getNombreYapellido());
            System.out.printf("\nedad: " + p.getEdad());
            System.out.printf("\ntelefono: " + p.getTelefono());
            System.out.printf("\ndni: " + p.getNombreYapellido());
            System.out.printf("\nespecialidad: "+p.getEspecialidad());
            System.out.printf("\nid: "+p.getId());
        }
    }
    public static void mostrarListaPacientes (List<Paciente> pacientes) {
        int contador = 0;
        for (Paciente p : pacientes) {
            contador++;
            System.out.printf("\n------paciente " + contador + "--------");
            System.out.printf("\nnombre: " + p.getNombreYapellido());
           // System.out.printf("\ncontrasenia: " + p.getContrasenia());
            System.out.printf("\nedad: " + p.getEdad());
            System.out.printf("\ntelefono: " + p.getTelefono());
            System.out.printf("\ndni: " + p.getNombreYapellido());
        }
    }

}
