package com.pvrlance.pvrpowermanager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.SystemClock;

public class ScreenService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Handler().postDelayed(new Runnable(){
			public void run() {
				wakeUp(ScreenService.this);
			}
		}, 10000);
		return super.onStartCommand(intent, flags, startId);
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

}
