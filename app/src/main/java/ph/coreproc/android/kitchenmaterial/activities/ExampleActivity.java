package ph.coreproc.android.kitchenmaterial.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import ph.coreproc.android.kitchenmaterial.R;
import ph.coreproc.android.kitchenmaterial.adapters.RVContributorAdapter;
import ph.coreproc.android.kitchenmaterial.fragments.ExampleFragment;
import ph.coreproc.android.kitchenmaterial.models.Contributor;
import ph.coreproc.android.kitchenmaterial.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by johneris on 6/1/2015.
 */
public class ExampleActivity extends BaseActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ExampleActivity.class);
        return intent;
    }

    @Bind(R.id.etUser)
    EditText etUser;

    @Bind(R.id.etRepository)
    EditText etRepository;

    @Bind(R.id.btnGetContributors)
    Button btnGetContributors;

    @Bind(R.id.rvContributors)
    RecyclerView rvContributors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialize();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_example;
    }

    private void initialize() {
        ExampleFragment exampleFragment = ExampleFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment, exampleFragment)
                .commit();

        rvContributors.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        rvContributors.setLayoutManager(llm);
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

//                MessageDialogFragment messageDialogFragment = MessageDialogFragment.newInstance("Title", "message", R.drawable.ic_launcher);
//                messageDialogFragment.show(getSupportFragmentManager(), "MessageDialogFragment");

                RVContributorAdapter rvContributorAdapter = new RVContributorAdapter(mContext, contributors);
                rvContributors.setAdapter(rvContributorAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                Toast.makeText(mContext, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
