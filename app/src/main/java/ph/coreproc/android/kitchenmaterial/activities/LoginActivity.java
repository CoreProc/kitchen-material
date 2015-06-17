package ph.coreproc.android.kitchenmaterial.activities;

import android.os.Bundle;
import android.widget.EditText;

import butterknife.InjectView;
import butterknife.OnClick;
import ph.coreproc.android.kitchenmaterial.R;

/**
 * Created by johneris on 6/16/2015.
 */
public class LoginActivity extends BaseActivity {

    @InjectView(R.id.etUsername)
    EditText etUsername;

    @InjectView(R.id.etPassword)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.btnLogin)
    public void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        // do your Http request here
    }

}
