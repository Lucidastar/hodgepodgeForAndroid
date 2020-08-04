package com.lucidastar.hodgepodge.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.Random;

public class ColoredTextView extends AppCompatTextView {
    private static final int[] COLORS = {
            Color.parseColor("#E91E63"),
            Color.parseColor("#673AB7"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#009688"),
            Color.parseColor("#FF9800"),
            Color.parseColor("#FF5722"),
            Color.parseColor("#795548")
    };
    private static final int[] TEXT_SIZES = {
            22, 22, 22
    };
    private static final Random random = new Random();
    private static final int CORNER_RADIUS = (int) dpToPixel(4);

    private static float dpToPixel(int dp) {
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    private static final int X_PADDING = (int) dpToPixel(16);
    private static final int Y_PADDING = (int) dpToPixel(8);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public ColoredTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    {
        setTextColor(Color.WHITE);
        setTextSize(TEXT_SIZES[random.nextInt(3)]);
        paint.setColor(COLORS[random.nextInt(COLORS.length)]);
        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), CORNER_RADIUS, CORNER_RADIUS, paint);
        super.onDraw(canvas);
    }

}
