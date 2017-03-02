package ph.coreproc.android.kitchenmaterial;

import com.ivankocijan.magicviews.MagicFont;
import com.ivankocijan.magicviews.MagicViews;

/**
 * Created by johneris on 6/5/2015.
 */
public class App extends com.activeandroid.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MagicViews.setFontFolderPath(this, "fonts");
    }

}
