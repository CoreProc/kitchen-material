package ph.coreproc.android.kitchenmaterial.rest;

import java.util.List;

import ph.coreproc.android.kitchenmaterial.models.Contributor;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by johneris on 6/5/2015.
 */
public interface ApiService {

//    @GET("")
//    void getContributors(@Header("X-Authorization") String authorization, Callback<Contributor> callback);

//    @POST("/users/new")
//    void createUser(@Body User user, Callback<User> cb);

    @GET("/repos/{user}/{repository}/contributors")
    void getContributors(
            @Path("user") String user,
            @Path("repository") String repository,
            Callback<List<Contributor>> callback
    );

}
