package com.lucidastar.hodgepodge.ui.activity.otherfeature.view.cusview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;


import com.lucidastar.hodgepodge.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiuyouzone on 2018/5/23.
 */

//源码地址：https://github.com/hust201010701/XRadarView/blob/master/xradar/src/main/java/com/orzangleli/radar/XRadarView.java
public class RadarView extends View {

    private static final String TAG = "mine";
    // 几边形雷达
    private int count = 5;

    private int layerCount = 6;  // 层数
    private int drawableSize = 40;
    private int drawablePadding = 10;
    private int descPadding = 5;

    private int titleSize = 40;
    private int dataSize = 30;

    private float radarPercent = 0.7f;

    private int startColor = Color.parseColor("#5783FF");
    private int endColor = Color.parseColor("#6A88FF");
    // 蜘蛛网线的颜色
    private int cobwebColor;
    // 圆心与各顶点连线颜色
    private int lineColor;
    // 数据值文本颜色
    private int dataColor;
    // 如果不是多色区域，是单一的颜色
    private int singleColor;

    // 标题文本颜色
    private int titleColor;
    // 圆点颜色
    private int pointColor;
    // 圆点半径大小
    private int pointRadius;
    // 边界线颜色
    private int borderColor;
    // 边界线的宽度
    private int borderWidth;
    // 半径线的颜色
    private int radiusColor;
    // 雷达图渐变颜色数组
    private int[] shaderColors = {R.color.cr_9bc6f5,R.color.cr_bad3f1,R.color.cr_d1e2f4,R.color.cr_e8f0fa};
    // 雷达图渐变颜色各种颜色分布的位置
    private float[] shaderPositions;


    // 是否画边界线
    private boolean enabledBorder = false;
    // 是否开启动画
    private boolean enabledAnimation = true;
    // 动画时长
    private int animDuration = 1000;
    // 是否显示圆点
    private boolean enabledShowPoint = true;
    // 是否绘制网格
    private boolean enabledPolygon = true;
    // 是否绘制渐变环
    private boolean enabledShade = true;
    // 是否绘制半径
    private boolean enabledRadius = true;
    // 是否绘制文本
    private boolean enabledText = true;
    // 是否将雷达区域绘制成渐变色
    private boolean enabledRegionShader = false;


    private int MAX_TEXT_WIDTH;  // 文字最大允许宽度

    // 每条边对应的圆心角
    private float angle;
    // 圆心x
    private int centerX;
    // 圆心y
    private int centerY;
    // 半径
    private float radius;

    // 外轮廓是否是圆形
    private boolean isCircle = false;

    // 区域渐变shader
    private Shader regionShader;

    private Paint cobwebPaint;
    private Paint linePaint;
    private Paint dataPaint;
    private Paint singlePaint;
    private TextPaint titlePaint;
    private Paint layerPaint;
    private Paint pointPaint;
    private Paint radiusPaint;
    private Paint borderPaint;


    // 当前缩放比
    private float currentScale;

    private List<Rect> titleRects;

    // 图标
    private int drawables[];
    // 标题
    CharSequence titles[];
    // 每种属性的值（0~1.0）
    double percents[];
    // 各个标题下面的数值文本
    CharSequence values[];
    // 区域颜色
    int colors[];

    private Path mRegionShadowPath = new Path();
    private Paint mRegionShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<String> mPositions = new ArrayList<>();

