package ph.coreproc.android.kitchenmaterial;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Base64;

import com.kosalgeek.android.photoutil.PhotoLoader;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

/**
 * Created by willm on 3/2/2017.
 */

public class BundyAndroidBase {

    // Image processing

    public static String encodeImage(String path) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = null;
        Bitmap bm = null;
        try {
            bm = PhotoLoader.init().from(path).requestSize(512, 512).getBitmap();
            bm = processImageOrientation(path, bm);
            bm.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            bm.recycle();
            b = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new String(Base64.encode(b, Base64.NO_WRAP), StandardCharsets.UTF_8);
    }

    public static Bitmap processImageOrientation(String photoPath, Bitmap bitmap) {
        try {
            ExifInterface ei = new ExifInterface(photoPath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);

            switch (orientation) {

                case ExifInterface.ORIENTATION_ROTATE_90:
                    return rotateImage(bitmap, 90);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return rotateImage(bitmap, 180);

                case ExifInterface.ORIENTATION_ROTATE_270:
                    return rotateImage(bitmap, 270);

                case ExifInterface.ORIENTATION_NORMAL:
                    return bitmap;
                default:
                    return bitmap;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public static ProgressDialog defaultProgressDialog (Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(message);
        progressDialog.setCancelable(false);
        return progressDialog;
    }


}
