package com.lucidastar.hodgepodge.ui.activity.layout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.lucidastar.hodgepodge.R;
import com.mine.lucidastarutils.utils.ScreenUtils;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        int screenHeight = ScreenUtils.getScreenHeight(this);
        Log.i("mine", "屏幕高度："+screenHeight);


    }
}
