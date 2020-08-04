package com.lucidastar.hodgepodge.view;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.lucidastar.hodgepodge.R;

/**
 * Created by qiuyouzone on 2017/9/26.
 */

public class CalendarDayTextView extends AppCompatTextView {

    public boolean isToday = false;
    LayoutInflater inflater;
    LinearLayout llCalendarDay;
    TextView tvDay;
    TextView tvPlaceNum;
    String mDay;
    String mPlaceNum;
    public CalendarDayTextView(Context context) {
        super(context);
        initControl(context);
    }

    public CalendarDayTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public CalendarDayTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context);
    }

    private void initControl(Context context) {
        inflater = LayoutInflater.from(context);
        llCalendarDay = (LinearLayout) inflater.inflate(R.layout.calendar_day,null);
        tvDay = (TextView) llCalendarDay.findViewById(R.id.tv_day);
        tvPlaceNum = (TextView) llCalendarDay.findViewById(R.id.tv_place_num);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (isToday){
            llCalendarDay.setBackgroundResource(R.color.colorAccent);
        }
        if (!TextUtils.isEmpty(mDay)){
            tvDay.setText(mDay);
        }

        if (!TextUtils.isEmpty(mPlaceNum)){
            tvPlaceNum.setText(mPlaceNum);
        }
//        super.onDraw(canvas);
    }

    public void setmDay(String mDay) {
        this.mDay = mDay;
        invalidate();
    }

    public void setmPlaceNum(String mPlaceNum) {
        this.mPlaceNum = mPlaceNum;
        invalidate();
    }
}