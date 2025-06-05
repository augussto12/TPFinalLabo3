package manejoJSON;

import Validaciones.Validar;
import clasesPersonas.Admin;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import clasesPersonas.Persona;
import extras.Especialidades;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeerArchivoPersonas {

    public static List<Persona> llenarPersonas() {

        List<Persona> listaPersonas = new ArrayList<>();
        try {
            JSONArray archivo = new JSONArray(JSONUtiles.leer("hospitalPersonas.json"));
            List<Persona> persona = new ArrayList<>();
            for (int i = 0; i < archivo.length(); i++) {
                JSONObject personajson = archivo.getJSONObject(i);

                if (personajson.has("especialidad")) {

                    int id = personajson.getInt("id");
                    long telefono = personajson.getLong("telefono");
                    String nombre = personajson.getString("nombre");
                    int edad = personajson.getInt("edad");
                    long dni = personajson.getLong("dni");
                    String contrasenia = personajson.getString("contrasenia");
                    String especialidadjson = personajson.getString("especialidad");

                    Especialidades especialidad = Especialidades.valueOf(especialidadjson);

                    Persona m = new Medico(nombre, edad, dni, telefono, contrasenia, especialidad, id);
                    listaPersonas.add(m);
                } else if (!personajson.has("id") && personajson.has("telefono")) {

                    long telefono = personajson.getLong("telefono");
                    String nombre = personajson.getString("nombre");
                    int edad = personajson.getInt("edad");
                    long dni = personajson.getLong("dni");
                    String contrasenia = personajson.getString("contrasenia");

                    Persona p = new Paciente(nombre, edad, dni, telefono, contrasenia);
                    listaPersonas.add(p);
                } else if (!personajson.has("telefono")) {

                    String nombre = personajson.getString("nombre");
                    long dni = personajson.getLong("dni");
                    String contrasenia = personajson.getString("contrasenia");

                    Persona a = new Admin(nombre, dni, contrasenia);
                    listaPersonas.add(a);
                }

            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return listaPersonas;
    }


    public static List<Medico> llenarlistamedicos() {
        List<Medico> listaMedicos = new ArrayList<>();
        try {
            JSONArray archivo = new JSONArray(JSONUtiles.leer("hospitalPersonas.json"));
            List<Medico> medicos = new ArrayList<>();

            for (int i = 0; i < archivo.length(); i++) {

                JSONObject personajson = archivo.getJSONObject(i);
                if (personajson.has("id")) {

                    int id = personajson.getInt("id");
                    long telefono = personajson.getLong("telefono");
                    String nombre = personajson.getString("nombre");
                    int edad = personajson.getInt("edad");
                    long dni = personajson.getLong("dni");
                    String contrasenia = personajson.getString("contrasenia");
                    String especialidadjson = personajson.getString("especialidad");

                    Especialidades especialidad = Especialidades.valueOf(especialidadjson);

                    Medico m = new Medico(nombre, edad, dni, telefono, contrasenia, especialidad, id);
                    medicos.add(m);
                }


            }
            listaMedicos = medicos;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return listaMedicos;
    }

    public static List<Paciente> llenarlistaPacientes() {
        List<Paciente> listaPacientes = new ArrayList<>();
        try {
            JSONArray archivo = new JSONArray(JSONUtiles.leer("hospitalPersonas.json"));
            List<Paciente> pacientes = new ArrayList<>();

            for (int i = 0; i < archivo.length(); i++) {
                JSONObject personajson = archivo.getJSONObject(i);
                int id = 0;
                if (!personajson.has("id") && personajson.has("telefono")) {

                    long telefono = personajson.getLong("telefono");
                    String nombre = personajson.getString("nombre");
                    int edad = personajson.getInt("edad");
                    Long dni = personajson.getLong("dni");
                    String contrasenia = personajson.getString("contrasenia");

                    Paciente p = new Paciente(nombre, edad, dni, telefono, contrasenia);
                    pacientes.add(p);
                }
                listaPacientes = pacientes;
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return listaPacientes;
    }

    public static List<Admin> llenarListaAdmin() {

        List<Admin> listaAdmin = new ArrayList<>();
        try {
            JSONArray archivo = new JSONArray(JSONUtiles.leer("hospitalPersonas.json"));
            List<Admin> admins = new ArrayList<>();

            for (int i = 0; i < archivo.length(); i++) {
                JSONObject personajson = archivo.getJSONObject(i);
                if (!personajson.has("telefono")) {

                    String nombre = personajson.getString("nombre");
                    Long dni = personajson.getLong("dni");
                    String contrasenia = personajson.getString("contrasenia");

                    Admin a = new Admin(nombre, dni, contrasenia);
                    admins.add(a);
                }
                listaAdmin = admins;
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return listaAdmin;
    }

    public static void mostrarListaMedicos(List<Medico> medicos) {
        int contador = 0;
        Scanner scan = new Scanner(System.in);
        int opcion = 0;
        System.out.println("[ 1 ] Ver medicos por especialidad");
        System.out.println("[ 2 ] Ver todos los medicos");
        System.out.printf("\nSu eleccion: ");
        opcion = Validar.validarSwitch(2);
        switch (opcion) {
            case 1:
                mostrarPorEspecialidad(medicos);
                break;
            case 2:
                mostrarTodosLosMedicos(medicos);
                break;
        }

    }

    public static void mostrarPorEspecialidad(List<Medico> medicos) {
        Scanner scan = new Scanner(System.in);
        int contador = 0;
        Especialidades especialidad = null;
        System.out.println("[ 1 ] Cardiologos");
        System.out.println("[ 2 ] Pediatras");
        System.out.println("[ 3 ] Clinicos");
        System.out.println("[ 4 ] Neurologos");
        System.out.printf("\nSu eleccion: ");
        int opcion = Validar.validarSwitch(4);

        switch (opcion) {
            case 1:
                especialidad = Especialidades.CARDIOLOGIA;
                break;
            case 2:
                especialidad = Especialidades.PEDIATRIA;
                break;
            case 3:
                especialidad = Especialidades.CLINICA;
                break;
            case 4:
                especialidad = Especialidades.NEUROLOGIA;
                break;
            default:
        }


        for (Medico medico : medicos) {
            if (medico.getEspecialidad() == especialidad) {
                contador++;
                mostrarUnMedico(medico, contador);
            }
        }
    }

    public static void mostrarTodosLosMedicos(List<Medico> medicos) {
        int contador = 0;

        for (Medico m : medicos) {
            contador++;
            mostrarUnMedico(m, contador);
        }
    }


    public static void mostrarUnMedico(Medico medico, int contador) {
        System.out.printf("\n------medico " + contador + "--------");
        System.out.printf("\nNombre: " + medico.getNombreYapellido());
        System.out.printf("\nEdad: " + medico.getEdad());
        System.out.printf("\nTelefono: " + medico.getTelefono());
        //System.out.printf("\nDni: " + medico.getNombreYapellido());
        System.out.printf("\nEspecialidad: " + medico.getEspecialidad());
        System.out.printf("\nId: " + medico.getId());
    }


}
