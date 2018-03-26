package mine.com.helper;

import mine.com.helper.scale.WindowLoadView;
import mine.com.helper.scale.WindowLoadViewImpl;

/**
 * Created by qiuyouzone on 2018/3/22.
 */

public class ScaleScreenHelperFactory {
    private static float t;
    private static int b;
    private static WindowLoadView i;

    private ScaleScreenHelperFactory() {
    }

    public static WindowLoadView getInstance() {
        return i;
    }

    public static void reset() {
        init(b, t);
    }

    public static void init(int s, float c) {
        b = s;
        t = c;
        i = new WindowLoadViewImpl(s, c);
    }
}
