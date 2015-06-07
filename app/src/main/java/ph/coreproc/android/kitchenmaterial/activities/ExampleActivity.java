package ph.coreproc.android.kitchenmaterial.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import ph.coreproc.android.kitchenmaterial.R;
import ph.coreproc.android.kitchenmaterial.dialogs.ExampleDialogFragment;
import ph.coreproc.android.kitchenmaterial.dialogs.MessageDialogFragment;
import ph.coreproc.android.kitchenmaterial.models.Contributor;
import ph.coreproc.android.kitchenmaterial.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by johneris on 6/1/2015.
 */
public class ExampleActivity extends BaseActivity {

    @InjectView(R.id.etUser)
    EditText etUser;

    @InjectView(R.id.etRepository)
    EditText etRepository;

    @InjectView(R.id.btnGetContributors)
    Button btnGetContributors;

    @InjectView(R.id.tvResults)
    TextView tvResults;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_example;
    }



    @OnClick(R.id.btnGetContributors)
    public void getContributors() {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        String user = etUser.getText().toString();
        String repository = etRepository.getText().toString();

        RestClient.getAPIService().getContributors(user, repository, new Callback<List<Contributor>>() {
            @Override
            public void success(List<Contributor> contributors, Response response) {
                progressDialog.dismiss();

                tvResults.setText("");

                MessageDialogFragment messageDialogFragment = MessageDialogFragment.newInstance("Title", "message", R.drawable.ic_launcher);
                messageDialogFragment.show(getSupportFragmentManager(), "MessageDialogFragment");

                for(Contributor contributor : contributors) {
                    tvResults.setText(tvResults.getText() +
                            "Contributor: " + contributor.userName
                                    + " (" + contributor.contributions +  ")" +
                            "\n");
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();

                tvResults.setText("");

                Toast.makeText(mContext, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
