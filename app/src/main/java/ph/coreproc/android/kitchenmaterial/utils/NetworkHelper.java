package ph.coreproc.android.kitchenmaterial.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

/**
 * Helper to check network availability.
 * You can also track network availability change every milliseconds
 * using setTimeout(int timeout) and calling startNetworkAvailabilityChecking()
 * and overriding onNetworkAvailabilityChanged(boolean isNetworkAvailable)
 * to get the network state.
 *
 * IMPORTANT: You must stop network availability checking onDestroy()
 * of an Activity if you will be using startNetworkAvailabilityChecking().
 * To do that, call method stopNetworkAvailabilityChecking() in onDestroy().
 *
 * @author johneris
 */
public class NetworkHelper {

    private Handler mHandler;
    private boolean mIsRunning;

    private boolean mIsNetworkAvailable;
    private int mTimeout;

    private Context mContext;


    public NetworkHelper(final Context context) {
        this.mContext = context;

        mHandler = new Handler();
        mIsRunning = true;

        mIsNetworkAvailable = isNetworkAvailable();
        mTimeout = 1000;
    }


    /**
     * Override this method to get network state if network state changes.
     * To start network availability checking, call method startNetworkAvailabilityChecking()
     * and call stopNetworkAvailabilityChecking() onDestroy of your Activity.
     *
     * @param isNetworkAvailable
     */
    public void onNetworkAvailabilityChanged(boolean isNetworkAvailable) {
    }


    /**
     * Set the time that the helper checks network availability in milliseconds.
     * To start network availability checking, call method startNetworkAvailabilityChecking()
     * and call stopNetworkAvailabilityChecking() onDestroy of your Activity.
     *
     * @param timeout
     */
    public void setTimeout(int timeout) {
        this.mTimeout = timeout;
    }


    /**
     * Check if network is available.
     *
     * @return true if network is available, false otherwise.
     */
    public boolean isNetworkAvailable() {
        ConnectivityManager cn = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cn.getActiveNetworkInfo();
        boolean isNetworkAvailable;
        if (nf != null && nf.isConnected() == true) {
            isNetworkAvailable = true;
        } else {
            isNetworkAvailable = false;
        }
        return isNetworkAvailable;
    }


    /**
     * Start Network availability checking every milliseconds set in variable mTimeout
     * using method setTimeout(int timeout).
     *
     * CAUTION: You must stop network availability checking onDestroy() of an Activity.
     * To do that, call method stopNetworkAvailabilityChecking() in onDestroy().
     */
    public void startNetworkAvailabilityChecking() {
        mIsRunning = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mIsRunning) {
                    try {
                        Thread.sleep(mTimeout);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                boolean isNetworkAvailable = isNetworkAvailable();
                                if (isNetworkAvailable != NetworkHelper.this.mIsNetworkAvailable) {
                                    NetworkHelper.this.mIsNetworkAvailable = isNetworkAvailable;
                                    onNetworkAvailabilityChanged(NetworkHelper.this.mIsNetworkAvailable);
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    /**
     * Stop Network availability checking.
     * This method must be called onDestroy() of an Activity.
     */
    public void stopNetworkAvailabilityChecking() {
        mIsRunning = false;
    }

}