    public RadarView(Context context) {
        this(context, null, 0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getResources().obtainAttributes(attrs, R.styleable.XRadarView);
        count = typedArray.getInteger(R.styleable.XRadarView_count, 6);
        layerCount = typedArray.getInteger(R.styleable.XRadarView_layerCount, 6);
        drawableSize = typedArray.getDimensionPixelSize(R.styleable.XRadarView_mDrawableSize, 40);
        drawablePadding = typedArray.getDimensionPixelSize(R.styleable.XRadarView_mDrawablePadding, 10);
        descPadding = typedArray.getDimensionPixelSize(R.styleable.XRadarView_descPadding, 5);
        titleSize = typedArray.getDimensionPixelSize(R.styleable.XRadarView_titleSize, 40);
        dataSize = typedArray.getDimensionPixelSize(R.styleable.XRadarView_dataSize, 30);
        radarPercent = typedArray.getFloat(R.styleable.XRadarView_radarPercent, 0.7f);
        startColor = typedArray.getColor(R.styleable.XRadarView_startColor, Color.parseColor("#80FFCC33"));
        endColor = typedArray.getColor(R.styleable.XRadarView_endColor, Color.parseColor("#80FFFFCC"));

        cobwebColor = typedArray.getColor(R.styleable.XRadarView_cobwebColor, Color.parseColor("#80444444"));
        lineColor = typedArray.getColor(R.styleable.XRadarView_mLineColor, Color.parseColor("#80999999"));
        dataColor = typedArray.getColor(R.styleable.XRadarView_dataColor, Color.parseColor("#00000000"));
        singleColor = typedArray.getColor(R.styleable.XRadarView_singleColor, Color.parseColor("#1192FF"));
        titleColor = typedArray.getColor(R.styleable.XRadarView_mTitleColor, Color.parseColor("#80000000"));
        pointColor = typedArray.getColor(R.styleable.XRadarView_pointColor, Color.parseColor("#80333366"));
        borderColor = typedArray.getColor(R.styleable.XRadarView_borderColor, Color.parseColor("#80333366"));
        radiusColor = typedArray.getColor(R.styleable.XRadarView_radiusColor, Color.parseColor("#A3C2EB"));
        borderWidth = typedArray.getDimensionPixelSize(R.styleable.XRadarView_mBorderWidth, 5);

        pointRadius = typedArray.getDimensionPixelSize(R.styleable.XRadarView_pointRadius, 10);
        enabledBorder = typedArray.getBoolean(R.styleable.XRadarView_enabledBorder, false);
        enabledAnimation = typedArray.getBoolean(R.styleable.XRadarView_enabledAnimation, true);
        enabledShowPoint = typedArray.getBoolean(R.styleable.XRadarView_enabledShowPoint, true);
        enabledPolygon = typedArray.getBoolean(R.styleable.XRadarView_enabledPolygon, true);
        enabledShade = typedArray.getBoolean(R.styleable.XRadarView_enabledShade, true);
        enabledText = typedArray.getBoolean(R.styleable.XRadarView_enabledText, true);
        animDuration = typedArray.getInteger(R.styleable.XRadarView_animDuration, 1000);

        typedArray.recycle();

        titleRects = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            titleRects.add(null);
        }

        angle = (float) (Math.PI * 2 / count);

        cobwebPaint = new Paint();
        cobwebPaint.setColor(cobwebColor);
        cobwebPaint.setAntiAlias(true);
        cobwebPaint.setStyle(Paint.Style.STROKE);

        linePaint = new Paint();
        linePaint.setColor(lineColor);
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);

        dataPaint = new Paint();
        dataPaint.setColor(dataColor);
        dataPaint.setAntiAlias(true);
        dataPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        singlePaint = new Paint();
        singlePaint.setColor(singleColor);
        singlePaint.setAntiAlias(true);
        singlePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        titlePaint = new TextPaint();
        titlePaint.setTextSize(dataSize);
        titlePaint.setColor(titleColor);
        titlePaint.setAntiAlias(true);

        layerPaint = new Paint();
        layerPaint.setAntiAlias(true);
        layerPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        pointPaint = new Paint();
        pointPaint.setStyle(Paint.Style.STROKE);
        pointPaint.setAntiAlias(true);
        pointPaint.setStrokeWidth(6f);
        pointPaint.setColor(pointColor);

        radiusPaint = new Paint();
        radiusPaint.setStyle(Paint.Style.FILL);
        radiusPaint.setAntiAlias(true);
        radiusPaint.setColor(radiusColor);


        borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        borderPaint.setAntiAlias(true);
        borderPaint.setColor(borderColor);
        borderPaint.setStrokeWidth(borderWidth);


        mRegionShadowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mRegionShadowPaint.setColor(Color.parseColor("#2E1192FF"));


        loadAnimation(enabledAnimation);

