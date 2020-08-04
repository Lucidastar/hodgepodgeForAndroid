package com.lucidastar.hodgepodge.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lucidastar.hodgepodge.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by qiuyouzone on 2017/9/26.
 */

public class NewCalendar extends LinearLayout {

    private ImageView btnPrev, btnNext;
    private TextView txtDate;
    private GridView grid;

    private Calendar curDate = Calendar.getInstance();

    private String displayFormat;

    public NewCalendarListener listener;

    public NewCalendar(Context context) {
        super(context);
    }

    public NewCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public NewCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    private void initControl(Context context, AttributeSet attrs) {
        bindControl(context);
        bindControlEvent();


        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.NewCalendar);

        try {
            String format = ta.getString(R.styleable.NewCalendar_dateFormat);
            displayFormat = format;
            if (displayFormat == null) {
                displayFormat = "MMM yyyy";
            }
        } finally {
            ta.recycle();
        }
        renderCalendar();
    }

    /**
     * 绑定事件的处理
     */
    private void bindControlEvent() {

        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                curDate.add(Calendar.MONTH, -1);
                renderCalendar();
            }
        });

        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                curDate.add(Calendar.MONTH, 1);
                renderCalendar();
            }
        });
    }

    /**
     * 渲染
     */
    private void renderCalendar() {
        SimpleDateFormat sdf = new SimpleDateFormat(displayFormat);
        txtDate.setText(sdf.format(curDate.getTime()));

        ArrayList<DayAndPlace> cells = new ArrayList<>();
        Calendar calendar = (Calendar) curDate.clone();

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int prevDays = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        calendar.add(Calendar.DAY_OF_MONTH, -prevDays);

        int maxCellCount = 6 * 7;
        while (cells.size() < maxCellCount) {
            DayAndPlace dayAndPlace = new DayAndPlace();
            dayAndPlace.date = calendar.getTime();
//            cells.add(calendar.getTime());
            cells.add(dayAndPlace);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        grid.setAdapter(new CalendarAdapter(getContext(), cells));
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener == null) {
                    return false;
                } else {
                    listener.onItemLongPress((Date) parent.getItemAtPosition(position));
                    return true;
                }
            }
        });
    }

    private class CalendarAdapter extends ArrayAdapter<DayAndPlace> {

        LayoutInflater inflater;

        public CalendarAdapter(@NonNull Context context, ArrayList<DayAndPlace> days) {
//            super(context, R.layout.calendar_text_day, days);
            super(context, R.layout.calendar_day, days);
            inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

//            Date date = getItem(position);
            DayAndPlace item = getItem(position);
            if (convertView == null) {
//                convertView = inflater.inflate(R.layout.calendar_text_day, parent, false);
                convertView = inflater.inflate(R.layout.calendar_day, parent, false);
            }

            int day = item.date.getDate();
//            ((TextView) convertView).setText(String.valueOf(day));
            ((TextView) convertView.findViewById(R.id.tv_day)).setText(String.valueOf(day));

//            ((CalendarDayTextView) convertView).setmDay(String.valueOf(day));
            Date now = new Date();
            boolean isTheSameMonth = false;
            if (item.date.getMonth() == now.getMonth()) {
                isTheSameMonth = true;
            }
            if (isTheSameMonth) {
                ((TextView) convertView.findViewById(R.id.tv_day)).setTextColor(Color.parseColor("#000000"));
            } else {
                ((TextView) convertView.findViewById(R.id.tv_day)).setTextColor(Color.parseColor("#666666"));
            }

            if (now.getDate() == item.date.getDate() && now.getMonth() == item.date.getMonth()
                    && now.getYear() == item.date.getYear()) {
                ((TextView) convertView.findViewById(R.id.tv_day)).setTextColor(Color.parseColor("#ff0000"));
//                ((CalendarDayTextView) convertView).isToday = true;
            }
            return convertView;
        }
    }

    /**
     * 绑定事件
     *
     * @param context
     */
    private void bindControl(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.calendar_view, this);

        btnPrev = (ImageView) findViewById(R.id.btnPrev);
        btnNext = (ImageView) findViewById(R.id.btnNext);
        txtDate = (TextView) findViewById(R.id.txtDate);
        grid = (GridView) findViewById(R.id.calendar_grid);

    }

    public interface NewCalendarListener {
        void onItemLongPress(Date day);
    }


    class DayAndPlace{
        Date date;
        String placeNum;
    }


}