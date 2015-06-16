package ph.coreproc.android.kitchenmaterial.utils;

import android.os.Environment;

/**
 * Helper to check if external storage is available and/or writable.
 */
public class StorageHelper {

    // Storage states
    private boolean externalStorageAvailable, externalStorageWritable;

    /**
     * Checks the external storage's state and saves it in member attributes.
     */
    private void checkStorage() {
        // Get the external storage's state
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // Storage is available and writeable
            externalStorageAvailable = externalStorageWritable = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            // Storage is only readable
            externalStorageAvailable = true;
            externalStorageWritable = false;
        } else {
            // Storage is neither readable nor writeable
            externalStorageAvailable = externalStorageWritable = false;
        }
    }

    /**
     * Checks the state of the external storage.
     *
     * @return True if the external storage is available, false otherwise.
     */
    public boolean isExternalStorageAvailable() {
        checkStorage();
        return externalStorageAvailable;
    }

    /**
     * Checks the state of the external storage.
     *
     * @return True if the external storage is writeable, false otherwise.
     */
    public boolean isExternalStorageWritable() {
        checkStorage();
        return externalStorageWritable;
    }

    /**
     * Checks the state of the external storage.
     *
     * @return True if the external storage is available and writeable, false
     * otherwise.
     */
    public boolean isExternalStorageAvailableAndWritable() {
        checkStorage();
        if (!externalStorageAvailable) {
            return false;
        } else if (!externalStorageWritable) {
            return false;
        } else {
            return true;
        }
    }

}