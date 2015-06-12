package ph.coreproc.android.kitchenmaterial.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import ph.coreproc.android.kitchenmaterial.dialogs.MessageDialogFragment;

/**
 * UiUtil is a collection of static methods that
 * are commonly used in user interface.
 *
 * @author johneris
 */
public class UiUtil {

    /**
     * Show a toast with duration LENGHT_SHORT.
     *
     * @param context
     * @param message
     */
    public static void showToastShort(Context context, String message) {
        showToast(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * Show a toast with duration LENGTH_LONG.
     *
     * @param context
     * @param message
     */
    public static void showToastLong(Context context, String message) {
        showToast(context, message, Toast.LENGTH_LONG);
    }

    private static void showToast(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    /**
     * Show a Message Dialog with the specified message.
     *
     * @param fragmentManager
     * @param message
     */
    public static void showMessageDialog(FragmentManager fragmentManager, String message) {
        MessageDialogFragment messageDialogFragment = MessageDialogFragment
                .newInstance(message);
        messageDialogFragment.show(fragmentManager, MessageDialogFragment.TAG);
    }

    /**
     * Show a Message Dialog with the specified
     * title and message.
     *
     * @param fragmentManager
     * @param title
     * @param message
     */
    public static void showMessageDialog(FragmentManager fragmentManager,
                                         String title, String message) {
        MessageDialogFragment messageDialogFragment = MessageDialogFragment
                .newInstance(title, message);
        messageDialogFragment.show(fragmentManager, MessageDialogFragment.TAG);
    }

    /**
     * Show a Message Dialog with the specified
     * title, message, and icon.
     *
     * @param fragmentManager
     * @param title
     * @param message
     * @param iconRes
     */
    public static void showMessageDialog(FragmentManager fragmentManager,
                                         String title, String message, int iconRes) {
        MessageDialogFragment messageDialogFragment = MessageDialogFragment
                .newInstance(title, message, iconRes);
        messageDialogFragment.show(fragmentManager, MessageDialogFragment.TAG);
    }

    /**
     * Create an instance of ProgressDialog that is
     * not cancelable with the specified message.
     *
     * @param context
     * @param message
     * @return an instance of a ProgressDialog
     */
    public static ProgressDialog getProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        return progressDialog;
    }

}
