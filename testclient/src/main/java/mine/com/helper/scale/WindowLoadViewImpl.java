package mine.com.helper.scale;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import mine.com.helper.rebound.RoundAll;

/**
 * Created by qiuyouzone on 2018/3/22.
 * 对应c
 */

public class WindowLoadViewImpl extends WindowScale implements WindowLoadView {

    private WindowAttributeMatch mWindowAttributeMatch = new WindowAttributeMatch();
    private float c;
    private int d;

    public WindowLoadViewImpl(int s, float a) {
        this.d = s;
        this.c = a;
    }

    @Override
    public View loadViewMinMax(View v, int nw, int nh, int xw, int xh) {
        try {
            if(nw != 0) {
                v.getClass().getMethod("setMinWidth", new Class[]{Integer.TYPE}).invoke(v, new Object[]{Integer.valueOf(this.getWidthHeight(nw))});
            }

            if(nh != 0) {
                v.getClass().getMethod("setMinHeight", new Class[]{Integer.TYPE}).invoke(v, new Object[]{Integer.valueOf(this.getWidthHeight(nw))});
            }

            if(xw != 0) {
                v.getClass().getMethod("setMaxWidth", new Class[]{Integer.TYPE}).invoke(v, new Object[]{Integer.valueOf(this.getWidthHeight(nw))});
            }

            if(xh != 0) {
                v.getClass().getMethod("setMaxHeight", new Class[]{Integer.TYPE}).invoke(v, new Object[]{Integer.valueOf(this.getWidthHeight(nw))});
            }
        } catch (Exception var7) {
            ;
        }

        return v;
    }

    @Override
    public View loadViewPadding(View v, int l, int t, int r, int b) {
        try {
            v.setPadding(this.getWidthHeight(l), this.getWidthHeight(t), this.getWidthHeight(r), this.getWidthHeight(b));
        } catch (Exception var7) {
            ;
        }

        return v;
    }

    @Override
    public View loadViewMargin(View v, int l, int t, int r, int b) {
        try {
            ViewGroup.MarginLayoutParams m = (ViewGroup.MarginLayoutParams)v.getLayoutParams();
            m.leftMargin = this.getWidthHeight(l);
            m.topMargin = this.getWidthHeight(t);
            m.rightMargin = this.getWidthHeight(r);
            m.bottomMargin = this.getWidthHeight(b);
        } catch (Exception var7) {
            ;
        }

        return v;
    }

    @Override
    public View loadViewWidthHeightSize(View v, int w, int h, int s) {
        return this.loadViewSize(this.loadViewWidthHeight(v, w, h), s);
    }

    @Override
    public View loadViewWidthHeight(View v, int w, int h) {
        try {
            ViewGroup.MarginLayoutParams m = (ViewGroup.MarginLayoutParams)v.getLayoutParams();
            int pw = this.getWidthHeight(w);
            int ph = this.getWidthHeight(h);
            if(pw != 0) {
                m.width = pw;
            }

            if(ph != 0) {
                m.height = ph;
            }
        } catch (Exception var7) {
            ;
        }

        return v;
    }

    public View loadViewSize(View v, int s) {
        if(v instanceof TextView) {
            ((TextView)v).setTextSize(0, this.getSize(s));
        } else if(v instanceof EditText) {
            ((EditText)v).setTextSize(0, this.getSize(s));
        } else if(v instanceof Button) {
            ((Button)v).setTextSize(0, this.getSize(s));
        } else if(v instanceof CheckBox) {
            ((CheckBox)v).setTextSize(0, this.getSize(s));
        } else if(v instanceof RadioButton) {
            ((RadioButton)v).setTextSize(0, this.getSize(s));
        }

        return v;
    }

    @Override
    public View loadViewGroup(ViewGroup v) {
        RoundAll h = new RoundAll();
        View i = this.loadViewGroup(v, this.mWindowAttributeMatch, h);
        h.c();
        return i;
    }


    public int getWidthHeight(int v) {
        return (int)this.e((float)this.width, (float)this.d, (float)v);
    }


    public float getSize(int v) {
        return this.e((float)this.width, (float)this.d, (float)v) * this.c;
    }


    private View loadViewGroup(ViewGroup vg, WindowAttributeMatch a, RoundAll h) {
        a.d(this.f(vg.getTag()), vg, this, h);

        for(int i = 0; i < vg.getChildCount(); ++i) {
            View v = vg.getChildAt(i);
            if(v instanceof ViewGroup) {
                this.loadViewGroup((ViewGroup)v, a, h);
            } else {
                a.d(this.f(v.getTag()), v, this, h);
            }
        }

        return vg;
    }

    private float e(float v1, float v2, float v3) {
        return v2 > v3?v1 / (v2 / v3):v1 * (v3 / v2);
    }

    private String f(Object o) {
        try {
            return o.toString();
        } catch (Exception var3) {
            return "";
        }
    }
}
