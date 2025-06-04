package manejoJSON;

import Exceptions.PacienteInvalidoException;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import extras.Especialidades;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Scanner;

public class GrabarJSONPersonas {

    public static void llenarPersonas() throws JSONException {

        JSONArray archivo = new JSONArray();
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < 8; i++) {
            JSONObject personaJSON = new JSONObject();
            System.out.printf("\nNombre:");
            String nombre = scan.nextLine();
            System.out.printf("\nDni:");
            String dni = scan.nextLine();
            System.out.printf("\nTel:");
            long telefono = scan.nextLong();
            scan.nextLine();
            System.out.printf("\nEdad:");
            int edad = scan.nextInt();
            scan.nextLine();
            System.out.printf("\nContrasenia:");
            String contrasenia = scan.nextLine();
            Especialidades especialidad = null;
            System.out.printf("\n[ 1 ] Cardiologia");
            System.out.printf("\n[ 2 ] Pediatria");
            System.out.printf("\n[ 3 ] Clinica");
            System.out.printf("\n[ 4 ] Neurologia");
            System.out.printf("\nsu eleccion:");
            int eleccion = scan.nextInt();
            scan.nextLine();
            switch (eleccion) {
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
            }

            personaJSON.put("nombre", nombre);
            personaJSON.put("dni", dni);
            personaJSON.put("telefono", telefono);
            personaJSON.put("edad", edad);
            personaJSON.put("id", i + 1);
            personaJSON.put("especialidad", especialidad);
            personaJSON.put("contrasenia", contrasenia);

            archivo.put(personaJSON);
            System.out.printf("\nSe agrego");
        }
        for (int j = 0; j < 3; j++) {

            JSONObject personaJSON1 = new JSONObject();
            System.out.printf("\nNombre:");
            String nombre1 = scan.nextLine();
            System.out.printf("\nContrasenia:");
            String contrasenia = scan.nextLine();
            System.out.printf("\nDni:");
            String dni1 = scan.nextLine();
            System.out.printf("\nTel:");
            long telefono1 = scan.nextLong();
            scan.nextLine();
            System.out.printf("\nEdad:");
            int edad1 = scan.nextInt();
            scan.nextLine();


            personaJSON1.put("nombre", nombre1);
            personaJSON1.put("contrasenia", contrasenia);
            personaJSON1.put("dni", dni1);
            personaJSON1.put("telefono", telefono1);
            personaJSON1.put("edad", edad1);

            archivo.put(personaJSON1);

        }
        JSONUtiles.grabar(archivo, "hospitalPersonas.json");

    }


    public static List<Paciente> agregarUnPaciente(Paciente nuevoPaciente) throws JSONException {
        if (nuevoPaciente == null) {
            throw new PacienteInvalidoException("El paciente no puede ser nulo");
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
            throw new PacienteInvalidoException("El paciente no puede ser nulo");
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
            personaJSON.put("id",medico.getId());

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


}






