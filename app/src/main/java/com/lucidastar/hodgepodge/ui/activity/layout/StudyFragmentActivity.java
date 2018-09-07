package com.lucidastar.hodgepodge.ui.activity.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.layout.fragment.LifeFragment;
import com.lucidastar.hodgepodge.ui.activity.layout.fragment.PhotoFragment;
import com.lucidastar.hodgepodge.view.CustomDialogFragment;
import com.mine.lucidastarutils.log.KLog;
import com.mine.lucidastarutils.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudyFragmentActivity extends AppCompatActivity {

    @BindView(R.id.btn_fragment_life)
    Button mBtnFragmentLife;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.btn_add_fragment)
    Button mBtnAddFragment;
    @BindView(R.id.btn_remove_fragment)
    Button mBtnRemoveFragment;
    private String[] lifeStr = {"onAttach", "onCreate", "onCreateView", "onViewCreate", "onActivityCreate",
            "onStart", "onResume", "onPause", "onStop", "onDestroyView", "onDestroy", "onDetach"};
    private FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    private LifeFragment lifeFragment = new LifeFragment();
    @BindView(R.id.vp_test)
    ViewPager mViewPager;
    private String[] mPhotoUrlStr = {"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536321321034&di=73f787e06ea852782d6d336799787635&imgtype=0&src=http%3A%2F%2Ffa.topitme.com%2Fa%2Fa2%2F8a%2F1130147841d058aa2al.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536321381382&di=60700e109fdf38c7eeb8129fd4abbc38&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F0b46f21fbe096b63b8f6858406338744eaf8ac73.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536321381382&di=526e298791a3349bdc479a6836625887&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F11%2F99%2F17%2F90q58PICeia.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536321381381&di=9d0ff97809ca30987ee2ded80feb4934&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F12%2F39%2F42%2F35958PICHZw.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536321381381&di=10201135cda5dddb7af5a6116f6ea806&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F11%2F27%2F08%2F09j58PICarX.jpg"
    };
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_fragment);
        ButterKnife.bind(this);
        mToolbar.setTitle("学习Fragment");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initData();
    }

    private void initData() {
        int i = 0;
        for (String url : mPhotoUrlStr) {
            mFragments.add(PhotoFragment.getInstance(url,i++));
        }
        mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
    }


    @OnClick({R.id.btn_add_fragment, R.id.btn_remove_fragment, R.id.btn_fragment_life})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_fragment:
                if (fragmentTransaction.isEmpty()) {
                    fragmentTransaction.add(R.id.fl_container, lifeFragment).commit();
                }
                break;
            case R.id.btn_remove_fragment:
                getSupportFragmentManager().beginTransaction().remove(lifeFragment).commit();
                break;
            case R.id.btn_fragment_life:
                StringBuilder stringBuilder = new StringBuilder();
                for (String lifeName : lifeStr) {
                    stringBuilder.append(lifeName).append(";");
                }
                CustomDialogFragment customDialogFragment = CustomDialogFragment.getInstance(stringBuilder.toString(), "生命周期", false);
                customDialogFragment.show(getSupportFragmentManager(), "lifeFragment");
                break;
        }
    }

    private class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

}
