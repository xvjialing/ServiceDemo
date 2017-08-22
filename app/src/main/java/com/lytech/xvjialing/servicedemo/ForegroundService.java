package com.lytech.xvjialing.servicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 前台service
 */
public class ForegroundService extends Service {
    private static final String TAG = ForegroundService.class.getSimpleName();

    private MyBinder mBinder=new MyBinder();


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");

        Notification notification=new Notification(R.mipmap.ic_launcher,"有通知到来",System.currentTimeMillis());
        Intent notificationIntent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);
        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    class MyBinder extends Binder {
        public void startDownload(){
            Log.d(TAG, "startDownload: ");
        }
    }
}
