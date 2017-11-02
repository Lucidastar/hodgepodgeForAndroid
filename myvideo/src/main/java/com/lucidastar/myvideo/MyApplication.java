package com.lucidastar.myvideo;

import android.app.Application;

import com.alivc.player.AliVcMediaPlayer;
import com.mine.lucidastarutils.utils.Utils;

import java.io.File;

/**
 * Created by qiuyouzone on 2017/11/1.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 检查/mnt/sdcard/TAOBAOPLAYER 是否存在,不存在创建
        File rootPath = new File("/mnt/sdcard/aliyun");
        if (!rootPath.exists()) {
            rootPath.mkdir();
        }

        final String businessId = "";
        AliVcMediaPlayer.init(getApplicationContext(), businessId);
        Utils.init(this);
    }
}
