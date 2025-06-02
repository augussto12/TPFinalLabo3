package manejoJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GrabarJSONMedicos {

    public static void llenarMedicos () throws JSONException {

        JSONArray archivo = new JSONArray();
        JSONObject medico = new JSONObject();

        medico.put("nombre", "hugo hernandez");
        medico.put("edad", 34);
        medico.put("telefono",223504976);
        medico.put("dni",22303313);

        archivo.put(medico);
        JSONUtiles.grabar(archivo);

    }


}
