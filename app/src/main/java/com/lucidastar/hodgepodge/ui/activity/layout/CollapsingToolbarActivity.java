package com.lucidastar.hodgepodge.ui.activity.layout;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lucidastar.hodgepodge.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollapsingToolbarActivity extends AppCompatActivity {

    //https://blog.csdn.net/u012045061/article/details/69568807
    //https://github.com/CTSN/TestCollapsingToolbarLayout
    /*NestedScrollView mNestedScrollView;
    private float totalHeight;      //总高度
    private float toolBarHeight;    //toolBar高度
    private float offSetHeight;     //总高度 -  toolBar高度  布局位移值
    private float llHeight;         //搜索框高度

    private float llHeightOffScale;     //高度差比值
    private float llOffDistance;        //距离差
    private float llOffDistanceScale;   //距离差比值
    private FrameLayout.LayoutParams params;

    private float mSelfHeight = 0;//用以判断是否得到正确的宽高值
    private float mHeadImgScale;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    EditText fab;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.bac)
    TextView bac;
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.iv_head)
    ImageView mHeadImage;*/

    private float mSelfHeight = 0;//用以判断是否得到正确的宽高值
    private float mTitleScale;
    private float mSubScribeScale;
    private float mSubScribeScaleX;
    private float mHeadImgScale;

    @BindView(R.id.iv_head)
    ImageView mHeadImage;
    @BindView(R.id.subscription_title)
    TextView mSubscriptionTitle;
    @BindView(R.id.subscribe)
    TextView mSubscribe;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
        ButterKnife.bind(this);
//        mNestedScrollView = (NestedScrollView) findViewById(R.id.ncv);
//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setTitle("");
//        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        bac.setAlpha(0f);
//        totalHeight = getResources().getDimension(R.dimen.ll_search);
//        toolBarHeight = getResources().getDimension(R.dimen.tool_bar_height);
//        offSetHeight = totalHeight - toolBarHeight;

        /**
         *   移动效果值／最终效果值 =  移动距离／ 能移动总距离（确定）
         */
        /*appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                //第一次进入获取高度，以及差值 高度差比值
                if (llHeight == 0){
                    llHeight = ll.getMeasuredHeight();
                    params = (FrameLayout.LayoutParams) ll.getLayoutParams();

                    //算出高度偏移量比值  相对与llHeight
                    llHeightOffScale = 1.0f - (toolBarHeight / llHeight);

                    //得到滑动差值 就是布局marginTop
                    llOffDistance = params.topMargin;
                    //得到滑动比值
                    llOffDistanceScale = llOffDistance / offSetHeight;
                }

                //滑动一次 得到渐变缩放值
                float alphaScale = (-verticalOffset) / offSetHeight;

                //获取高度缩放值
                float llHeightScale = 1.0f-(llHeightOffScale*((-verticalOffset)/offSetHeight));
                //计算maigintop值
                float distance = llOffDistance - (-verticalOffset)*llOffDistanceScale;

                image.setAlpha(1.0f-alphaScale);
                bac.setAlpha(alphaScale);
                params.height = (int)(llHeight* llHeightScale);
                params.setMargins(0,(int)distance,0,0);

                fl.requestLayout();


                mHeadImage.setScaleX(1.0f-alphaScale);
                mHeadImage.setScaleY(1.0f-alphaScale);
                mHeadImage.setTranslationY(mHeadImgScale * verticalOffset);


            }
        });
*/

        final float screenW = getResources().getDisplayMetrics().widthPixels;
        final float toolbarHeight = getResources().getDimension(R.dimen.toolbar_height);
        final float initHeight = getResources().getDimension(R.dimen.ll_search);
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (mSelfHeight == 0) {
                    mSelfHeight = mSubscriptionTitle.getHeight();
                    float distanceTitle = mSubscriptionTitle.getTop() + (mSelfHeight - toolbarHeight) / 2.0f;
                    float distanceSubscribe = mSubscribe.getY() + (mSubscribe.getHeight() - toolbarHeight) / 2.0f;
                    float distanceHeadImg = mHeadImage.getY() + (mHeadImage.getHeight() - toolbarHeight) / 2.0f;
                    float distanceSubscribeX = screenW / 2.0f - (mSubscribe.getWidth() / 2.0f + getResources().getDimension(R.dimen.normal_space));
                    mTitleScale = distanceTitle / (initHeight - toolbarHeight);
                    mSubScribeScale = distanceSubscribe / (initHeight - toolbarHeight);
                    mHeadImgScale = distanceHeadImg / (initHeight - toolbarHeight);
                    mSubScribeScaleX = distanceSubscribeX / (initHeight - toolbarHeight);
                }
                float scale = 1.0f - (-verticalOffset) / (initHeight - toolbarHeight);
                mHeadImage.setScaleX(scale);
                mHeadImage.setScaleY(scale);
                mHeadImage.setTranslationY(mHeadImgScale * verticalOffset);
                mSubscriptionTitle.setTranslationY(mTitleScale * verticalOffset);
                mSubscribe.setTranslationY(mSubScribeScale * verticalOffset);
                mSubscribe.setTranslationX(-mSubScribeScaleX * verticalOffset);
            }
        });
    }
}
