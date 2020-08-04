package com.lucidastar.hodgepodge.ui.activity.otherfeature.view;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.ui.activity.otherfeature.view.cusview.RadarView;
import com.mine.lucidastarutils.utils.ScreenUtils;

public class StudyViewActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    RadarView mRadarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_view);
        mRadarView = findViewById(R.id.rdv);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置title
        mToolbar.setTitle("view的一些知识点");
        //设置副标题
//        mToolbar.setSubtitle("subTitle");
        //设置logo
//        mToolbar.setLogo(R.mipmap.ic_launcher);
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
        initRadarView();


    }

    private void initRadarView(){
        mRadarView.setCount(5);
        mRadarView.setLayerCount(4);
        mRadarView.setEnabledShade(true);//绘制渐变环
        mRadarView.setEnabledRegionShader(true);//允许区域颜色渐变
        mRadarView.setEnabledShowPoint(true);//显示圆点
        mRadarView.setStartColor(Color.parseColor("#9bc6f5"));//开启渐变色时，圆心处的颜色
        mRadarView.setEndColor(Color.parseColor("#e8f0fa"));//开启渐变色时，外圈处的颜色
        mRadarView.setLineColor(Color.parseColor("#ff0000"));//圆心与各个顶点连线的颜色

        //圆点空心填充白色  圆点与圆点线的颜色
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(ScreenUtils.px2dp(this,24));
        mPaint.setColor(Color.parseColor("#ffffff"));
        mRadarView.setPointColor(mPaint);//圆点颜色
        mRadarView.setEnabledBorder(true);
        mRadarView.setBorderColor(Color.parseColor("#2269BB"));//圆点与圆点之间的连线
        mRadarView.setTitles(new String[]{"意识","实战","知识","专业","经验"});


        mRadarView.setCobwebColor(Color.parseColor("#00000000"));
        mRadarView.setRadiusLineWidth(ScreenUtils.px2dp(this,24));

        mRadarView.setDataSize(60);


        mRadarView.setValues(new String[]{"12.5","26","58","45","15"});
//        mRadarView.setDrawables(new int[]{12,26,58,45,15});
        double percents[] = new double[]{0.8, 0.8, 0.9, 1, 0.6};
        mRadarView.setPercents(percents);
        mRadarView.setEnabledText(true);
        mRadarView.setEnabledRadius(true);
        mRadarView.setPointRadius(12);
        mRadarView.setDataColor(Color.parseColor("#7498FF"));
    }


}
