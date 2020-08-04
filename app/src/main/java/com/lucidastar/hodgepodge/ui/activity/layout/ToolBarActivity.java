package com.lucidastar.hodgepodge.ui.activity.layout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.lucidastar.hodgepodge.R;

public class ToolBarActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置title
        mToolbar.setTitle("ToolBar的使用");
        //设置副标题
        mToolbar.setSubtitle("subTitle");
        //设置logo
        mToolbar.setLogo(R.mipmap.ic_launcher);
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



        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_test_inservice:

                        mToolbar.setTitle(R.string.TestIntentService);
                        break;
                    case R.id.nav_test_status:

                        mToolbar.setTitle(R.string.TestStatusBar);
                        break;
                    case R.id.nav_test_dagger:

                        mToolbar.setTitle(R.string.TestDagger);
                        break;
                    case R.id.nav_test_widget:

                        mToolbar.setTitle(R.string.TestWidget);
                        break;
                    case R.id.nav_test_other_feature:

                        mToolbar.setTitle(R.string.TestOtherFeature);
                        break;
                    case R.id. nav_test_demo_list:

                        mToolbar.setTitle(R.string.TestDemoList);
                        break;
                }
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer,menu);
        return true;
    }
}
