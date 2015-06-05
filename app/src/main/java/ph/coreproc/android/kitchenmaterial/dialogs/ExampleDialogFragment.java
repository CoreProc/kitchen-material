package ph.coreproc.android.kitchenmaterial.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import ph.coreproc.android.kitchenmaterial.R;

/**
 * Created by johneris on 5/27/2015.
 */
public class ExampleDialogFragment extends DialogFragment {

//    ExampleDialogFragment df = new ExampleDialogFragment();
//    df.show(getSupportFragmentManager(), "ExampleDialogFragment");

    private Context mContext;

    public static ExampleDialogFragment newInstance() {
        ExampleDialogFragment fragment = new ExampleDialogFragment();

//        Bundle args = new Bundle();
//        args.putString("key", "value");
//        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

//        String str = getArguments().getString("key");

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

//        View view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
//                .inflate(R.layout.layout_file, null);
//        builder.setView(view);

//        builder.setTitle(R.string.dialog_this_title);

        builder.setPositiveButton(R.string.dialog_action_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton(R.string.dialog_action_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }

}
