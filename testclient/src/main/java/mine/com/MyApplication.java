package mine.com;

import android.accessibilityservice.AccessibilityService;
import android.app.Application;

import mine.com.helper.AppInit;
import mine.com.helper.ScaleScreenHelperFactory;

/**
 * Created by qiuyouzone on 2018/3/22.
 */
@AppInit(name = "yueqiu", log = false, scale = 1.0f)
public class MyApplication extends Application {
    public static MyApplication INSTANCE;

    @Override
    public void onCreate() {
        INSTANCE = this;
        final AppInit a = (AppInit)this.getClass().getAnnotation(AppInit.class);
        ScaleScreenHelperFactory.init(a.width(), a.scale());
        super.onCreate();

    }
}
