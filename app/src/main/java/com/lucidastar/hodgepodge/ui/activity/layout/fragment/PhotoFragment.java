package com.lucidastar.hodgepodge.ui.activity.layout.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.base.LazyLoadBaseFragment;
import com.mine.lucidastarutils.log.KLog;

public class PhotoFragment extends LazyLoadBaseFragment {

    private String mPhotoUrl;
    private ImageView mIvTest;
    private int mPosition;
    public PhotoFragment() {

    }

    public static PhotoFragment getInstance(){
        PhotoFragment photoFragment = new PhotoFragment();
        return photoFragment;
    }
    public static PhotoFragment getInstance(String url){
        PhotoFragment photoFragment = new PhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("photoUrl",url);
        photoFragment.setArguments(bundle);
        return photoFragment;
    }
    public static PhotoFragment getInstance(String url,int position){
        PhotoFragment photoFragment = new PhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("photoUrl",url);
        bundle.putInt("position",position);
        photoFragment.setArguments(bundle);
        return photoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null){
            mPhotoUrl = arguments.getString("photoUrl");
            mPosition = arguments.getInt("position");
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_photo;
    }

    @Override
    protected void initView(View rootView) {
        mIvTest = rootView.findViewById(R.id.iv_test);
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        KLog.i(getClass().getSimpleName() + "位置"+mPosition+"====  对用户可见");
    }

    @Override
    public void onFragmentPause() {
        super.onFragmentPause();
        KLog.i(getClass().getSimpleName() + "位置"+mPosition+"====  对用户不可见");
    }

    @Override
    public void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        KLog.i(getClass().getSimpleName() + "位置"+mPosition+"====  对用户第一次可见");
//        Glide.with(this).load(mPhotoUrl).into(mIvTest);
    }
}
