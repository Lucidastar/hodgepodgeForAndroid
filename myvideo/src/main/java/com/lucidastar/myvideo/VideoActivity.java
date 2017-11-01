package com.lucidastar.myvideo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.MediaPlayer;
import com.lucidastar.myvideo.utils.Formatter;
import com.mine.lucidastarutils.utils.ScreenUtils;
import com.mine.lucidastarutils.utils.ToastUtils;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class VideoActivity extends AppCompatActivity {

    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_PHONE_STATE};
    private AliVcMediaPlayer mPlayer = null;
    private SurfaceHolder mSurfaceHolder = null;
    private SurfaceView mSurfaceView = null;
    // 标记播放器是否已经停止
    private boolean isStopPlayer = false;
    // 标记播放器是否已经暂停
    private boolean isPausePlayer = false;
    private boolean isPausedByUser = false;
    //用来控制应用前后台切换的逻辑
    private boolean isCurrentRunningForeground = true;
    private boolean isFullScreen = false;
    public final static int TAG_VOD=0;//录播
    public final static int TAG_LIVE=1;//直播
    private static final int REQUEST_READ_PHONE = 1;

    private int playType = TAG_VOD;
    private String playUrl = "http://player.alicdn.com/video/aliyunmedia.mp4";
    private String playUrl1 = "rtmp://live.hkstv.hk.lxdns.com/live/hks";


    private FrameLayout flContain;//包裹的容器
    private LinearLayout mLoading;//加载动画
    private TextView mTextLoading;//加载文字
    private LinearLayout mllChangePosition;//快进时显示的界面
    private TextView mTextCurrentPosition;//快进时显示的进度
    private ProgressBar mPbChangePosition;//快进时显示的进度条

    private LinearLayout mLLPlayComplete;//播放完成界面
    private TextView mTextReplay;//重新播放
    private TextView mTextShare;//分享

    private LinearLayout mLLPlayError;//播放错误
    private TextView mTextRetry;//重试

    private LinearLayout mLLTop;//顶部区域
    private ImageView mIvBack;//返回
    private TextView mTextTitle;//标题

    private LinearLayout mLLBottom;//底部区域
    private ImageView mIvPlayOrPause;//播放和暂停
    private TextView mTextPlayPosition;//播放位置
    private TextView mTextPlayCurrentPosition;//播放的当前位置

    private SeekBar mSeekBar;//进度条
    private ImageView mIvFullScreen;//全屏
    private TextView mTextVideoLength;//视频长度
    private ImageView mIvCenterPlay;//中间播放按钮
    private String TAG = "VideoActivity";

    private TextView mTextVideoLengthFront;

    private List<String> logStrs = new ArrayList<>();
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SS");
    private boolean inSeek = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        getParams();
        initView();
        //检查权限
        checkPlayPermission();

        initListener();
    }

    private void initListener() {
        mIvFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mPlayer != null) {
                    mPlayer.setVolume(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mIvCenterPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inSeek = false;
                start();
                showVideoProgressInfo();
            }
        });
    }

    private void initView() {
        flContain = (FrameLayout) findViewById(R.id.fl_contain);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvCenterPlay = (ImageView) findViewById(R.id.iv_center_start);
        mIvFullScreen = (ImageView) findViewById(R.id.iv_full_screen);
        mIvPlayOrPause = (ImageView) findViewById(R.id.iv_restart_or_pause);
        mLLBottom = (LinearLayout) findViewById(R.id.ll_bottom);
        mllChangePosition = (LinearLayout) findViewById(R.id.ll_change_position);
        mLLPlayComplete = (LinearLayout) findViewById(R.id.ll_completed);
        mLLPlayError = (LinearLayout) findViewById(R.id.ll_error);
        mLLTop = (LinearLayout) findViewById(R.id.ll_top);

        mTextCurrentPosition = (TextView) findViewById(R.id.tv_change_position_current);
        mTextLoading = (TextView) findViewById(R.id.tv_load);
        mTextPlayCurrentPosition = (TextView) findViewById(R.id.tv_position);
        mTextReplay = (TextView) findViewById(R.id.tv_replay);
        mTextShare = (TextView) findViewById(R.id.tv_share);
        mTextTitle = (TextView) findViewById(R.id.tv_title);
        mTextVideoLength = (TextView) findViewById(R.id.tv_length);

        mTextVideoLengthFront = (TextView) findViewById(R.id.tv_duration);
        mSeekBar = (SeekBar) findViewById(R.id.sb_seek);
    }

    private void checkPlayPermission() {
        //检查权限
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkOnePermission(PERMISSIONS_STORAGE[0])) {
                //获取传过来的参数
                initPlayerData();
            } else {
                permissionCheck();
            }
        } else {
            initPlayerData();
        }
    }

    //获取参数
    private void getParams(){
        Intent intent = getIntent();
        playUrl = intent.getStringExtra("videoUrl");
        isFullScreen = intent.getBooleanExtra("isFullScreen",false) ;
        playType = intent.getIntExtra("playType",TAG_VOD);
    }

    //实例化播放器的参数
    private void initPlayerData() {
        mSurfaceView = new SurfaceView(this);
        mPlayer = new AliVcMediaPlayer(this, mSurfaceView);
        flContain.removeAllViews();
        flContain.addView(mSurfaceView);
        mSurfaceView.setZOrderOnTop(false);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(mSurfaceHolderCallBack);
        initPlayerListener();

    }

    private void initPlayerListener() {
        if (mPlayer != null) {
            // 初始化播放器
            mPlayer.setPreparedListener(new VideoPreparedListener());
            mPlayer.setErrorListener(new VideoErrorListener());
            mPlayer.setInfoListener(new VideoInfoListener());
            mPlayer.setSeekCompleteListener(new VideoSeekCompleteListener());
            mPlayer.setCompletedListener(new VideoCompleteListener());
            mPlayer.setVideoSizeChangeListener(new VideoSizeChangeListener());
            mPlayer.setBufferingUpdateListener(new VideoBufferUpdateListener());
            mPlayer.setStopedListener(new VideoStoppedListener());
            mPlayer.setFrameInfoListener(new MyFrameInfoListener(this));
            // 如果同时支持软解和硬解是有用
            mPlayer.setDefaultDecoder(1);
            // 重点: 在调试阶段可以使用以下方法打开native log
            mPlayer.enableNativeLog();
            if (playType == TAG_VOD) {
                mPlayer.setMediaType(MediaPlayer.MediaType.Vod);
            } else {
                mPlayer.setMediaType(MediaPlayer.MediaType.Live);
            }
        }
    }


    public static Intent getInstance(Context context,String url,boolean isScreen,int playType){
        Intent intent = new Intent(context,VideoActivity.class);
        intent.putExtra("videoUrl",url);
        intent.putExtra("isScreen",isScreen);
        intent.putExtra("playType",playType);
        return intent;
    }

    public void startVideo(Context context, String url, boolean isScreen, int playType){
        Intent instance = getInstance(context, url, isScreen,playType);
        VideoActivity.this.startActivity(instance);
    }

    private SurfaceHolder.Callback mSurfaceHolderCallBack = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            //                holder.setType(SurfaceHolder.SURFACE_TYPE_GPU);
            holder.setKeepScreenOn(true);
            // Important: surfaceView changed from background to front, we need reset surface to mediaplayer.
            // 对于从后台切换到前台,需要重设surface;部分手机锁屏也会做前后台切换的处理
            if (mPlayer != null) {
                mPlayer.setVideoSurface(holder.getSurface());
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            if (mPlayer != null) {
                mPlayer.setSurfaceChanged();
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
                if (mPlayer != null) {
                    mPlayer.releaseVideoSurface();
                }
        }
    };

    private void showVideoProgressInfo() {
        int curPosition = (int) mPlayer.getCurrentPosition();
        int duration = (int) mPlayer.getDuration();
        if ((mPlayer.isPlaying())
                && !inSeek) {

            mTextPlayCurrentPosition.setText(Formatter.formatTime(curPosition));
            mTextVideoLengthFront.setText(Formatter.formatTime(duration));
            mSeekBar.setMax(duration);
            mSeekBar.setProgress(curPosition);
        }
        startUpdateTimer();
    }

    private void startUpdateTimer() {
        progressUpdateTimer.removeMessages(0);
        progressUpdateTimer.sendEmptyMessageDelayed(0, 1000);
    }

    private void stopUpdateTimer() {
        progressUpdateTimer.removeMessages(0);
    }
    private Handler progressUpdateTimer = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            showVideoProgressInfo();
        }
    };

    //开始播放
    private void start() {
        if (mPlayer != null) {
            if (!TextUtils.isEmpty(playUrl)) {
                mPlayer.prepareAndPlay(playUrl);
            }
            mIvPlayOrPause.setBackgroundResource(R.drawable.ic_player_pause);

        }
    }

    private void pause() {
        if (mPlayer != null) {
            mPlayer.pause();
            mIvPlayOrPause.setBackgroundResource(R.drawable.ic_player_start);
        }
    }

    private void stop() {
        if (mPlayer != null) {
            mPlayer.stop();
        }
    }

    //继续
    private void resume() {
        if (mPlayer != null) {
            mPlayer.play();
            mIvPlayOrPause.setBackgroundResource(R.drawable.ic_player_start);
        }
    }
    //销毁
    private void destroy() {
        if (mPlayer != null) {
            mPlayer.releaseVideoSurface();
            mPlayer.stop();
            mPlayer.destroy();
        }
    }

    //重新播放
    private void replay() {
        stop();
        start();
    }

    private void savePlayerState() {
        if (mPlayer.isPlaying()) {
            //we pause the player for not playing on the background
            // 不可见，暂停播放器
            pause();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 重点:播放器没有停止,也没有暂停的时候,在activity的pause的时候也需要pause
        if (!isStopPlayer && !isPausePlayer && mPlayer != null) {
            Log.e(TAG, "onPause mpayer.");
            mPlayer.pause();
            isPausePlayer = true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        savePlayerState();
    }

    @Override
    protected void onDestroy() {
        stop();
        destroy();
        super.onDestroy();
    }

    private class MyFrameInfoListener implements  MediaPlayer.MediaPlayerFrameInfoListener {

        private WeakReference<VideoActivity> vodModeActivityWeakReference;

        public MyFrameInfoListener(VideoActivity vodModeActivity)
        {
            vodModeActivityWeakReference = new WeakReference<VideoActivity>(vodModeActivity);
        }
        @Override
        public void onFrameInfoListener() {
            VideoActivity vodModeActivity = vodModeActivityWeakReference.get();
            if(vodModeActivity != null) {
                vodModeActivity.updateLogInfo();
            }
        }
    }
    private void updateLogInfo() {
        Map<String, String> debugInfo = mPlayer.getAllDebugInfo();
        long createPts = 0;
        if (debugInfo.get("create_player") != null) {
            String time = debugInfo.get("create_player");
            createPts = (long) Double.parseDouble(time);
            logStrs.add(format.format(new Date(createPts)) + getString(R.string.log_player_create_success));
        }
        if (debugInfo.get("open-url") != null) {
            String time = debugInfo.get("open-url");
            long openPts = (long) Double.parseDouble(time) + createPts;
            logStrs.add(format.format(new Date(openPts)) + getString(R.string.log_open_url_success));
        }
        if (debugInfo.get("find-stream") != null) {
            String time = debugInfo.get("find-stream");
            long findPts = (long) Double.parseDouble(time) + createPts;
            logStrs.add(format.format(new Date(findPts)) + getString(R.string.log_request_stream_success));
        }
        if (debugInfo.get("open-stream") != null) {
            String time = debugInfo.get("open-stream");
            long openPts = (long) Double.parseDouble(time) + createPts;
            logStrs.add(format.format(new Date(openPts)) + getString(R.string.log_start_open_stream));
        }
        logStrs.add(format.format(new Date()) + getString(R.string.log_first_frame_played));
    }


    /**
     * 准备完成监听器:调度更新进度
     */
    private class VideoPreparedListener implements MediaPlayer.MediaPlayerPreparedListener {
        @Override
        public void onPrepared() {
            inSeek = false;
            showVideoProgressInfo();
        }
    }


    /**
     * 错误处理监听器
     */
    private class VideoErrorListener implements MediaPlayer.MediaPlayerErrorListener {

        @Override
        public void onError(int i, String s) {
            int errCode = 0;
            if (mPlayer == null) {
                return;
            }
            errCode = mPlayer.getErrorCode();
            switch (errCode) {
                case MediaPlayer.ALIVC_ERR_LOADING_TIMEOUT:
                    Log.i("mine", "onError:ALIVC_ERR_LOADING_TIMEOUT");
                    mPlayer.reset();
                    break;
                case MediaPlayer.ALIVC_ERR_READD:
                    mPlayer.reset();
                    break;
                default:
                    break;

            }
        }
    }

    /**
     * 信息通知监听器:重点是缓存开始/结束
     */
    private class VideoInfoListener implements MediaPlayer.MediaPlayerInfoListener {

        public void onInfo(int what, int extra) {
            switch (what) {
                case MediaPlayer.MEDIA_INFO_UNKNOW:
                    break;
                case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                    break;
                case MediaPlayer.MEDIA_INFO_BUFFERING_END:

                    break;
                case MediaPlayer.MEDIA_INFO_TRACKING_LAGGING:
                    break;
                case MediaPlayer.MEDIA_INFO_NETWORK_ERROR:
                    break;
                case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                    if (mPlayer != null)
                        Log.d(TAG, "on Info first render start : " + ((long) mPlayer.getPropertyDouble(AliVcMediaPlayer.FFP_PROP_DOUBLE_1st_VFRAME_SHOW_TIME, -1) - (long) mPlayer.getPropertyDouble(AliVcMediaPlayer.FFP_PROP_DOUBLE_OPEN_STREAM_TIME, -1)));

                    break;
            }
        }
    }

    /**
     * 快进完成监听器
     */
    private class VideoSeekCompleteListener implements MediaPlayer.MediaPlayerSeekCompleteListener {

        public void onSeekCompleted() {
            inSeek = false;
        }
    }

    /**
     * 视频播完监听器
     */
    private class VideoCompleteListener implements MediaPlayer.MediaPlayerCompletedListener {

        public void onCompleted() {

        }
    }

    /**
     * 视频大小变化监听器
     */
    private class VideoSizeChangeListener implements MediaPlayer.MediaPlayerVideoSizeChangeListener {

        public void onVideoSizeChange(int width, int height) {

        }
    }

    /**
     * 视频缓存变化监听器: percent 为 0~100之间的数字】
     */
    private class VideoBufferUpdateListener implements MediaPlayer.MediaPlayerBufferingUpdateListener {

        public void onBufferingUpdateListener(int percent) {

        }
    }

    private class VideoStoppedListener implements MediaPlayer.MediaPlayerStopedListener {
        @Override
        public void onStopped() {

            isStopPlayer = true;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {                //转为竖屏了。
            //显示状态栏
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mSurfaceView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            //设置view的布局，宽高之类
            ViewGroup.LayoutParams surfaceViewLayoutParams = flContain.getLayoutParams();
            surfaceViewLayoutParams.height = (int) (ScreenUtils.getScreenWidth(this) * 9.0f / 16);
            surfaceViewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;

        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {                //转到横屏了。
            //隐藏状态栏
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            flContain.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN);
            //设置view的布局，宽高
            ViewGroup.LayoutParams surfaceViewLayoutParams = flContain.getLayoutParams();
            surfaceViewLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            surfaceViewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;

        }
    }

    private void permissionCheck() {
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (String permission : PERMISSIONS_STORAGE) {
            if (PermissionChecker.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionCheck = PackageManager.PERMISSION_DENIED;
            }
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_READ_PHONE);
        }
    }

    protected boolean checkOnePermission(String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_READ_PHONE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    ToastUtils.showShortToast("请在设置中获取权限");
                }
                break;
        }

    }
}
