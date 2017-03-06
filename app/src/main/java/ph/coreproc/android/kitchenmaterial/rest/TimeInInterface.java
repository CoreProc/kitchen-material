package ph.coreproc.android.kitchenmaterial.rest;

import com.google.gson.JsonObject;

import java.util.List;

import ph.coreproc.android.kitchenmaterial.models.Contributor;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit2.Call;
import retrofit2.http.Body;

/**
 * Created by willm on 3/2/2017.
 */

public interface TimeInInterface {

    @POST("/repos/")
    Call<JsonObject> timeInUser(
            @Body JsonObject jsonObject
    );

}
