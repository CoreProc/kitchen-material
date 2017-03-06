package ph.coreproc.android.kitchenmaterial.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.hardware.Camera;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coreproc.android.kitchen.models.APIError;
import com.coreproc.android.kitchen.utils.ErrorUtil;
import com.coreproc.android.kitchen.utils.KitchenRestClient;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.gson.JsonObject;
import com.kosalgeek.android.photoutil.CameraPhoto;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ph.coreproc.android.kitchenmaterial.BundyAndroidBase;
import ph.coreproc.android.kitchenmaterial.R;
import ph.coreproc.android.kitchenmaterial.rest.TimeInInterface;
import ph.coreproc.android.kitchenmaterial.utils.UiUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrCodeReaderActivity extends AppCompatActivity {

    // QR CODE SCANNING

    @Bind(R.id.rlQrCodeScan)
    RelativeLayout rlQrCodeScan;

    @Bind(R.id.cameraPreview)
    ImageView cameraPreview;

    @Bind(R.id.qrdecoderview)
    QRCodeReaderView qrCodeReaderView;


    Context mContext;
    String mQrResult = "";
    int CAMERA_PREVIEW = 0;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_reader);

        ButterKnife.bind(this);
        mContext = this;

        initialize();

    }

    private void initialize() {
        mProgressDialog = BundyAndroidBase.defaultProgressDialog(mContext, "Signing in...");
        resetScan();

    }

    private void setUpQrScanner() {

        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(new QRCodeReaderView.OnQRCodeReadListener() {
            @Override
            public void onQRCodeRead(String text, PointF[] points) {
                mQrResult = text;
                qrCodeReaderView.stopCamera();

                startActivity(new Intent(mContext, CaptureImageActivity.class));
                finish();
            }
        });
        qrCodeReaderView.setQRDecodingEnabled(true);
        qrCodeReaderView.setAutofocusInterval(2000L);
        qrCodeReaderView.setTorchEnabled(true);
        qrCodeReaderView.setBackCamera();

    }



    private void resetScan() {
        if (qrCodeReaderView != null) {
            qrCodeReaderView.stopCamera();
        }

        setUpQrScanner();
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
