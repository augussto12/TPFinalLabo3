package manejoJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GrabarJSONAgenda {
    public static void llenarAgenda () throws JSONException {

        JSONArray archivo = new JSONArray();
        JSONObject turno = new JSONObject();

        turno.put("fecha", "14/07/2025 12:00");
        turno.put("dni medico", 22303313);//momentaneamente le pongo DNI, vas a ser un medico entero(osea un objeto)
        turno.put("dni paciente",45922938);//momentaneamente le pongo DNI, vas a ser un paciente(osea un objeto)
        turno.put("motivo", "me duele el dedo");
        archivo.put(turno);
        JSONUtiles.grabar(archivo);

    }
}
