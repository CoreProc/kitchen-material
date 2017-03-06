package ph.coreproc.android.kitchenmaterial.activities;

import android.animation.Animator;
import android.app.ProgressDialog;
import android.content.Context;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coreproc.android.kitchen.models.APIError;
import com.coreproc.android.kitchen.utils.ErrorUtil;
import com.coreproc.android.kitchen.utils.KitchenRestClient;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.JsonObject;

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

public class CaptureImageActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    // CAMERA

    @Bind(R.id.rlCamera)
    RelativeLayout rlCamera;

    @Bind(R.id.svCamera)
    SurfaceView svCamera;

    @Bind(R.id.tvCounter)
    TextView tvCounter;

    ProgressDialog mProgressDialog;
    String imageLocation;
    int timerCamera = 3;

    Camera camera;
    SurfaceHolder surfaceHolder;
    Camera.PictureCallback rawCallback;
    Camera.ShutterCallback shutterCallback;
    Camera.PictureCallback jpegCallback;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);

        ButterKnife.bind(this);
        mContext = this;

    }

    private void initialize() {

        mProgressDialog = BundyAndroidBase.defaultProgressDialog(mContext, "Please wait...");
        resetCamera();
    }

    private void resetCamera() {
        if (camera != null) {
            stopCameraView();
        }

        surfaceHolder = svCamera.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        rawCallback = new Camera.PictureCallback() {
            public void onPictureTaken(byte[] data, Camera camera) {
                Log.d("Log", "onPictureTaken - raw");
            }
        };

        /** Handles data for jpeg picture */
        shutterCallback = new Camera.ShutterCallback() {
            public void onShutter() {
                Log.i("Log", "onShutter'd");
            }
        };
        jpegCallback = new Camera.PictureCallback() {
            public void onPictureTaken(byte[] data, Camera camera) {
                FileOutputStream outStream = null;
                imageLocation = "/sdcard/Pictures/" + System.currentTimeMillis() + ".jpg";
                try {
                    outStream = new FileOutputStream(imageLocation);
                    outStream.write(data);
                    outStream.close();
                    Log.d("Log", "onPictureTaken - wrote bytes: " + data.length);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
                Log.d("Log", "onPictureTaken - jpeg");

                UiUtil.showMessageDialog(getSupportFragmentManager(), "This is where we send now the shits. H3h3");
            }
        };


    }

    private void timeInUser(String path) {
        mProgressDialog.show();
        String encodedImg = "" + BundyAndroidBase.encodeImage(path);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("file", encodedImg);

        TimeInInterface timeInInterface = KitchenRestClient.create(mContext, TimeInInterface.class, true);
        Call<JsonObject> userResponseCall = timeInInterface.timeInUser(jsonObject);
        userResponseCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                mProgressDialog.hide();
                if (!response.isSuccessful()) {
                    APIError error = ErrorUtil.parsingError(response);
                    UiUtil.showMessageDialog(getSupportFragmentManager(), "An error occured. Please try again.");
                    finish();
                    return;
                }
                Toast.makeText(mContext, "Welcome, NAME HERE" + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                UiUtil.showMessageDialog(getSupportFragmentManager(), "An error occured. Please try again.");
            }
        });

    }

    private void captureImage() {
        // TODO Auto-generated method stub
        camera.takePicture(shutterCallback, rawCallback, jpegCallback);
    }

    private void stopCameraView()
    {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
        }
    }

    private void startCamera()
    {
        int cameraId = 0;
        try{

            for(int i=0;i<Camera.getNumberOfCameras();i++){
                Camera.CameraInfo info = new Camera.CameraInfo();
                Camera.getCameraInfo(i,info);
                if(info.facing== Camera.CameraInfo.CAMERA_FACING_FRONT){
                    cameraId = i;
                    break;
                }
            }
            camera=Camera.open(cameraId);
        }catch(RuntimeException e){
            Log.e("CAMERA ", "init_camera: " + e);
            return;
        }

        Camera.Parameters param;
        param = camera.getParameters();
        //modify parameter


        for (Camera.Size size : param.getSupportedPictureSizes()) {
            if (size.height  <= 1024 && size.width <= 768) {
                param.setPreviewSize(size.width, size.height);
                param.setPictureSize(size.width, size.height);
                break;
            }
        }

        param.setPreviewFrameRate(20);
        camera.setParameters(param);
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
            //camera.takePicture(shutter, raw, jpeg)
        } catch (Exception e) {
            Log.e("CAMERA", "init_camera: " + e);
            return;
        }

        // try to capture if ready
        startTimer();
    }
    Runnable runnable;
    private void startTimer() {
        tvCounter.setText("" + timerCamera);
        YoYo.with(Techniques.ZoomIn)
                .duration(1000)
                .playOn(tvCounter);
        runnable = new Runnable() {
            @Override
            public void run() {
                timerCamera -= 1;
                if (timerCamera == 0) {
                    captureImage();
                    return;
                }

                tvCounter.setText("" + timerCamera);
                YoYo.with(Techniques.ZoomIn)
                        .duration(1000)
                        .playOn(tvCounter);
                tvCounter.postDelayed(runnable, 1000);
            }
        };
        tvCounter.postDelayed(runnable, 1000);
    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        startCamera();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialize();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopCameraView();
    }
}
