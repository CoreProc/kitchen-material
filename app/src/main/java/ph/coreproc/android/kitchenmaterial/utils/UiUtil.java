package ph.coreproc.android.kitchenmaterial.utils;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import ph.coreproc.android.kitchenmaterial.dialogs.MessageDialogFragment;

/**
 * UiUtil is a collection of static methods that
 * are commonly used in user interface
 *
 * @author johneris
 */
public class UiUtil {

    public static void showToastShort(Context context, String message) {
        showToast(context, message, Toast.LENGTH_SHORT);
    }

    public static void showToastLong(Context context, String message) {
        showToast(context, message, Toast.LENGTH_LONG);
    }

    private static void showToast(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    public static void showMessageDialog(FragmentManager fragmentManager, String message) {
        MessageDialogFragment messageDialogFragment = MessageDialogFragment
                .newInstance(message);
        messageDialogFragment.show(fragmentManager, MessageDialogFragment.TAG);
    }

    public static void showMessageDialog(FragmentManager fragmentManager,
                                         String title, String message) {
        MessageDialogFragment messageDialogFragment = MessageDialogFragment
                .newInstance(title, message);
        messageDialogFragment.show(fragmentManager, MessageDialogFragment.TAG);
    }

    public static void showMessageDialog(FragmentManager fragmentManager,
                                         String title, String message, int iconRes) {
        MessageDialogFragment messageDialogFragment = MessageDialogFragment
                .newInstance(title, message, iconRes);
        messageDialogFragment.show(fragmentManager, MessageDialogFragment.TAG);
    }

}
