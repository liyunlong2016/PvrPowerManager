package com.pvrlance.pvrpowermanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 关机，必须签名
    public void shutDownClick(View v) {

        Log.i(TAG, "shutDownClick");
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (pm != null) {
            if (Build.VERSION.SDK_INT < 24) {
                try {
                    Method method = pm.getClass().getDeclaredMethod("shutdown", boolean.class, boolean.class);
                    method.invoke(pm, false, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    @SuppressLint("SoonBlockedPrivateApi")
                    Method method = pm.getClass().getDeclaredMethod("shutdown", boolean.class, String.class, boolean.class);
                    method.invoke(pm, false, null, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 重启，必须签名
    public void reBootClick(View v) {

        Log.i(TAG, "reBootClick");
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (pm != null) {
            pm.reboot("");
        }
    }

    public static void goToSleep(Context context) {
        PowerManager powerManager= (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        if(powerManager != null) {
            try {
                powerManager.getClass().getMethod("goToSleep", new Class[]{long.class}).invoke(powerManager, SystemClock.uptimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // 锁屏
    public void goToSleepClick(View v) {

        Log.i(TAG, "goToSleepClick");
        goToSleep(this);
    }

    public static void wakeUp(Context context) {
        PowerManager powerManager= (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        if(powerManager != null) {
            try {
                powerManager.getClass().getMethod("wakeUp", new Class[]{long.class}).invoke(powerManager, SystemClock.uptimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 锁屏
    public void wakeUpClick(View v) {

        Log.i(TAG, "wakeUpClick");
        goToSleep(this);
        Intent intent = new Intent(this, ScreenService.class);
        startService(intent);
    }
}
