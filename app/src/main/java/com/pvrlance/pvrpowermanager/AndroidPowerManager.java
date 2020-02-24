package com.pvrlance.pvrpowermanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import java.lang.reflect.Method;

public class AndroidPowerManager {

    private static final String TAG = "PvrPowerManager";

    public void androidShutDown(Context context) {

        Log.i(TAG, "androidShutDown");
        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
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

    public void androidReBoot(Context context) {

        Log.i(TAG, "androidReBoot");
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (pm != null) {
            pm.reboot("");
        }
    }

    public void androidGoToSleep(Context context) {

        Log.i(TAG, "androidGoToSleep");
        PowerManager powerManager= (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        if(powerManager != null) {
            try {
                powerManager.getClass().getMethod("goToSleep", new Class[]{long.class}).invoke(powerManager, SystemClock.uptimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void androidWakeUp(Context context) {

        Log.i(TAG, "androidwakeUp");
        PowerManager powerManager= (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        if(powerManager != null) {
            try {
                powerManager.getClass().getMethod("wakeUp", new Class[]{long.class}).invoke(powerManager, SystemClock.uptimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
