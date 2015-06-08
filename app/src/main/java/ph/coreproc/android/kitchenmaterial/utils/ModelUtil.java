package ph.coreproc.android.kitchenmaterial.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by johneris on 6/8/2015.
 */
public class ModelUtil {

    private static Gson gson;

    static {
        gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(Type type, String json) {
        return (T) gson.fromJson(json, type);
    }

}
