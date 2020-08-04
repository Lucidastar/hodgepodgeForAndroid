package com.lucidastar.hodgepodge.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mine.lucidastarutils.log.KLog;
import com.mine.lucidastarutils.utils.ScreenUtils;

/**
 * Created by qiuyouzone on 2018/9/29.
 */

public class DragFloatActionButton extends FloatingActionButton {

    private int screenWidth;
    private int screenHeight;
    private int screenWidthHalf;
    private int statusHeight;
    private int virtualHeight;

    public DragFloatActionButton(Context context) {
        super(context);
        init();
    }

    public DragFloatActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DragFloatActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        screenWidth = ScreenUtils.getScreenWidth(getContext());
        screenWidthHalf = screenWidth / 2;
        screenHeight = ScreenUtils.getScreenHeight(getContext());
        statusHeight = ScreenUtils.getStatusHeight(getContext());
        virtualHeight = ScreenUtils.getVirtualBarHeigh(getContext());
    }

    private int lastX;
    private int lastY;

    private boolean isDrag;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                setPressed(true);
                getParent().requestDisallowInterceptTouchEvent(true);//屏蔽父控件拦截事件
                lastX = rawX;
                lastY = rawY;
                KLog.i("getX=" + getX() + "；screenWidthHalf=" + screenWidthHalf);
                break;
            case MotionEvent.ACTION_MOVE:
                isDrag = true;
                //计算手指移动了多少
                int dx = rawX - lastX;
                int dy = rawY - lastY;
                //这里修复一些手机无法触发点击事件的问题
                int distance = (int) Math.sqrt(dx * dx + dy * dy);
                KLog.i("distance---->" + distance + "");
                if (distance < 3) {
                    isDrag = false;
                    break;
                }
                float x = getX() + dx;
                float y = getY() + dy;
                x = x < 0 ? 0 : x > screenWidth - getWidth() ? screenWidth - getWidth() : x;
                if (y < 0) {
                    y = 0;
                }
                if (y > screenHeight - statusHeight - getHeight()) {
                    y = screenHeight - statusHeight - getHeight();
                }
                setX(x);
                setY(y);
                lastX = rawX;
                lastY = rawY;
                KLog.i("move---->" + "getX=" + getX() + "；screenWidthHalf=" + screenWidthHalf + " " + isDrag + "  statusHeight=" + statusHeight + " virtualHeight" + virtualHeight + " screenHeight" + screenHeight + "  getHeight=" + getHeight() + " y" + y);
                break;
            case MotionEvent.ACTION_UP:
                if (isDrag) {
                    //恢复按压效果
                    setPressed(false);
                    KLog.i("ACTION_UP---->" + "getX=" + getX() + "；screenWidthHalf=" + screenWidthHalf);
                    if (rawX >= screenWidthHalf) {
                        animate().setInterpolator(new DecelerateInterpolator())
                                .setDuration(500)
                                .xBy(screenWidth - getWidth() - getX())
                                .start();
                    } else {
                        ObjectAnimator oa = ObjectAnimator.ofFloat(this, "x", getX(), 0);
                        oa.setInterpolator(new DecelerateInterpolator());
                        oa.setDuration(500);
                        oa.start();
                    }
                }
                KLog.i("up---->" + isDrag + "");
                break;
        }
        //如果是拖拽则消耗事件，否则正常传递即可。
        KLog.i("up---->" + isDrag + "");
        KLog.i("super.onTouchEvent---->" + super.onTouchEvent(event));
        return true;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
}
