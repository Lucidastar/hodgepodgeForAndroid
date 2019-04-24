package com.lucidastar.hodgepodge.ui.activity.api;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.handler.TestHandlerActivity;
import com.mine.lucidastarutils.log.KLog;
import com.mine.lucidastarutils.utils.ToastUtils;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MatrixStudyActivity extends AppCompatActivity {
    @BindView(R.id.iv_test)
    ImageView mImageView;
    @BindView(R.id.btn_rotate)
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_study);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_rotate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rotate:
                ToastUtils.showShortToast("旋转");
                rotate(mImageView);
                break;
        }
    }

    private void rotate(ImageView imageView){
        Matrix matrix = new Matrix();
        /*matrix.reset();
        final float viewWidth = getImageViewWidth(imageView);
        final float viewHeight = getImageViewHeight(imageView);
        final int drawableWidth = imageView.getDrawable().getIntrinsicWidth();
        final int drawableHeight = imageView.getDrawable().getIntrinsicHeight();
        matrix.postTranslate((viewWidth - drawableWidth) / 2F,
                (viewHeight - drawableHeight) / 2F);
        imageView.setImageMatrix(matrix);
        float[] des = new float[18];
        matrix.getValues(des);
        KLog.i("内容="+ Arrays.toString(des));*/
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meizhi);
        // 创建一个和原图一样大小的图片
        Bitmap afterBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);

        // 依据原图的中心位置旋转
        matrix.setRotate(120, bitmap.getWidth() / 2,
                bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, matrix, new Paint());
        imageView.setImageBitmap(afterBitmap);

    }

    private int getImageViewWidth(ImageView imageView) {
        return imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
    }

    private int getImageViewHeight(ImageView imageView) {
        return imageView.getHeight() - imageView.getPaddingTop() - imageView.getPaddingBottom();
    }
}
