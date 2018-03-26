package mine.com.helper.rebound;

import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;

/**
 * Created by qiuyouzone on 2018/3/22.
 */

public class RoundC extends RoundA {
    private float mFloat;
    public RoundC(View view, Spring s) {
        super(view, s);
    }


    public RoundC(View v, Spring s, float a) {
        super(v, s);
        this.mFloat = a;
    }

    @Override
    protected SimpleSpringListener f() {
        return new SimpleSpringListener() {
            private float mWidth;
            private float mHeight;

            public void onSpringUpdate(Spring s) {
                this.mWidth = (float)s.getCurrentValue();
                this.mHeight = 1.0F - this.mWidth * RoundC.this.mFloat;
                RoundC.this.mView.setScaleX(this.mHeight);
                RoundC.this.mView.setScaleY(this.mHeight);
            }
        };
    }
}
