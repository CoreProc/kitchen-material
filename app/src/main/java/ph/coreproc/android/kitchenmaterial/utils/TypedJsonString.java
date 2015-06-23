package ph.coreproc.android.kitchenmaterial.utils;

import retrofit.mime.TypedString;

/**
 * Created by johneris on 6/23/2015.
 */
public class TypedJsonString extends TypedString {

    public TypedJsonString(String body) {
        super(body);
    }

    @Override public String mimeType() {
        return "application/json";
    }

}
