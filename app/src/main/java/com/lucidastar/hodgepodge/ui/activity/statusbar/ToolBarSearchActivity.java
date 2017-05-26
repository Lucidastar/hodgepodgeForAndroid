package com.lucidastar.hodgepodge.ui.activity.statusbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lucidastar.hodgepodge.R;

public class ToolBarSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.inflateMenu(R.menu.search_menu);
        final EditText searchEdit = (EditText) toolbar.findViewById(R.id.et_search);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_search){

                    // 获取内容
                    String content = searchEdit.getText().toString();
                    Toast.makeText(ToolBarSearchActivity.this,"查询内容："+content,Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
}
