package mine.com.helper.rebound;

import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringUtil;

/**
 * Created by qiuyouzone on 2018/3/22.
 */

public class RoundB extends RoundA{

    private int x;

    public RoundB(View v, Spring s, int x) {
        super(v, s);
        this.x = x;
    }
    @Override
    protected SimpleSpringListener f() {
        return new SimpleSpringListener() {
            public void onSpringUpdate(Spring s) {
                RoundB.this.mView.setTranslationX((float) SpringUtil.mapValueFromRangeToRange((double)((float)s.getCurrentValue()), 0.0D, 1.0D, 0.0D, (double)RoundB.this.x));
            }
        };
    }
}
