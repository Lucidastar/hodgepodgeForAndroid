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
     * 初始化工具类
     * @param context  上下文
     * @param isShowLog  是否显示log
     * @param tag  log的tag
     */
    public static void init(Context context,boolean isShowLog,String tag) {
        Utils.context = context.getApplicationContext();
        setKLog(isShowLog,tag);
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

    private static void setKLog(boolean isShowLog,String tag){
        KLog.init(isShowLog, tag);
    }
}
