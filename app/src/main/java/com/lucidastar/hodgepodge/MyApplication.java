package com.lucidastar.hodgepodge;

import android.app.Application;

import com.mine.lucidastarutils.utils.Utils;

/**
 * Created by qiuyouzone on 2017/9/28.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
