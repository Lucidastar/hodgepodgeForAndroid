package mine.com.helper.rebound;

import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;

/**
 * Created by qiuyouzone on 2018/3/22.
 */

public class RoundD extends RoundA {
    private float mSimple;
    public RoundD(View v, Spring s, float a) {
        super(v, s);
        this.mSimple = a;
    }

    @Override
    protected SimpleSpringListener f() {
        return new SimpleSpringListener() {
            private float mWidth;
            private float mHeight;

            public void onSpringUpdate(Spring s) {
                this.mWidth = (float)s.getCurrentValue();
                this.mHeight = 1.0F - this.mWidth * RoundD.this.mSimple;
                RoundD.this.mView.setAlpha(this.mHeight);
            }
        };
    }
}
