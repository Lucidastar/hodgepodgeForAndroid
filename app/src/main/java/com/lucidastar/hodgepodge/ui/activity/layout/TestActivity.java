package com.lucidastar.hodgepodge.ui.activity.layout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

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
