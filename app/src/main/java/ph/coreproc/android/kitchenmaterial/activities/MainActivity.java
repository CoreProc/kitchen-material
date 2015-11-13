package ph.coreproc.android.kitchenmaterial.activities;

import android.content.Intent;
import android.os.Bundle;

import ph.coreproc.android.kitchenmaterial.R;

/**
 * Created by johneris on 6/1/2015.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = DrawerActivity.newIntent(mContext);
        startActivity(intent);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

}
