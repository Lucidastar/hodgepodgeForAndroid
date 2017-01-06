package com.lucidastar.hodgepodge.ui.activity.otherfeature.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.lucidastar.hodgepodge.R;
import com.socks.library.KLog;

public class TestHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String result = (String) msg.obj;
                KLog.i("mine",result);
            }
        };


        Message msg = Message.obtain();
        msg.obj = "nihao";
        handler.sendMessage(msg);

    }



}
