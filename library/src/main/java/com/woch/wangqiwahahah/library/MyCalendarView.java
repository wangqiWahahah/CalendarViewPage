package com.woch.wangqiwahahah.library;


import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangqiWahahah on 2017/6/6.
 */

public class MyCalendarView extends LinearLayout implements View.OnClickListener{

    private RelativeLayout rl_title;
    private ViewPager m_vp_calendar;
    private RecyclerView m_rv_extend;
    private TextView tv_month, tv_week, tv_year;

    private int show_year, show_month = 0;
    private int m_position = 100;

    private CalendarPageAdapter calendarPageAdapter;

    public MyCalendarView(Context context) {
        super(context);
        this.setOrientation(LinearLayout.VERTICAL);
        //init(context);
    }

    public MyCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(LinearLayout.VERTICAL);
        //init(context);
    }

    public MyCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOrientation(LinearLayout.VERTICAL);
        //init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyCalendarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.setOrientation(LinearLayout.VERTICAL);
        //init(context);
    }


    private void init(){

        View view = LayoutInflater.from(getContext()).inflate(R.layout.calendar_layout, null, false);
        rl_title = (RelativeLayout) view.findViewById(R.id.rl_title);
        m_vp_calendar = (ViewPager) view.findViewById(R.id.m_vp_calendar);
        tv_month = (TextView) rl_title.findViewById(R.id.tv_month);
        tv_week = (TextView) rl_title.findViewById(R.id.tv_week);
        tv_year = (TextView) rl_title.findViewById(R.id.tv_year);
        //m_rv_extend = (RecyclerView) view.findViewById(R.id.m_rv_extend);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if (show_year==0 || show_month==0){
            show_year = calendar.get(Calendar.YEAR);
            show_month = calendar.get(Calendar.MONTH) + 1;
        }
        calendarPageAdapter = new CalendarPageAdapter(getContext(), show_year, show_month, -1);

        m_vp_calendar.setAdapter(calendarPageAdapter);

        m_vp_calendar.setCurrentItem(m_position);

        int day = calendar.get(Calendar.DAY_OF_MONTH);

        tv_year.setText(show_year+"");
        tv_month.setText(show_month+"");
        Calendar calendar_P = Calendar.getInstance();
        calendar_P.set(show_year, (show_month-1), day);
        tv_week.setText(CalendarUtils.getInstance().getChineseWeek(calendar_P));

        addView(view, 0, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setListener();

    }

    public void setShowTime(int year, int month){

        if(year < 1970 || month <= 0){
            throw new IllegalArgumentException("time is wrong...");
        }
        this.show_year = year;
        this.show_month = month;
        init();

    }


    private void setListener(){

        rl_title.setOnClickListener(this);
        m_vp_calendar.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.i("MyCalendarView", position+"--------onPageScrolled-----------"+positionOffset+"---------"+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

                int m_year = show_year;
                int m_month = show_month;
                int[] year_month = CalendarUtils.getInstance().tryToCalculateMonth(m_position, position, show_year, show_month, m_year, m_month);
                m_year = year_month[0];
                m_month = year_month[1];
                tv_year.setText(m_year+"");
                tv_month.setText(m_month+"");
                Calendar calendar_P = Calendar.getInstance();
                calendar_P.set(m_year, (m_month-1), CalendarUtils.getInstance().getTodayOfMonth());
                tv_week.setText(CalendarUtils.getInstance().getChineseWeek(calendar_P));
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public void onClick(View v) {



    }




}
