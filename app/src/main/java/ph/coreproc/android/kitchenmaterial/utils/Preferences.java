package ph.coreproc.android.kitchenmaterial.utils;

import android.content.Context;
import android.content.SharedPreferences;

import ph.coreproc.android.kitchenmaterial.rest.RestClient;

/**
 * Created by johneris on 6/16/2015.
 */
public class Preferences {

    private static String PACKAGE = "ph.coreproc.android";

    /**
     * Keys used to get and put values
     */
    public final static String API_KEY = "API_KEY";
    public final static String BASE_URL_MODE = "BASE_URL_MODE";



    public static void setApiKey(Context context, String apiKey) {
        SharedPreferences prefs = context.getSharedPreferences(PACKAGE, Context.MODE_PRIVATE);
        prefs.edit().putString(API_KEY, apiKey).commit();
    }

    public static String getApiKey(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PACKAGE, Context.MODE_PRIVATE);
        return prefs.getString(API_KEY, "");
    }

    public static void setBaseUrlMode(Context context, RestClient.BaseUrlMode baseUrlMode) {
        SharedPreferences prefs = context.getSharedPreferences(PACKAGE, Context.MODE_PRIVATE);
        prefs.edit().putString(BASE_URL_MODE, baseUrlMode.toString()).commit();
    }

    public static RestClient.BaseUrlMode getBaseUrlMode(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PACKAGE, Context.MODE_PRIVATE);
        String baseUrlMode = prefs.getString(BASE_URL_MODE, "");
        return baseUrlMode.equals(RestClient.BaseUrlMode.LIVE) ?
                RestClient.BaseUrlMode.LIVE : RestClient.BaseUrlMode.DEV;
    }

}