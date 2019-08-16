package com.lucidastar.hodgepodge.ui.activity.layout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lucidastar.hodgepodge.R;

public class StudyTabLayoutActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_tab_layout);
        mTabLayout  = findViewById(R.id.tl_title);

//        addTabLayoutItem();
        addCustomTab();
    }

    private void addTabLayoutItem() {
        for (int i = 0; i < 12; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText("你好"));
        }
    }

    private void addCustomTab(){
        for (int i = 0; i < 12; i++) {
            View inflate = LayoutInflater.from(StudyTabLayoutActivity.this).inflate(R.layout.tab_custom_item, null);
            TabLayout.Tab tab = mTabLayout.newTab();
            TextView textView = inflate.findViewById(R.id.tv_name);
            if (i % 2 == 0){
                textView.setTextColor(Color.parseColor("#ffccff"));
            }
            tab.setCustomView(inflate);
            mTabLayout.addTab(tab);
        }
    }

}
