package com.lucidastar.hodgepodge.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lucidastar.hodgepodge.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestActivity extends AppCompatActivity {

    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mImageView = (ImageView) this.findViewById(R.id.iv_test);
        Glide.with(this).load("https://www.baidu.com/img/bd_logo1.png").into(mImageView);
        Executors.newSingleThreadScheduledExecutor();
    }
}
