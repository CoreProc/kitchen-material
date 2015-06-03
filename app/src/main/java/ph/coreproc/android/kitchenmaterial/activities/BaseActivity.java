package ph.coreproc.android.kitchenmaterial.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import ph.coreproc.android.kitchenmaterial.R;

/**
 * Created by johneris on 6/1/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResourceId());
        ButterKnife.inject(this);

        mContext = this;

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

    }

    protected abstract int getLayoutResourceId();

}
