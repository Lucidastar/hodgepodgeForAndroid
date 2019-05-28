package com.lucidastar.hodgepodge.ui.activity.otherfeature.view.cusview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class ImageTextView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final static int RADIUS = (int) dp2px(120);
    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    private StaticLayout staticLayout;
    TextPaint mTextPaint = new TextPaint();
    public ImageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setColor(Color.parseColor("#436EEE"));
        mPaint.setStrokeWidth(40);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setTextSize(dp2px(60));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.getFontMetrics(fontMetrics);

        mTextPaint.setTextSize(dp2px(20));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        String content = "这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这" +
                "这是内容这是内容这是内容这是内容这是内容这是内容" +
                "这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容" +
                "这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容" +
                "是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容";

        staticLayout = new StaticLayout(content,mTextPaint,getWidth(), Layout.Alignment.ALIGN_NORMAL,1.0f,1.0f,true);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        staticLayout.draw(canvas);
    }

    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
