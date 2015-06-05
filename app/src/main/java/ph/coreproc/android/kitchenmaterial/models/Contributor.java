package ph.coreproc.android.kitchenmaterial.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 6/5/2015.
 */
@Table(name = "Contributors")
public class Contributor extends Model {

    /*
     * @Expose - expose the field to Gson
     * @SerializedName("") - name used by Gson
     * @Column(name = "") - column name used by ActiveAndroid
     */

    @Expose
    @SerializedName("login")
    @Column(name = "user_name")
    public String userName;

    @Expose
    @SerializedName("contributions")
    @Column(name = "contributions")
    public int contributions;

    public Contributor() {
        super();    // required by ActiveAndroid
    }

    public String toJson() {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(this);
        return json;
    }

    public Contributor fromJson(String json) {
        Gson gson = new GsonBuilder().create();
        Contributor contributor = gson.fromJson(json, Contributor.class);
        return contributor;
    }

}
