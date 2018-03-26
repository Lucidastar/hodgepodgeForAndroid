package mine.com.helper.bound;

import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by qiuyouzone on 2018/3/22.
 */

public final class BoundViewHelper {
    private BoundViewHelper() {
    }

    public static View boundView(Object c, View cv) {
        Field[] fs = c.getClass().getDeclaredFields();
        if(fs != null && fs.length > 0) {
            Field[] var3 = fs;
            int var4 = fs.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Field f = var3[var5];
                BoundView b = (BoundView)f.getAnnotation(BoundView.class);
                if(b != null) {
                    View v = cv.findViewById(b.value());
                    if(b.isClick()) {
                        try {
                            v.setOnClickListener((View.OnClickListener)c);
                        } catch (Exception var11) {
                            ;
                        }
                    }

                    if(v != null) {
                        try {
                            f.setAccessible(true);
                            f.set(c, v);
                            f.setAccessible(false);
                        } catch (Exception var10) {
                            var10.printStackTrace();
                        }
                    }
                }
            }
        }

        return cv;
    }
}
