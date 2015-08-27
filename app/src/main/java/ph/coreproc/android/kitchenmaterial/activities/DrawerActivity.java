package ph.coreproc.android.kitchenmaterial.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.Bind;
import ph.coreproc.android.kitchenmaterial.R;

/**
 * Created by johneris on 8/26/15.
 */
public class DrawerActivity extends BaseActivity {

    @Bind(R.id.navigationView)
    NavigationView mNavigationView;

    @Bind(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_drawer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_account_box_black_48dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Toast.makeText(mContext, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawer(GravityCompat.START);

                switch (menuItem.getItemId()) {
                    case R.id.menuItemHome:
                        menuItemHomeSelected();
                        return true;
                }

                return false;
            }
        });
    }

    private void menuItemHomeSelected() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return false;
    }
}
