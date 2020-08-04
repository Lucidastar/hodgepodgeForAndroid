package com.lucidastar.hodgepodge.ui.activity.layout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
//        mToolbar.setTitle("这是appBarLayout");
        TextView textView = (TextView) findViewById(R.id.tv_title);
        textView.setText("这是appBarLayout");
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
