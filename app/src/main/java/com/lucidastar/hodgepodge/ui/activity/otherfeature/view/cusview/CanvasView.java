package com.lucidastar.hodgepodge.ui.activity.otherfeature.view.cusview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.lucidastar.hodgepodge.R;

public class CanvasView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path mPath = new Path();
    Bitmap mBitmap = null;
    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(40);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(12);

        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setStrokeWidth(60);
        mPaint2.setColor(Color.RED);


        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meizhi);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//layout改变了  就会调用
        super.onSizeChanged(w, h, oldw, oldh);
        mPath.reset();
        mPath.addRect(400,400,600,600, Path.Direction.CCW);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {

//        canvas.drawColor(Color.parseColor("#ccffcc"));//画背景
        canvas.drawBitmap(mBitmap,new Rect(0,0,getWidth(),getBottom()),new Rect(0,0,getWidth(),getBottom()),mPaint);
        canvas.drawLine(0,0,300,300,mPaint);//划线
        canvas.drawCircle(300,300,60,mPaint);//画圆
        canvas.drawPath(mPath,mPaint);//画路径
        canvas.drawTextOnPath("这是内容这是内容这是内容这是内容这是内容这是内容",mPath,20,20,mPaint);//在路径上写字
        canvas.drawText("这是画的测试内容测试内容",0,500,mPaint);//画字
        canvas.drawArc(new RectF(100,800,500,1200),180,180,false, mPaint1);//画一个圆弧
//        canvas.drawBitmap(mBitmap,200,850,mPaint);

//        canvas.drawBitmap(mBitmap,new Rect(mBitmap.getWidth()/3,300,mBitmap.getWidth()/2+400,1000),new Rect(mBitmap.getWidth()/3,300,mBitmap.getWidth()/2+400,1000),mPaint);
        //第一个参数为要绘制的bitmap对象，第二个参数为要绘制的Bitmap对象的矩形区域，第三个参数为要将bitmap绘制在屏幕的什么地方，第四个参数为Paint对象。
        //画了图像的一部分

//        canvas.drawPicture(new Picture());//这个不太理解，先不了解

        canvas.drawPoint(100,1100,mPaint2);//画个点


        canvas.drawRect(new RectF(100,1300,400,1600),mPaint1);//画一个长方形  我设置成了正方形

        canvas.drawRoundRect(new RectF(500,1300,900,1600),30,30,mPaint1);//画圆角长方形

//        canvas.drawVertices();//看名字是画一个垂直的  没懂怎么用
//        canvas.clipOutRect(new RectF(0,0,1600,1600));//裁剪  这是api要求是26
        canvas.save();
        canvas.translate(1600,600);
        canvas.restore();
        super.onDraw(canvas);
    }
}
