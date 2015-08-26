package ph.coreproc.android.kitchenmaterial.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Bind;
import ph.coreproc.android.kitchenmaterial.R;

/**
 * Created by johneris on 6/15/2015.
 */
public class ExampleFragment extends Fragment {

    private Context mContext;

    @Bind(R.id.tv)
    TextView tv;

    public static ExampleFragment newInstance() {
        ExampleFragment exampleFragment = new ExampleFragment();

//        Bundle args = new Bundle();
//        args.putString("key", "value");
//        fragment.setArguments(args);

        return exampleFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example, container, false);

//        Bundle bundle = getArguments();

        ButterKnife.bind(this, view);
        initialize();

        mContext = getActivity();

        return view;
    }

    private void initialize() {
        tv.setText("Hello");
    }

}
