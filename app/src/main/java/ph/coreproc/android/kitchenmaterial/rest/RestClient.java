package ph.coreproc.android.kitchenmaterial.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by johneris on 6/5/2015.
 */
public class RestClient {

    private static final String BASE_URL = "https://api.github.com";
    private static ApiService apiService;

    static {
        setupRestClient();
    }

    private RestClient() {}

    private static void setupRestClient() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(ApiService.class);
    }

    public static ApiService getAPIService() {
        return apiService;
    }

}
