package com.lucidastar.performanceanalysis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class PicLoadActivity extends AppCompatActivity {
    static OtherClass sOtherClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_load);
        sOtherClass = new OtherClass();
        ImageView imageView = findViewById(R.id.iv_test);
        imageView.setImageResource(R.mipmap.test_img);

    }

    class OtherClass{

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sOtherClass = null;
    }
}
