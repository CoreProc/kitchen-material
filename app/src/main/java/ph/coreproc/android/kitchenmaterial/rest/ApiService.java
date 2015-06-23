package ph.coreproc.android.kitchenmaterial.rest;

import java.util.List;

import ph.coreproc.android.kitchenmaterial.models.Contributor;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by johneris on 6/5/2015.
 */
public interface ApiService {

//    @GET("")
//    void getContributors(@Header("X-Authorization") String authorization, Callback<Contributor> callback);

//    @GET("")
//    void getContributors(Callback<com.google.gson.JsonObject> callback);

//    @POST("/users/new")
//    void createUser(@Body User user, Callback<User> cb);

//    @POST("/users/new")
//    void createUser(@Body TypedJsonString user, Callback<com.google.gson.JsonObject> cb);

    // MyModel m = new MyModel("some");
    // JsonObject jsonObject = ModelUtil.toJsonObject(m);
    // jsonObject.addProperty("key", "value you want to add");
    // RestClient.getApiService().myMethod(new TypedJsonString(jsonObject.toString()),
    //      new Callback<com.google.gson.JsonObject> callback);

    @GET("/repos/{user}/{repository}/contributors")
    void getContributors(
            @Path("user") String user,
            @Path("repository") String repository,
            Callback<List<Contributor>> callback
    );

}
