package com.lucidastar.hodgepodge.ui.activity.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lucidastar.hodgepodge.R;

public class AppBarLayoutActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    //http://www.jianshu.com/p/d159f0176576
    //http://www.jianshu.com/p/ac56f11e7ce1
    //https://github.com/pinguo-zhouwei/MaterialDesignSamples
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("这是appBarLayout");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
