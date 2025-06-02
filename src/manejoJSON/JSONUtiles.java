package manejoJSON;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONUtiles {

    public static void grabar(JSONArray array, String archivo) {
        try {
            FileWriter file = new FileWriter(archivo);
            file.write(array.toString(4));
            file.flush();
            file.close();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public static void grabar(JSONObject objeto, String archivo) {
        try (FileWriter file = new FileWriter(archivo)) {
            file.write(objeto.toString(4));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }


    public static JSONTokener leer(String archivo) {
        JSONTokener tokener = null;

        try {
            tokener = new JSONTokener(new FileReader(archivo));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tokener;
    }

}