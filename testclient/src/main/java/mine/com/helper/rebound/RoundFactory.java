package mine.com.helper.rebound;

import android.view.View;

import com.facebook.rebound.SpringSystem;

/**
 * Created by qiuyouzone on 2018/3/22.
 */

public final class RoundFactory {
    public static final SpringSystem RoundH = SpringSystem.create();

    private RoundFactory() {
    }

    public static RoundC d(View v, float b) {
        return new RoundC(v, RoundH.createSpring(), b);
    }

    public static RoundD f(View v, float b) {
        return new RoundD(v, RoundH.createSpring(), b);
    }

    public static RoundC c(View v, float b) {
        return new RoundC(v, RoundH.createSpring(), b);
    }

    public static RoundD e(View v, float b) {
        return new RoundD(v, RoundH.createSpring(), b);
    }

    public static RoundE h(View v, int y) {
        return new RoundE(v, RoundH.createSpring(), y);
    }

    public static RoundB b(View v, int x) {
        return new RoundB(v, RoundH.createSpring(), x);
    }
}
