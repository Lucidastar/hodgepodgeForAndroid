package com.lucidastar.hodgepodge.ui.activity.otherfeature.saidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lucidastar.hodgepodge.R;

public class StudentAidlActivity extends AppCompatActivity {

    private LocalService localService;
    MyConnection mServiceConnection;
    boolean mBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_aidl);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,LocalService.class);
        mServiceConnection = new MyConnection();
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void testBinderService(View view){
        int randomNum = localService.getRandomNum();
        Toast.makeText(getApplicationContext(),"获取到了数字"+randomNum,Toast.LENGTH_SHORT).show();
        Log.i("mine", "onServiceConnected: "+randomNum);
    }

    private class MyConnection implements  ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("mine", "链接成功");
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            localService = binder.getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }
}
