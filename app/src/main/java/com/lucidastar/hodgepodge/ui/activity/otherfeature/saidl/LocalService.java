package com.lucidastar.hodgepodge.ui.activity.otherfeature.saidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by qiuyouzone on 2017/5/27.
 */

public class LocalService extends Service {

    private Random mRandom = new Random();
    int a = 0;
    private LocalBinder mBinder = new LocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(2000);
                        a++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public class LocalBinder extends Binder {
        LocalService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LocalService.this;
        }
    }

    public int getRandomNum(){

        //每次都会创建一个线程

//        return mRandom.nextInt(20);
        return a;
    }


}
