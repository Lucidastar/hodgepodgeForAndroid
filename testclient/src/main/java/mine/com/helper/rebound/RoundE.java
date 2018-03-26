package mine.com.helper.rebound;

import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringUtil;

/**
 * Created by qiuyouzone on 2018/3/22.
 */

public class RoundE extends RoundA {
    public int y;

    public RoundE(View v, Spring s, int y) {
        super(v, s);
        this.y = y;
    }
    @Override
    protected SimpleSpringListener f() {
        return new SimpleSpringListener() {
            public void onSpringUpdate(Spring s) {
                RoundE.this.mView.setTranslationY((float) SpringUtil.mapValueFromRangeToRange((double)((float)s.getCurrentValue()), 0.0D, 1.0D, 0.0D, (double)RoundE.this.y));
            }
        };
    }
}
