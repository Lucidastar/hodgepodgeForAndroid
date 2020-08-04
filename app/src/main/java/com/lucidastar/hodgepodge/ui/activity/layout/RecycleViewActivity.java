package com.lucidastar.hodgepodge.ui.activity.layout;

import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;

import androidx.appcompat.app.AppCompatActivity;
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
        mUserInfoAdapter.setOnClickFabulousListener(new UserInfoAdapter.OnClickFabulousListener() {
            @Override
            public void clickFabulous(View view, int position) {
                User user = mUserList.get(position);
                changeUserInfo(position);
                ToastUtils.showShortToast("位置：" + position);
                KLog.i(user.toString());

            }
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
//        mUserInfoAdapter.notifyDataSetChanged();


    }

    private void initData() {
         manager = new LinearLayoutManager(this);
        mRcvUser.setLayoutManager(manager);
        mUserList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
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
    }

    public void notifyItem(View view) {
        //添加一条数据
        User newUser = new User();
        newUser.setName("newUser");
        newUser.setAge("20岁");
        newUser.setFabulous(12);
        newUser.setFabulous(false);
        newUser.setHead("https://mmbiz.qpic.cn/mmbiz_jpg/iccib9G9IAFPhSwdibNCmTBzKD57YoqXpT0HhDKLNX4dVHIo44H5IiaOOHxpQUw0c8Yn63vGNBxyJPfYsqpraxiaeyQ/0?wx_fmt=jpeg");
        mUserList.add(0,newUser);
//        mUserInfoAdapter.notifyItemInserted(0);
        mUserInfoAdapter.notifyDataSetChanged();
        KLog.i("大小"+mUserList.size());



    }

    public void clearData(View view) {
        mUserList.clear();
        mUserInfoAdapter.notifyDataSetChanged();
    }
}
