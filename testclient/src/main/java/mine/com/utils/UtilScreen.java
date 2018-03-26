package mine.com.utils;

import android.util.DisplayMetrics;
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
        WindowManager windowManager = (WindowManager) MyApplication.INSTANCE.getSystemService("window");
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }
}
