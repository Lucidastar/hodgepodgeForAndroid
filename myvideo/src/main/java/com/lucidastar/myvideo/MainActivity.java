package com.lucidastar.myvideo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void openVideo(View view){
//        startActivity(new Intent(this,VideoActivity.class));

//        Intent intent = VideoActivity.getInstance(this,"http://player.alicdn.com/video/aliyunmedia.mp4",false,VideoActivity.TAG_VOD);
        Intent intent = VideoActivity.getInstance(this,"http://cctvalih5c.v.myalicdn.com/live/cctv1_2/index.m3u8",false,VideoActivity.TAG_LIVE);
        startActivity(intent);
    }
}
