package mine.com.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import mine.com.MyApplication;

/**
 * Created by qiuyouzone on 2018/3/22.
 */

public class UtilScreen {
    private UtilScreen() {
    }

    public static int[] screenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
//        WindowManager windowManager = (WindowManager) MyApplication.INSTANCE.getSystemService("window");
        WindowManager windowManager = (WindowManager) MyApplication.INSTANCE.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }
}
