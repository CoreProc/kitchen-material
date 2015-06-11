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
public class MessageDialogFragment extends DialogFragment {

    public static final String TAG = "MessageDialogFragment";

    private static final String ARGS_TITLE = "ARGS_TITLE";
    private static final String ARGS_MESSAGE = "ARGS_MESSAGE";
    private static final String ARGS_ICON = "ARGS_ICON";

    private Context mContext;

    public static MessageDialogFragment newInstance(String message) {
        return newInstance(null, message, 0);
    }

    public static MessageDialogFragment newInstance(String title, String message) {
        return newInstance(title, message, 0);
    }

    public static MessageDialogFragment newInstance(String title, String message, int icon) {
        MessageDialogFragment fragment = new MessageDialogFragment();

        Bundle args = new Bundle();
        if(title != null) args.putString(ARGS_TITLE, title);
        if(message != null) args.putString(ARGS_MESSAGE, message);
        if(icon != 0) args.putInt(ARGS_ICON, icon);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        Bundle bundle = getArguments();
        if(bundle.containsKey(ARGS_TITLE)) {
            builder.setTitle(bundle.getString(ARGS_TITLE));
        }
        if(bundle.containsKey(ARGS_MESSAGE)) {
            builder.setMessage(bundle.getString(ARGS_MESSAGE));
        }
        if(bundle.containsKey(ARGS_ICON)) {
            builder.setIcon(bundle.getInt(ARGS_ICON));
        }

        builder.setPositiveButton(R.string.dialog_action_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }

}
