import clasesManejoTurnos.Agenda;
import clasesManejoTurnos.Turno;
import clasesPersonas.ListaMedicos;
import clasesPersonas.Medico;
import clasesPersonas.Paciente;
import manejoJSON.GrabarJSONAgenda;
import manejoJSON.GrabarJSONPersonas;
import manejoJSON.LeerArchivoAgenda;
import manejoJSON.LeerArchivoPersonas;
import menu.MenuPrincipal;
import org.json.JSONException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main (String [] args) throws JSONException {

        MenuPrincipal.menu();
        //List<Medico> medicos = new ArrayList<>();
        //medicos = LeerArchivoPersonas.llenarlistamedicos();
        //List<Paciente> pacientes = new ArrayList<>();



        //GrabarJSONPersonas.llenarPersonas();



    }
}