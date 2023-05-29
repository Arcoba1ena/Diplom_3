package api.functions;

import com.google.gson.Gson;

public class Util {
    public static String serialize(Object modelClass) {
        Gson gson = new Gson();
        return gson.toJson(modelClass);
    }

    public static <T> T deserialize(String json, Class<T> modelClass) {
        Gson gson = new Gson();
        return gson.fromJson(json, modelClass);
    }
}