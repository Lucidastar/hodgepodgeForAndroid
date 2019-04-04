package com.lucidastar.performanceanalysis;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by qiuyouzone on 2019/3/15.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initLeakCanary();
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
