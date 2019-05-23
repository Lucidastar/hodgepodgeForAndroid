package com.lucidastar.hodgepodge.ui.activity.otherfeature.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lucidastar.hodgepodge.R;

public class StudyViewActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置title
        mToolbar.setTitle("view的一些知识点");
        //设置副标题
//        mToolbar.setSubtitle("subTitle");
        //设置logo
//        mToolbar.setLogo(R.mipmap.ic_launcher);
        mToolbar.setSubtitleTextColor(getResources().getColor(R.color.white));
        //这些简单的属性也可以在xml中进行设置
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);//设置返回按钮
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
