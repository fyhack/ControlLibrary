package com.elclcd.orientation;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * @Description 
 * 屏幕传感服务
 *
 * @author elc_simayi
 * @date 2013-12-11 上午10:57:49 
 */
public class OrientationService extends Service {
    public final String TAG = "OrientationService";
    public static final String FLAG_VALUE = "flag_value";
    public static final int RESTFLAG = -9999 ; 
    private WindowManager manager;
    private View view;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Log.d(TAG, "====onCreate====");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "====onStart====");
        if(view == null){
            view = new View(getApplicationContext());
            manager.addView(view, new MyLayoutParams(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED));
        }
        
        int rotation = intent.getIntExtra(FLAG_VALUE, ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        
        if(rotation == RESTFLAG){
            manager.removeView(view);
            view = null; 
            Settings.System.putInt(getContentResolver(), "accelerometer_rotation", ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            return super.onStartCommand(intent, flags, startId);
        }
        
        boolean is = Settings.System.putInt(getContentResolver(), "accelerometer_rotation", rotation);
        Log.w(TAG, "settings ：" + is);

        manager.updateViewLayout(view, new MyLayoutParams(rotation));
        
        Log.i(TAG, "rotation:" + rotation);
        return super.onStartCommand(intent, flags, startId);
    }

}
