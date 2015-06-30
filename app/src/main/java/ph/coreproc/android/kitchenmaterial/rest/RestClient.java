package ph.coreproc.android.kitchenmaterial.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by johneris on 6/5/2015.
 */
public class RestClient {

    public enum BaseUrlMode {
        LIVE, DEV
    }

    private static final String BASE_URL_LIVE = "https://api.github.com";
    private static final String BASE_URL_DEV = "https://api.github.com";
    private static BaseUrlMode baseUrlMode;

    private static ApiService apiService;

    static {
        setupRestClient(BASE_URL_DEV);
    }

    private RestClient() {}

    private static void setupRestClient(String baseUrl) {
        if(baseUrl.equals(BASE_URL_LIVE)) {
            baseUrlMode = BaseUrlMode.LIVE;
        } else if(baseUrl.equals(BASE_URL_DEV)) {
            baseUrlMode = BaseUrlMode.DEV;
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(ApiService.class);
    }

    public static void switchToDevMode() {
        setupRestClient(BASE_URL_DEV);
    }

    public static void switchToLiveMode() {
        setupRestClient(BASE_URL_LIVE);
    }

    public static BaseUrlMode getBaseUrlMode() {
        return baseUrlMode;
    }

    public static ApiService getAPIService() {
        return apiService;
    }

}
