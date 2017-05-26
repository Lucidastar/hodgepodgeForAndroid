package com.lucidastar.hodgepodge.ui.activity.statusbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lucidastar.hodgepodge.R;

public class ToolBarToUse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar_to_use);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.inflateMenu(R.menu.setting_menu);

        // 设置溢出菜单的图标
//        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_menu_more_overflow));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ToolBarToUse.this,"菜单栏",Toast.LENGTH_SHORT).show();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_setting:
                        //点击设置菜单
                        Toast.makeText(ToolBarToUse.this,"设置",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_model:
                        Toast.makeText(ToolBarToUse.this,"模式",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }
}
