package com.lucidastar.hodgepodge.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.mine.lucidastarutils.log.KLog;

/**
 * Created by qiuyouzone on 2018/9/29.
 */

public class CustomViewTest extends View {

    private boolean isDrag = false;

    public CustomViewTest(Context context) {
        super(context);
    }

    public CustomViewTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:

                KLog.i("down---->" );
                break;
            case MotionEvent.ACTION_MOVE:
                isDrag = true;
                KLog.i("move---->" );

                break;
            case MotionEvent.ACTION_UP:
                isDrag = false;
                KLog.i("up---->");

                break;
        }
        return super.onTouchEvent(event);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        switch (event.getAction() & MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_DOWN:
//
//                KLog.i("dddown---->" );
//                break;
//            case MotionEvent.ACTION_MOVE:
//                isDrag = true;
//                KLog.i("ddmove---->" );
//
//                break;
//            case MotionEvent.ACTION_UP:
//                isDrag = false;
//                KLog.i("ddup---->");
//
//                break;
//        }
//        return super.onTouchEvent(event);
//    }


}