        colors = new int[count];
        drawables = new int[count];
        titles = new CharSequence[count];
        percents = new double[count];
        values = new CharSequence[count];

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w) / 2 * radarPercent;
        MAX_TEXT_WIDTH = (int) (Math.min(h, w) / 2 * (1 - radarPercent));
        //中心坐标
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(Math.min(wSpecSize, hSpecSize), Math.min(wSpecSize, hSpecSize));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (regionShader == null && shaderColors != null) {
            regionShader = new LinearGradient(getLeft(), getTop(), getRight(), getBottom(), shaderColors, shaderPositions, Shader.TileMode.CLAMP);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (enabledShade) {
            drawLayer(canvas, startColor, endColor);
        } else {
            drawLayer(canvas, startColor, startColor);
        }
        if (enabledPolygon) {
            drawPolygon(canvas);
        }

        if (enabledRadius) {
            drawRadius(canvas);
        }
        if (enabledText) {
            drawText(canvas);
        }
        if (colors == null || colors.length == 0) {
            drawRegion(canvas, currentScale);
        } else {
            drawRegionWithColor(canvas, currentScale);
        }

        if (enabledBorder) {
            drawBorder(canvas, currentScale);
        }

        drawShadow(canvas);
        if (enabledShowPoint) {
            drawPoint(canvas, currentScale);
        }


    }

    Path mPath = new Path();

    private void drawBorder(Canvas canvas, float scale) {
        float curX, curY;
        float nextX, nextY;
        for (int i = 0; i < count - 1; i++) {
            curX = (float) (centerX + Math.cos(angle * i + Math.PI / 2) * radius * percents[i] * scale);
            curY = (float) (centerY - Math.sin(angle * i + Math.PI / 2) * radius * percents[i] * scale);

            nextX = (float) (centerX + Math.cos(angle * (i + 1) + Math.PI / 2) * radius * percents[i + 1] * scale);
            nextY = (float) (centerY - Math.sin(angle * (i + 1) + Math.PI / 2) * radius * percents[i + 1] * scale);

            canvas.drawLine(curX, curY, nextX, nextY, borderPaint);

        }

        curX = (float) (centerX + Math.cos(angle * (count - 1) + Math.PI / 2) * radius * percents[count - 1] * scale);
        curY = (float) (centerY - Math.sin(angle * (count - 1) + Math.PI / 2) * radius * percents[count - 1] * scale);
        nextX = (float) (centerX + Math.cos(angle * 0 + Math.PI / 2) * radius * percents[0] * scale);
        nextY = (float) (centerY - Math.sin(angle * 0 + Math.PI / 2) * radius * percents[0] * scale);

        canvas.drawLine(curX, curY, nextX, nextY, borderPaint);


    }


    private void drawPoint(Canvas canvas, float scale) {

        mPositions.clear();
        for (int i = 0; i < count; i++) {
            int x, y;
            x = (int) (centerX + scale * percents[i] * radius * Math.cos(angle * i + Math.PI / 2));
            y = (int) (centerY - scale * percents[i] * radius * Math.sin(angle * i + Math.PI / 2));
            pointPaint.setColor(Color.parseColor("#2269BB"));
            canvas.drawCircle(x, y, pointRadius, pointPaint);
            mPositions.add(x+":"+y);

            //修改
            canvas.save();
            canvas.scale(0.8f,0.8f,x,y);
            pointPaint.setColor(Color.WHITE);
            canvas.drawCircle(x, y, pointRadius, pointPaint);
            canvas.restore();

        }



    }

    // 画圆心与顶点的连线
    private void drawRadius(Canvas canvas) {
        for (int i = 0; i < count; i++) {
            canvas.drawLine(centerX, centerY, (float) (centerX + Math.cos(angle * i + Math.PI / 2) * radius), (float) (centerY - Math.sin(angle * i + Math.PI / 2) * radius), radiusPaint);
        }

    }

    public void setRadiusLineWidth(float width){
        radiusPaint.setStrokeWidth(width);
        invalidate();
    }

    // 画蜘蛛网
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / layerCount;
        for (int i = layerCount; i >= 1; i--) {
            float curR = r * i;
            path.reset();
            if (isCircle) {
                path.addCircle(centerX, centerY, curR, Path.Direction.CW);
            } else {
                for (int j = 0; j < count; j++) {
                    if (j == 0) {
                        path.moveTo(centerX, centerY - curR);
                    } else {
                        path.lineTo((float) (centerX + Math.cos(angle * j + Math.PI / 2) * curR), (float) (centerY - Math.sin(angle * j + Math.PI / 2) * curR));
                    }
                }
                path.close();
            }
            canvas.drawPath(path, cobwebPaint);
        }
    }

    // 画 各层的颜色
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void drawLayer(Canvas canvas, int startColor, int endColor) {
        Path path = null;
        Path prePath = null;
        float r = radius / layerCount;
        for (int i = layerCount; i >= 0; i--) {
            float curR = r * i;
            path = new Path();
            for (int j = 0; j < count; j++) {
                if (isCircle) {
                    path.addCircle(centerX, centerY, curR, Path.Direction.CW);
                } else {
                    if (j == 0) {
                        path.moveTo(centerX, centerY - curR);
                    } else {
                        path.lineTo((float) (centerX + Math.cos(angle * j + Math.PI / 2) * curR), (float) (centerY - Math.sin(angle * j + Math.PI / 2) * curR));
                    }
                }
            }

            if (prePath != null) {
                if (i != 0) {
                    prePath.op(path, Path.Op.DIFFERENCE);
                    prePath.close();
                    // 计算渐变颜色
                    int a0 = Color.alpha(startColor);
                    int r0 = Color.red(startColor);
                    int g0 = Color.green(startColor);
                    int b0 = Color.blue(startColor);

                    int a1 = Color.alpha(endColor);
                    int r1 = Color.red(endColor);
                    int g1 = Color.green(endColor);
                    int b1 = Color.blue(endColor);

                    int a2 = (int) (1.0 * i * (a1 - a0) / layerCount + a0);
                    int r2 = (int) (1.0 * i * (r1 - r0) / layerCount + r0);
                    int g2 = (int) (1.0 * i * (g1 - g0) / layerCount + g0);
                    int b2 = (int) (1.0 * i * (b1 - b0) / layerCount + b0);

                    layerPaint.setColor(Color.argb(a2, r2, g2, b2));
                    canvas.drawPath(prePath, layerPaint);
                } else {
                    prePath.close();
                    layerPaint.setColor(startColor);
                    canvas.drawPath(prePath, layerPaint);
                }
            }
            prePath = path;

        }
    }

    // 画 文字和图标
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = titlePaint.getFontMetrics();
        for (int i = 0; i < count; i++) {
            float x = 0f, y = 0f, curAngle;

            x = (float) (centerX + (radius) * Math.cos(angle * i + Math.PI / 2));
            y = (float) (centerY - (radius) * Math.sin(angle * i + Math.PI / 2));
            curAngle = (float) (angle * i + Math.PI / 2);
            // 取余 在0-2PI范围内
            curAngle = (float) (curAngle % (2 * Math.PI));
            SpannableString ss;
            if (titles[i] instanceof SpannableString) {
                ss = (SpannableString) titles[i];
            } else {
                if (values == null || values[i] == null) {
                    ss = new SpannableString(titles[i]);
                } else {
                    ss = new SpannableString(titles[i] + "\n" + values[i]);
                }
                ss.setSpan(new AbsoluteSizeSpan(titleSize,false), 0, titles[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(new ForegroundColorSpan(Color.parseColor("#262626")), titles[i].length(), ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(new AbsoluteSizeSpan(dataSize,false), titles[i].length(), ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (drawables == null) {
                drawables = new int[count];
            }
            if (Math.abs(curAngle - 3 * Math.PI / 2) < 0.1 || Math.abs(curAngle - Math.PI / 2) < 0.1) {
                if (Math.abs(curAngle - Math.PI / 2) < 0.1) {
                    drawMultiLinesTextAndIcon(canvas, x - MAX_TEXT_WIDTH / 2, y, ss, drawables[i], 1, i);
                } else if (Math.abs(curAngle - Math.PI * 3 / 2) < 0.1) {
                    drawMultiLinesTextAndIcon(canvas, x - MAX_TEXT_WIDTH / 2, y, ss, drawables[i], -1, i);
                } else {
                    drawMultiLinesTextAndIcon(canvas, x - MAX_TEXT_WIDTH / 2, y, ss, drawables[i], 0, i);
                }
            } else if (curAngle >= 0 && curAngle < Math.PI / 2) {
                drawMultiLinesTextAndIcon(canvas, x + descPadding, y, ss, drawables[i], 0, i);
            } else if (curAngle > 3 * Math.PI / 2 && curAngle <= Math.PI * 2) {
                drawMultiLinesTextAndIcon(canvas, x + descPadding, y, ss, drawables[i], 0, i);
            } else if (curAngle > Math.PI / 2 && curAngle <= Math.PI) {
                drawMultiLinesTextAndIcon(canvas, x - MAX_TEXT_WIDTH, y, ss, drawables[i], 0, i);
            } else if (curAngle >= Math.PI && curAngle < 3 * Math.PI / 2) {
                drawMultiLinesTextAndIcon(canvas, x - MAX_TEXT_WIDTH, y, ss, drawables[i], 0, i);
            }
        }
    }

    public Bitmap getResizeBitmap(int drawable) {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), drawable);
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        // 设置想要的大小
        int newWidth = drawableSize;
        int newHeight = drawableSize;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newBm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
                true);
        bitmap.recycle();
        return newBm;
    }

    // 用一种颜色画区域
    private void drawRegion(Canvas canvas, float scale) {
        canvas.save();
        singlePaint.setColor(singleColor);
        if (enabledRegionShader) {
            singlePaint.setShader(regionShader);
        } else {
            singlePaint.setShader(null);
        }
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int x, y;
            x = (int) (centerX + scale * percents[i] * radius * Math.cos(angle * i + Math.PI / 2));
            y = (int) (centerY - scale * percents[i] * radius * Math.sin(angle * i + Math.PI / 2));
            Point p = new Point();
            p.set(x, y);
            list.add(p);
        }

        Path path = new Path();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                path.moveTo(list.get(i).x, list.get(i).y);
            } else {
                path.lineTo(list.get(i).x, list.get(i).y);
            }
        }
        path.close();
        canvas.drawPath(path, singlePaint);
        canvas.restore();
    }

    // 多种颜色画区域
    private void drawRegionWithColor(Canvas canvas, float scale) {
        canvas.save();
        int colorSize = colors.length;
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int x, y;
            x = (int) (centerX + scale * percents[i] * radius * Math.cos(angle * i + Math.PI / 2));
            y = (int) (centerY - scale * percents[i] * radius * Math.sin(angle * i + Math.PI / 2));
            Point p = new Point();
            p.set(x, y);
            list.add(p);
        }

        Path path = new Path();
        for (int i = 0; i < list.size() - 1; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            path.lineTo(list.get(i).x, list.get(i).y);
            path.lineTo(list.get(i + 1).x, list.get(i + 1).y);
            path.lineTo(centerX, centerY);
            path.close();
            dataPaint.setColor(colors[i % colorSize]);
            canvas.drawPath(path, dataPaint);
        }
        path.reset();
        path.moveTo(centerX, centerY);
        path.lineTo(list.get(list.size() - 1).x, list.get(list.size() - 1).y);
        path.lineTo(list.get(0).x, list.get(0).y);
        path.lineTo(centerX, centerY);
        path.close();
        dataPaint.setColor(colors[(list.size() - 1) % colorSize]);
        canvas.drawPath(path, dataPaint);
        canvas.restore();
    }

    // 画 多行文字
    public void drawMultiLinesTextAndIcon(Canvas canvas, float x, float y, CharSequence text, int drawable, int verticalValue, int position) {
        int drawableAvaiable = 1;
        try {
            this.getContext().getResources().openRawResource(drawable);
            drawableAvaiable = 1;
        } catch (Exception e) {
            drawableAvaiable = 0;
        }
        int allowWidth = MAX_TEXT_WIDTH - descPadding;
        int rectWidth = allowWidth;
        Rect rect;
        if (drawable != -1) {
            if (allowWidth > drawableSize * drawableAvaiable + drawablePadding) {
                allowWidth = allowWidth - drawableSize * drawableAvaiable - drawablePadding;
            }
        }
        StaticLayout layout = new StaticLayout(text, titlePaint, allowWidth, Layout.Alignment.ALIGN_CENTER, 1.0F, 0.0F, true);
        canvas.save();
        if (verticalValue == 1) {
            canvas.translate(x, y - layout.getHeight() - descPadding);
            rect = new Rect((int) x, (int) (y - layout.getHeight() - descPadding), (int) x + rectWidth, (int) (y - layout.getHeight() - descPadding) + layout.getHeight());
        } else if (verticalValue == -1) {
            canvas.translate(x, y + descPadding);
            rect = new Rect((int) x, (int) (y + descPadding), (int) x + rectWidth, (int) (y + descPadding) + layout.getHeight());
        } else {
            canvas.translate(x, y - layout.getHeight() / 2);
            rect = new Rect((int) x, (int) (y - layout.getHeight() / 2), (int) x + rectWidth, (int) (y - layout.getHeight() / 2) + layout.getHeight());
        }
        titleRects.set(position, rect);
        layout.draw(canvas);
        canvas.restore();//别忘了restore

        // 绘制图标
        if (drawableSize * drawableAvaiable != 0) {
            Bitmap bitmap = getResizeBitmap(drawable);
            if (bitmap != null) {
                if (verticalValue == 1) {
                    canvas.drawBitmap(bitmap, x + layout.getWidth(), y - drawableSize * drawableAvaiable / 2 - layout.getHeight() / 2 - descPadding, titlePaint);
                } else if (verticalValue == -1) {
                    canvas.drawBitmap(bitmap, x + layout.getWidth(), y + descPadding + layout.getHeight() / 2 - drawableSize * drawableAvaiable / 2, titlePaint);
                } else {
                    canvas.drawBitmap(bitmap, x + layout.getWidth(), y - drawableSize * drawableAvaiable / 2, titlePaint);
                }
                bitmap.recycle();
            }
        }

    }

    public void loadAnimation(boolean enabled) {
        if (!enabled) {
            currentScale = 1;
            invalidate();
        } else {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.3f, 1.0f);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.setDuration(animDuration);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentScale = (float) animation.getAnimatedValue();
                    invalidate();
                }

            });
            valueAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.i(TAG, "onAnimationEnd: ");
                    setPathShadow();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            valueAnimator.start();
        }
    }

    private void setPathShadow(){
        if (mPositions != null && mPositions.size() == 5){
            List<String> strings = mPositions.subList(mPositions.size() - 5, mPositions.size());
            String s = strings.get(0);
            String[] split = s.split(":");
            mRegionShadowPath.moveTo(Float.parseFloat(split[0]),Float.parseFloat(split[1]));
            for (int i = 1; i < strings.size(); i++) {
                String str = strings.get(i);
                String[] split1 = str.split(":");
                mRegionShadowPath.lineTo(Float.parseFloat(split1[0]),Float.parseFloat(split1[1]));
            }
            mRegionShadowPath.close();
        }
        invalidate();
    }

    private void drawShadow(Canvas canvas){
        if (mPositions.size() > 0){
            canvas.drawPath(mRegionShadowPath,mRegionShadowPaint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                for (int i = 0; i < titleRects.size(); i++) {
                    Rect rect = titleRects.get(i);
                    if (rect != null && rect.contains(x, y)) {
                        if (onTitleClickListener != null) {
                            onTitleClickListener.onTitleClick(RadarView.this, i, x, y, rect);
                            return true;
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }


    OnTitleClickListener onTitleClickListener;

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.onTitleClickListener = onTitleClickListener;
    }

    public interface OnTitleClickListener {
        void onTitleClick(RadarView view, int position, int touchX, int touchY, Rect titleRect);
    }

    public void setDrawables(int[] drawables) {
        this.drawables = drawables;
        invalidate();
    }

    public void setPercents(double[] percents) {
        this.percents = percents;
        invalidate();
    }

    public void setTitles(CharSequence[] titles) {
        this.titles = titles;
        invalidate();
    }

    public void setValues(CharSequence[] values) {
        this.values = values;
        invalidate();
    }

    public void setColors(int[] colors) {
        this.colors = colors;
        invalidate();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        angle = (float) (Math.PI * 2 / count);
        invalidate();
    }

    public int getLayerCount() {
        return layerCount;
    }

    public void setLayerCount(int layerCount) {
        this.layerCount = layerCount;
        invalidate();
    }

    public int getDrawableSize() {
        return drawableSize;
    }

    public void setDrawableSize(int drawableSize) {
        this.drawableSize = drawableSize;
        invalidate();
    }

    public int getDrawablePadding() {
        return drawablePadding;
    }

    public void setDrawablePadding(int drawablePadding) {
        this.drawablePadding = drawablePadding;
        invalidate();
    }

    public int getDescPadding() {
        return descPadding;
    }

    public void setDescPadding(int descPadding) {
        this.descPadding = descPadding;
        invalidate();
    }

    public int getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
        invalidate();
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
        invalidate();
    }

    public float getRadarPercent() {
        return radarPercent;
    }

    public void setRadarPercent(float radarPercent) {
        this.radarPercent = radarPercent;
        invalidate();
    }

    public int getStartColor() {
        return startColor;
    }

    public void setStartColor(int startColor) {
        this.startColor = startColor;
        invalidate();
    }

    public int getEndColor() {
        return endColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
        invalidate();
    }

    public boolean isEnabledAnimation() {
        return enabledAnimation;
    }

    public void setEnabledAnimation(boolean enabledAnimation) {
        this.enabledAnimation = enabledAnimation;
        invalidate();
    }

    public boolean isEnabledShowPoint() {
        return enabledShowPoint;
    }

    public void setEnabledShowPoint(boolean enabledShowPoint) {
        this.enabledShowPoint = enabledShowPoint;
        invalidate();
    }

    public int getCobwebColor() {
        return cobwebColor;
    }

    public void setCobwebColor(int cobwebColor) {
        this.cobwebColor = cobwebColor;
        cobwebPaint.setColor(cobwebColor);
        invalidate();
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        linePaint.setColor(lineColor);
        invalidate();
    }

    public int getDataColor() {
        return dataColor;
    }

    public void setDataColor(int dataColor) {
        this.dataColor = dataColor;
        dataPaint.setColor(dataColor);
        invalidate();
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        titlePaint.setColor(titleColor);
        invalidate();
    }

    public int getPointColor() {
        return pointColor;
    }

    public void setPointColor(int pointColor) {
        this.pointColor = pointColor;
        pointPaint.setColor(pointColor);
        invalidate();
    }

    public void setPointColor(Paint pointColor) {
        this.pointColor = pointColor.getColor();
        this.pointPaint = pointColor;
        invalidate();
    }

    public int getPointRadius() {
        return pointRadius;
    }

    public void setPointRadius(int pointRadius) {
        this.pointRadius = pointRadius;
        invalidate();
    }

    public boolean isEnabledBorder() {
        return enabledBorder;
    }

    public void setEnabledBorder(boolean enabledBorder) {
        this.enabledBorder = enabledBorder;
        invalidate();
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        borderPaint.setColor(borderColor);
        invalidate();
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public boolean isEnabledPolygon() {
        return enabledPolygon;
    }

    public void setEnabledPolygon(boolean enabledPolygon) {
        this.enabledPolygon = enabledPolygon;
        invalidate();
    }

    public int[] getColors() {
        return colors;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public void setCircle(boolean circle) {
        isCircle = circle;
        invalidate();
    }

    public boolean isEnabledShade() {
        return enabledShade;
    }

    public void setEnabledShade(boolean enabledShade) {
        this.enabledShade = enabledShade;
        invalidate();
    }

    public boolean isEnabledRadius() {
        return enabledRadius;
    }

    public void setEnabledRadius(boolean enabledRadius) {
        this.enabledRadius = enabledRadius;
        invalidate();
    }

    public boolean isEnabledText() {
        return enabledText;
    }

    public void setEnabledText(boolean enabledText) {
        this.enabledText = enabledText;
        invalidate();
    }

    public int getSingleColor() {
        return singleColor;
    }

    public void setSingleColor(int singleColor) {
        this.singleColor = singleColor;
        singlePaint.setColor(singleColor);
        invalidate();
    }

    public void setRegionShaderConfig(int colors[], float positions[]) {
        this.shaderColors = colors;
        this.shaderPositions = positions;
        requestLayout();
        invalidate();
    }

    public void setEnabledRegionShader(boolean enabled) {
        this.enabledRegionShader = enabled;
        requestLayout();
        invalidate();
    }

    public RectF getPathRect() {
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int x, y;
            x = (int) (centerX + percents[i] * radius * Math.cos(angle * i + Math.PI / 2));
            y = (int) (centerY - percents[i] * radius * Math.sin(angle * i + Math.PI / 2));
            Point p = new Point();
            p.set(x, y);
            list.add(p);
        }

        Path path = new Path();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                path.moveTo(list.get(i).x, list.get(i).y);
            } else {
                path.lineTo(list.get(i).x, list.get(i).y);
            }
        }
        path.close();
        RectF rect = new RectF();
        path.computeBounds(rect, true);
        return rect;
    }
}
