package com.elclcd.orientation;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.Service;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * @Description 屏幕传感服务
 * 
 * @author elc_simayi
 * @date 2013-12-11 上午10:57:49
 */
public class OrientationService extends Service {
    public final String TAG = "OrientationService";
    public static final String FLAG_VALUE = "flag_value";
    public static final int RESTFLAG = -9999;
    private WindowManager manager;
    private View window;

    private Handler handler = new Handler();

    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "====onCreate====");
        super.onCreate();
        manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new RefreshTask(), 0, 500);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "====onStart====");
        if (window == null) {
            window = new View(getApplicationContext());
            manager.addView(window, new MyLayoutParams(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED));
        }

        int rotation = intent.getIntExtra(FLAG_VALUE, ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        if (rotation == RESTFLAG) {
            MyWindowManager.createSmallWindow(getApplicationContext());
        }

        if (rotation == RESTFLAG) {
            manager.removeView(window);
            window = null;
            Settings.System.putInt(getContentResolver(), "accelerometer_rotation",
                    ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            return super.onStartCommand(intent, flags, startId);
        }

        boolean is = Settings.System.putInt(getContentResolver(), "accelerometer_rotation", rotation);
        Log.w(TAG, "settings ：" + is);

        manager.updateViewLayout(window, new MyLayoutParams(rotation));

        Log.i(TAG, "rotation:" + rotation);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timer = null;
    }

    class RefreshTask extends TimerTask {

        @Override
        public void run() {
            // 当前界面是桌面，且没有悬浮窗显示，则创建悬浮窗。
            if (isHome() && !MyWindowManager.isWindowShowing()) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        MyWindowManager.createSmallWindow(getApplicationContext());
                    }
                });
            }
            // 当前界面不是桌面，且有悬浮窗显示，则移除悬浮窗。
            else if (!isHome() && MyWindowManager.isWindowShowing()) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        MyWindowManager.removeSmallWindow(getApplicationContext());
                        MyWindowManager.removeBigWindow(getApplicationContext());
                    }
                });
            }
        }
    }

    private boolean isHome() {
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
        return getHomes().contains(rti.get(0).topActivity.getPackageName());
    }

    private List<String> getHomes() {
        List<String> names = new ArrayList<String>();
        PackageManager packageManager = this.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo ri : resolveInfo) {
            names.add(ri.activityInfo.packageName);
        }
        return names;
    }

}
