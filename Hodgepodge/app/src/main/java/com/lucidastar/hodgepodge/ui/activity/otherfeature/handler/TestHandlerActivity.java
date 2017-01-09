package com.lucidastar.hodgepodge.ui.activity.otherfeature.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;

import com.lucidastar.hodgepodge.R;
import com.socks.library.KLog;

public class TestHandlerActivity extends AppCompatActivity {



    private Handler mMainThreadHandler;
    private Handler mSubThreadHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);

        mMainThreadHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    KLog.i("收到子线程发过来的信息" + msg.what);
                    int taskId = getTaskId();
                    KLog.i("taskId" + taskId);
                }

                if (mSubThreadHandler != null){
                    mSubThreadHandler.sendEmptyMessageDelayed(1,2000);
                }
            }
        };

        new MyThread().start();

    }


    class MyThread extends Thread{
        @Override
        public void run() {

            mMainThreadHandler.sendEmptyMessageDelayed(0,2000);
            KLog.i(Process.myPid());
            Looper.prepare();
            mSubThreadHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 1) {
                        KLog.i("收到主线程发过来的信息" + msg.what);
                        int taskId = getTaskId();
                        KLog.i("taskId" + taskId);
                        mMainThreadHandler.sendEmptyMessageDelayed(0,10000);
                    }

                }
            };

            Looper.loop();
        }
    }

}
