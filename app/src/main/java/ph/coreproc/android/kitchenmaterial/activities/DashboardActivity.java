package ph.coreproc.android.kitchenmaterial.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapper;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ph.coreproc.android.kitchenmaterial.R;

public class DashboardActivity extends AppCompatActivity {

    @Bind(R.id.loadingPage)
    ImageView loadingPage;

    @Bind(R.id.tvCurrentTime)
    TextView tvCurrentTime;

    @Bind(R.id.tvCurrentDate)
    TextView tvCurrentDate;

    @Bind(R.id.timeIn)
    Button buttonTimeIn;

    @Bind(R.id.timeOut)
    Button buttonTimeOut;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mContext = this;
        ButterKnife.bind(this);

        Glide.with(mContext)
                .load(R.drawable.flash_loading)
                .asGif()
                .into(loadingPage);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingPage.setVisibility(View.GONE);
            }
        }, 1000);

        tvCurrentDate.setText(new SimpleDateFormat("MMMM dd, yyyy, EEE", Locale.US).format(new Date()));
        tvCurrentTime.setText(new SimpleDateFormat("HH:mm a", Locale.US).format(new Date()));

        final Handler handlerDate = new Handler(getMainLooper());
        handlerDate.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvCurrentDate.setText(new SimpleDateFormat("MMMM dd, yyyy, EEE", Locale.US).format(new Date()));
                handlerDate.postDelayed(this, 1000);
            }
        }, 5000);

        final Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvCurrentTime.setText(new SimpleDateFormat("hh:mm a", Locale.US).format(new Date()));
                handler.postDelayed(this, 1000);
            }
        }, 10);

    }

    @OnClick({R.id.timeIn, R.id.timeOut})
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
