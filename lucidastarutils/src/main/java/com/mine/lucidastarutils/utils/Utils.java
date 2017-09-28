package com.mine.lucidastarutils.utils;

import android.content.Context;
import android.support.compat.BuildConfig;

import com.mine.lucidastarutils.log.KLog;

/**
 * Created by hanxiaoxing on 16/12/27.
 */

public class Utils {

    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
        KLog.init(true, "Lucidastar");
    }
    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

    public static Context getApp() {
        return context;
    }
}
