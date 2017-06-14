package com.woch.wangqiwahahah.library;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by wangqiWahahah on 2017/6/7.
 */

public class CalendarMonthView extends ViewGroup{

    private int Max_width = 0;
    private int month_width = 0;

    private LayoutParams layoutParams;

    private int show_year, show_month;
    private CalendarDataControl calendarDataControl;

    public CalendarMonthView(Context context, CalendarDataControl calendarDataControl) {
        super(context);
        this.calendarDataControl = calendarDataControl;
    }

    public CalendarMonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarMonthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CalendarMonthView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setPositionMonth(int year, int month){

        if(year < 1970 || month <= 0){
            return;
        }

        show_year = year;
        show_month = month;

        if (Max_width > 0){
            month_width = Max_width/7;
            initChildrenLayoutParams();
        }

        initView();

    }

    public void setReLayout(int Max_width){

        this.Max_width = Max_width;

    }

    private void initChildrenLayoutParams() {

        if (month_width > 0){
            layoutParams = new LayoutParams(month_width, month_width);
        }

    }

    private Calendar calendar;
    private int mDayOfWeekStart, mFirstDayOfWeek;

    private void initView(){

        removeAllViews();
        if (Max_width == 0){
            addView(new View(getContext()));
            return;
        }

        calendar = Calendar.getInstance();
        calendar.set(show_year, (show_month-1), 1);
        mDayOfWeekStart = calendar.get(Calendar.DAY_OF_WEEK); // 这月第一天是星期几
        mFirstDayOfWeek = calendar.getFirstDayOfWeek(); // 星期的第一天是什么
        int month_day_count = CalendarUtils.getInstance().getMonthOfDayCount(show_year, (show_month-1)); // 一个月有几天
        int offset = getOffset(mDayOfWeekStart, mFirstDayOfWeek);

        for (int i=1; i<=month_day_count; i++){

            View view = LayoutInflater.from(getContext()).inflate(R.layout.calendar_month_item, null, false);
            view.setLayoutParams(layoutParams);
            view.setVisibility(VISIBLE);
            final RelativeLayout rl_month_item = (RelativeLayout) view.findViewById(R.id.rl_month_item);
            TextView tv_day = (TextView) view.findViewById(R.id.tv_day);
            TextView tv_holiday = (TextView) view.findViewById(R.id.tv_holiday);
            TextView tv_chinese_day = (TextView) view.findViewById(R.id.tv_chinese_day);
            //tv_chinese_day.setLayoutParams(params_chinese_day);
            tv_day.setText(i+"");
            //tv_holiday.setVisibility(GONE);
            tv_chinese_day.setText(LunarCalendar.getInstance().getLunarDate(show_year,(show_month-1),i,false));

//            if (CalendarUtils.getInstance().getTodayOfMonth() == i){
//
//                if (CalendarUtils.getInstance().getMonthOfYear() == (show_month-1) && CalendarUtils.getInstance().getYear() == show_year){
//
//                    rl_month_item.setBackgroundResource(R.drawable.circle_red);
//                    //rl_month_item.setBackground();
//                    Log.i("CalendarMonthView", "-------------circle_red--------");
//
//                }else {
//
//                    rl_month_item.setBackgroundResource(R.drawable.circle_gray);
//                    Log.i("CalendarMonthView", "-------------circle_gray--------");
//
//                }
//
//            }

            if (CalendarUtils.getInstance().getTodayOfMonth() == i){
                if (getIsToday(i)){
                    rl_month_item.setBackgroundResource(R.drawable.circle_red);
                }else {
                    rl_month_item.setBackgroundResource(R.drawable.circle_gray);
                }
            }

            final int m_position = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    //calendarDataControl.getClickDate(show_year+"-"+show_month+"-"+m_position, );
                    if (getIsToday(m_position)){
                        rl_month_item.setBackgroundResource(R.drawable.hollow_circle_today_gray);
                    }else {
                        rl_month_item.setBackgroundResource(R.drawable.hollow_circle_gray);
                    }

                }
            });

            //Log.i("CalendarMonthView", LunarCalendar.getInstance().getLunarDate(show_year,(show_month-1),i,false)+"-------------initView--------"+i);
            //addView(view);
            addView(view, i-1, layoutParams);

        }


        Log.i("CalendarMonthView", "-------------initView--------");

    }

    private boolean getIsToday(int i){

        boolean isToday = false;

        if (CalendarUtils.getInstance().getTodayOfMonth() == i){

            if (CalendarUtils.getInstance().getMonthOfYear() == (show_month-1) && CalendarUtils.getInstance().getYear() == show_year){

                //rl_month_item.setBackground();
                Log.i("CalendarMonthView", "-------------circle_red--------");
                isToday = true;

            }else {

                Log.i("CalendarMonthView", "-------------circle_gray--------");
                isToday = false;

            }

        }else {
            isToday = false;
        }


        return isToday;

    }

    private int getOffset(int mDayOfWeekStart, int mFirstDayOfWeek){

        return mDayOfWeekStart < mFirstDayOfWeek ? (mDayOfWeekStart + 7) : (mDayOfWeekStart - mFirstDayOfWeek);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //Calendar.getInstance().

        l = 0;
        t = 0;

        int mPosX = l;
        int mPosY = t;
        int count = getChildCount();
        int offset = getOffset(mDayOfWeekStart, mFirstDayOfWeek);
        for (int i=offset;i<count+offset;i++){

            int viewPos = i-offset;
            View view = getChildAt(viewPos);
            view.setVisibility(VISIBLE);
            int height = view.getMeasuredHeight();
            int width = view.getMeasuredWidth();
            mPosX = l + i%7*month_width;
            mPosY = t + i/7*month_width;
            view.layout(mPosX, mPosY, mPosX+month_width, mPosY+month_width);
            //Log.i("CalendarMonthView", width+"-------------onLayout---offset--------"+offset + "-----mPosX-------" + mPosX);
            //mPosX = mPosX

        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (Max_width == 0){
            int width = measureWidth(widthMeasureSpec);
            if (width > 0){
                Max_width = width;

                //一些判定条件。。
                setPositionMonth(show_year, show_month);

            }
        }


        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), month_width * 6);
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(month_width, month_width);
        }

    }

    private int measureWidth(int measureSpec) {

        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            // result = (int) mTextPaint.measureText(mText) + getPaddingLeft()
            // + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by
                // measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;
    }


}
