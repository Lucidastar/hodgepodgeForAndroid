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

    private LocalBinder mBinder = new LocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        LocalService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LocalService.this;
        }
    }

    public int getRandomNum(){
        return mRandom.nextInt(20);
    }
}
