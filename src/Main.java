import clasesManejoTurnos.Agenda;
import clasesManejoTurnos.Turno;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import manejoJSON.GrabarJSONAgenda;
import manejoJSON.GrabarJSONPersonas;
import manejoJSON.LeerArchivoPersonas;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main (String [] args){
        List<Medico> medicos = new ArrayList<>();
        List<Paciente> pacientes = new ArrayList<>();
        medicos = LeerArchivoPersonas.llenarlistamedicos();
        pacientes = LeerArchivoPersonas.llenarlistaPacientes();

        LeerArchivoPersonas.mostrarListaPacientes(pacientes);
        LeerArchivoPersonas.mostrarListaMedicos(medicos);
    }
}