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
                    String dni = personajson.getString("dni");
                    String contrasenia = personajson.getString("contrasenia");
                    String especialidadjson = personajson.getString("especialidad");

                    Especialidades especialidad = Especialidades.valueOf(especialidadjson);

                    Medico m = new Medico(nombre,edad,dni,telefono,contrasenia,especialidad,id);
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
                    String dni = personajson.getString("dni");
                    String contrasenia = personajson.getString("contrasenia");

                    Paciente p = new Paciente(nombre,edad,dni,telefono,contrasenia);
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
            System.out.printf("\nNombre: " + p.getNombreYapellido());
            System.out.printf("\nEdad: " + p.getEdad());
            System.out.printf("\nTelefono: " + p.getTelefono());
            //System.out.printf("\nDni: " + p.getNombreYapellido());
            System.out.printf("\nEspecialidad: "+p.getEspecialidad());
            System.out.printf("\nId: "+p.getId());
        }
    }
    public static void mostrarListaPacientes (List<Paciente> pacientes) {
        int contador = 0;
        for (Paciente p : pacientes) {
            contador++;
            System.out.printf("\n------Paciente " + contador + "--------");
            System.out.printf("\nNombre: " + p.getNombreYapellido());
           // System.out.printf("\ncontrasenia: " + p.getContrasenia());
            System.out.printf("\nEdad: " + p.getEdad());
            System.out.printf("\nTelefono: " + p.getTelefono());
            System.out.printf("\nDni: " + p.getNombreYapellido());
        }
    }

}
