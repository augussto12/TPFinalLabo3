package manejoJSON;

import extras.Especialidades;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public class GrabarJSONPersonas {

    public static void llenarPersonas () throws JSONException {

        JSONArray archivo = new JSONArray();
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i<8;i++){
            JSONObject personaJSON = new JSONObject();
            System.out.printf("\nnombre:");
            String nombre = scan.nextLine();
            System.out.printf("\ndni:");
            long dni = scan.nextLong();
            System.out.printf("\ntel:");
            long telefono = scan.nextLong();
            System.out.printf("\nedasd:");
            int edad = scan.nextInt();
            Especialidades especialidad = null;
            System.out.printf("\n[ 1 ] cardiologia");
            System.out.printf("\n[ 2 ] pediatria");
            System.out.printf("\n[ 3 ] clinica");
            System.out.printf("\n[ 4 ] neurologia");
            System.out.printf("\nsu eleccion:");
            int eleccion = scan.nextInt();
            scan.nextLine();
            switch (eleccion){
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
            personaJSON.put("dni",dni);
            personaJSON.put("telefono",telefono);
            personaJSON.put("edad", edad);
            personaJSON.put("id", i);
            personaJSON.put("especialidad", especialidad);

            archivo.put(personaJSON);
            System.out.printf("\nse agrego");
        }
        for (int j = 0; j<3;j++){

            JSONObject personaJSON1 = new JSONObject();
            System.out.printf("\nnombre:");
            String nombre1 = scan.nextLine();
            System.out.printf("\ncontrasenia:");
            String contrasenia = scan.nextLine();
            System.out.printf("\ndni:");
            long dni1 = scan.nextLong();
            System.out.printf("\ntel:");
            long telefono1 = scan.nextLong();
            System.out.printf("\nedasd:");
            int edad1 = scan.nextInt();
            scan.nextLine();

            personaJSON1.put("nombre", nombre1);
            personaJSON1.put("contrasenia", contrasenia);
            personaJSON1.put("dni",dni1);
            personaJSON1.put("telefono",telefono1);
            personaJSON1.put("edad", edad1);

            archivo.put(personaJSON1);

        }
        JSONUtiles.grabar(archivo,"hospitalPersonas.json");

    }


}
