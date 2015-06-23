package ph.coreproc.android.kitchenmaterial.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

/**
 * ModelUtil contains static methods and variables
 * that are commonly used by a model.
 *
 * @author johneris
 */
public class ModelUtil {

    /**
     * Gson object that will be used for converting
     * POJOs into JSON string and vice-versa.
     */
    private static Gson gson;

    /**
     * Initialize the gson object using a GsonBuilder
     * excluding fields of POJOs without @Expose annotation
     * and with a date format that will be used in conversion.
     */
    static {
        gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .excludeFieldsWithoutExposeAnnotation()     // don't include fields without @Expose
                .create();
    }

    /**
     * Convert java object to JSON string using Gson.
     *
     * @param object a java object
     * @return the JSON string of the specified object
     */
    public static String toJsonString(Object object) {
        return gson.toJson(object);
    }

    /**
     * Convert java object to JsonObject.
     *
     * @param object a java object
     * @return the JsonObject of the specified object
     */
    public static JsonObject toJsonObject(Object object) {
        return gson.toJsonTree(object).getAsJsonObject();
    }

    /**
     * Convert a JSON string into an object according to
     * the specified type of object.
     *
     * @param type the type of POJO that the json string will be converted into
     * @param json JSON string that will be converted into a java object
     * @param <T>
     * @return the corresponding object from the specified JSON string and type
     */
    public static <T> T fromJson(Type type, String json) {
        return (T) gson.fromJson(json, type);
    }

}
