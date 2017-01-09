package com.lucidastar.hodgepodge.ui.activity.otherfeature;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucidastar.hodgepodge.R;
import com.socks.library.KLog;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TestBitmapActivity extends AppCompatActivity {

    @BindView(R.id.btn_getBitmapSize)
    Button mBtnGetBitmapSize;
    @BindView(R.id.tv_size)
    TextView mTvSize;
    @BindView(R.id.iv_test)
    ImageView mIvTest;
    @BindView(R.id.imageWidth)
    EditText mImageWidth;
    @BindView(R.id.imageHeight)
    EditText mImageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bitmap);
        ButterKnife.bind(this);

    }


    //获取占用内存大小
    public final int getByteCount(Bitmap bitmap) {
        // int result permits bitmaps up to 46,340 x 46,340
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    @OnClick({R.id.btn_getBitmapSize, R.id.btn_imageSize, R.id.btn_getScreenInfo, R.id.btn_getCustomImage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_getBitmapSize:
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.forself);
                int byteCount = getByteCount(bitmap);
                KLog.i("图片占用内存的大小:" + byteCount);
                mTvSize.setText("图片占用内存的大小：" + getDataSize(byteCount) + "," + imageWidthAndHeight(R.drawable.forself));
                break;

            case R.id.btn_imageSize:
                BitmapDrawable bitmapDrawable = (BitmapDrawable) mIvTest.getDrawable();
                Bitmap bitmap1 = bitmapDrawable.getBitmap();
                int byteCount1 = getByteCount(bitmap1);
                mTvSize.setText("image的图片：" + getDataSize(byteCount1));
                break;
            case R.id.btn_getScreenInfo:
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                float width = dm.widthPixels * dm.density;
                float height = dm.heightPixels * dm.density;
                mTvSize.setText("屏幕的宽：" + dm.widthPixels + "\n屏幕的高：" + dm.heightPixels + "\n" + "密度：" + dm.density + "\n" + "密度dpi（代表每寸多少个像素点）:" + dm.densityDpi + "\n" + "xdpi:" + dm.xdpi + ",ydpi:" + dm.ydpi + "\n分辨率：" + width + "*" + height);

                break;

            case R.id.btn_getCustomImage:
                int h = Integer.valueOf(mImageHeight.getText().toString());
                int w = Integer.valueOf(mImageWidth.getText().toString());
                Bitmap bitmap2 = decodeSampledBitmapFromResource(getResources(), R.drawable.forself, w, h);
                mIvTest.setImageBitmap(bitmap2);
                break;
        }
    }

    public static String getDataSize(long size) {
        if (size < 0) {
            size = 0;
        }
        DecimalFormat formater = new DecimalFormat("####.00");
        if (size < 1024) {
            return size + "bytes";
        } else if (size < 1024 * 1024) {
            float kbsize = size / 1024f;
            return formater.format(kbsize) + "KB";
        } else if (size < 1024 * 1024 * 1024) {
            float mbsize = size / 1024f / 1024f;
            return formater.format(mbsize) + "MB";
        } else if (size < 1024 * 1024 * 1024 * 1024) {
            float gbsize = size / 1024f / 1024f / 1024f;
            return formater.format(gbsize) + "GB";
        } else {
            return "size: error";
        }

    }

    public String imageWidthAndHeight(int drawId) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), drawId, opts);
        opts.inSampleSize = 1;
        opts.inJustDecodeBounds = false;
//        Bitmap mBitmap =BitmapFactory.decodeResource(getResources(), drawId, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        return "图片的信息：高-->" + height + ",宽-->" + width;
    }


    //先进行测量合适的宽高
    public int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    //生成想要的尺寸的图片
    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

}
