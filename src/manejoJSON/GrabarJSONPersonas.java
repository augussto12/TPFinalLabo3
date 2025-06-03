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
            System.out.printf("\nNombre:");
            String nombre = scan.nextLine();
            System.out.printf("\nDni:");
            long dni = scan.nextLong();
            scan.nextLine();
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

            personaJSON.put("Nombre", nombre);
            personaJSON.put("Dni",dni);
            personaJSON.put("Telefono",telefono);
            personaJSON.put("Edad", edad);
            personaJSON.put("Id", i);
            personaJSON.put("Especialidad", especialidad);
            personaJSON.put("Contrasenia",contrasenia);

            archivo.put(personaJSON);
            System.out.printf("\nSe agrego");
        }
        for (int j = 0; j<3;j++){

            JSONObject personaJSON1 = new JSONObject();
            System.out.printf("\nNombre:");
            String nombre1 = scan.nextLine();
            System.out.printf("\nContrasenia:");
            String contrasenia = scan.nextLine();
            System.out.printf("\nDni:");
            long dni1 = scan.nextLong();
            scan.nextLine();
            System.out.printf("\nTel:");
            long telefono1 = scan.nextLong();
            scan.nextLine();
            System.out.printf("\nEdasd:");
            int edad1 = scan.nextInt();
            scan.nextLine();



            personaJSON1.put("Nombre", nombre1);
            personaJSON1.put("Contrasenia", contrasenia);
            personaJSON1.put("Dni",dni1);
            personaJSON1.put("Telefono",telefono1);
            personaJSON1.put("Edad", edad1);

            archivo.put(personaJSON1);

        }
        JSONUtiles.grabar(archivo,"hospitalPersonas.json");

    }


}
