package ph.coreproc.android.kitchenmaterial.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.kosalgeek.android.photoutil.CameraPhoto;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ph.coreproc.android.kitchenmaterial.R;
import ph.coreproc.android.kitchenmaterial.utils.UiUtil;

public class QrCodeReaderActivity extends AppCompatActivity {

    @Bind(R.id.cameraPreview)
    ImageView cameraPreview;

    @Bind(R.id.qrdecoderview)
    QRCodeReaderView qrCodeReaderView;

    Context mContext;
    String mQrResult = "";
    int CAMERA_PREVIEW = 0;

    final int CAMERA_REQUEST = 1100;
    CameraPhoto cameraPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_reader);

        ButterKnife.bind(this);
        mContext = this;

        setUpQrScanner();

    }

    private void setUpQrScanner() {

        final TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(new QRCodeReaderView.OnQRCodeReadListener() {
            @Override
            public void onQRCodeRead(String text, PointF[] points) {
                mQrResult = text;
                // resultTextView.setText(text);
                qrCodeReaderView.stopCamera();
                startCameraCapture();
            }
        });
        qrCodeReaderView.setQRDecodingEnabled(true);
        qrCodeReaderView.setAutofocusInterval(2000L);
        qrCodeReaderView.setTorchEnabled(true);
        qrCodeReaderView.setBackCamera();

    }

    @OnClick(R.id.cameraPreview)
    public void cameraPreviewOnClick(View view) {
        ImageView imageView = (ImageView) view;

        if (CAMERA_PREVIEW == 0) {
            // Front Camera, so we switch to back
            qrCodeReaderView.setBackCamera();
            qrCodeReaderView.stopCamera();
            qrCodeReaderView.startCamera();
            CAMERA_PREVIEW = 1;
            imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_camera_rear_white_48dp));
        } else {
            qrCodeReaderView.setFrontCamera();
            qrCodeReaderView.stopCamera();
            qrCodeReaderView.startCamera();
            CAMERA_PREVIEW = 0;
            imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_camera_front_white_48dp));
        }
    }

    private void startCameraCapture() {
        cameraPhoto = new CameraPhoto(getApplicationContext());
        try {
            startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);
        } catch (IOException e) {
            UiUtil.showMessageDialog(getSupportFragmentManager(), e.getMessage());
        }
        cameraPhoto.addToGallery();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {

                try {
                    String photoPath = cameraPhoto.getPhotoPath();
                    // Bitmap bitmap = PhotoLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                    //    updateProfilePhoto(photoPath);
                    // upload photo


                } catch (Exception e) {
                    UiUtil.showMessageDialog(getSupportFragmentManager(), "Please try again");
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }
}
