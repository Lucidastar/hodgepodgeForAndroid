package com.lucidastar.myvideo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void openVideo(View view){
//        startActivity(new Intent(this,VideoActivity.class));

//        Intent intent = VideoActivity.getInstance(this,"http://player.alicdn.com/video/aliyunmedia.mp4",false,VideoActivity.TAG_VOD);
//        Intent intent = VideoActivity.getInstance(this,"rtmp://58.200.131.2:1935/livetv/hunantv",false,VideoActivity.TAG_LIVE);
        Intent intent = VideoActivity.getInstance(this,"http://cctvalih5ca.v.myalicdn.com/live/cctv1_2/index.m3u8",false,VideoActivity.TAG_LIVE);
        startActivity(intent);
    }
}
