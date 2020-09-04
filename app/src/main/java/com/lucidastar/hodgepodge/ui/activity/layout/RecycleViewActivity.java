package com.lucidastar.hodgepodge.ui.activity.layout;

import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.layout.adapter.UserInfoAdapter;
import com.lucidastar.hodgepodge.ui.activity.layout.model.User;
import com.mine.lucidastarutils.log.KLog;
import com.mine.lucidastarutils.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecycleViewActivity extends AppCompatActivity {

    private UserInfoAdapter mUserInfoAdapter;
    private RecyclerView mRcvUser;
    private List<User> mUserList;
    LinearLayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mUserInfoAdapter.setOnClickFabulousListener((view, position) -> {
            User user = mUserList.get(position);
            changeUserInfo(position);
            ToastUtils.showShortToast("位置：" + position);
            KLog.i(user.toString());

        });

        mRcvUser.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                KLog.i("onScrollStateChanged"+newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                KLog.i(dy+"");
            }
        });


    }

    private void changeUserInfo(int position) {
        User user = mUserList.get(position);
        user.setFabulous(!user.isFabulous());
        user.setFabulous(user.isFabulous() ? user.getFabulous() + 1 : user.getFabulous() - 1);
        mUserList.set(position, user);
        mUserInfoAdapter.notifyItemChanged(position);


    }

    private void initData() {
         manager = new LinearLayoutManager(this);
        mRcvUser.setLayoutManager(manager);
        mUserList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            User user = new User();
            user.setName("测试" + i);
            user.setAge(random.nextInt(80) + "岁");
            user.setFabulous(i);
            user.setFabulous(random.nextBoolean());
            user.setHead("https://mmbiz.qpic.cn/mmbiz_jpg/iccib9G9IAFPhSwdibNCmTBzKD57YoqXpT0HhDKLNX4dVHIo44H5IiaOOHxpQUw0c8Yn63vGNBxyJPfYsqpraxiaeyQ/0?wx_fmt=jpeg");
            mUserList.add(user);
        }

        mUserInfoAdapter = new UserInfoAdapter(mUserList, this);
        mRcvUser.setAdapter(mUserInfoAdapter);
    }

    private void initView() {
        mRcvUser = findViewById(R.id.rcv_user);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置title
        mToolbar.setTitle("RecycleView的使用");
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

    }

    public void notifyItemInsertedClick(View view) {
        //添加一条数据
        mUserList.add(createUser());
        mUserInfoAdapter.notifyItemInserted(mUserList.size());
//        mUserInfoAdapter.notifyDataSetChanged();
        KLog.i("大小"+mUserList.size());

    }

    public void clearData(View view) {
        mUserList.clear();
        mUserInfoAdapter.notifyDataSetChanged();
    }

    private User createUser(){
        User user = new User();
        user.setName("新的测试"+mUserList.size());
        user.setAge("12");
        user.setFabulous(1);
        user.setFabulous(false);
        user.setHead("https://mmbiz.qpic.cn/mmbiz_jpg/iccib9G9IAFPhSwdibNCmTBzKD57YoqXpT0HhDKLNX4dVHIo44H5IiaOOHxpQUw0c8Yn63vGNBxyJPfYsqpraxiaeyQ/0?wx_fmt=jpeg");
        return user;
    }
}
