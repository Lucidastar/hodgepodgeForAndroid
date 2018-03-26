package mine.com.helper.rebound;

import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;

/**
 * Created by qiuyouzone on 2018/3/22.
 */

public abstract class RoundA {
    public View mView;
    public Spring mSpring;

    public RoundA(View view, Spring s) {
        this.mView = view;
        this.mSpring = s;
        this.mSpring.addListener(this.f());
    }

    public void d(double s) {
        this.mSpring.setEndValue(s);
    }

    public void c(double s) {
        this.mSpring.setCurrentValue(s);
    }

    protected abstract SimpleSpringListener f();
}
