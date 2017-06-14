package com.woch.wangqiwahahah.library;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
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
    private BeforeClickDate beforeClickDate;
    private int Today = 1;
    private int Common = 0;
    private int OutsideDay = 2;
    private FunctionConfig functionConfig;

    private int beforeYear;
    private int beforeMonth;

    private int afterYear;
    private int afterMonth;

    private ViewPager viewPager;
    private int now_Postion;

    public CalendarMonthView(Context context, CalendarDataControl calendarDataControl, BeforeClickDate beforeClickDate, FunctionConfig functionConfig) {
        super(context);
        this.calendarDataControl = calendarDataControl;
        this.beforeClickDate = beforeClickDate;
        this.functionConfig = functionConfig;
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

    public void setViewPage(ViewPager viewPager, int now_Postion){

        this.viewPager = viewPager;
        this.now_Postion = now_Postion;

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
            //view.setVisibility(VISIBLE);
            final RelativeLayout rl_month_item = (RelativeLayout) view.findViewById(R.id.rl_month_item);
            TextView tv_day = (TextView) view.findViewById(R.id.tv_day);
            TextView tv_holiday = (TextView) view.findViewById(R.id.tv_holiday);
            TextView tv_chinese_day = (TextView) view.findViewById(R.id.tv_chinese_day);
            //tv_chinese_day.setLayoutParams(params_chinese_day);
            tv_day.setText(i+"");
            //tv_holiday.setVisibility(GONE);
            if (functionConfig.isSHOW_CHINA_DATE()){
                tv_chinese_day.setVisibility(VISIBLE);
                tv_chinese_day.setText(LunarCalendar.getInstance().getLunarDate(show_year, show_month,i,false));
            }else {
                tv_chinese_day.setVisibility(GONE);
            }

            if (beforeClickDate != null){

                if (!StringUtils.isEmpty(beforeClickDate.before_date)){

                    if (beforeClickDate.year == show_year && beforeClickDate.month == show_month && beforeClickDate.day == i){
                        if (beforeClickDate.type == Today){

                            rl_month_item.setBackgroundResource(R.drawable.hollow_circle_today_gray);

                        }else if (beforeClickDate.type == OutsideDay){

                            rl_month_item.setBackgroundResource(R.drawable.hollow_circle_gray);

                        }else if (beforeClickDate.type == Common){

                            rl_month_item.setBackgroundResource(R.drawable.hollow_circle_gray);

                        }
                        beforeClickDate.before_view = rl_month_item;

                    }


                }

            }

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
//                    if (!StringUtils.isEmpty(before_date)){
//
//
//                    }
                    if (beforeClickDate == null){
                        beforeClickDate = new BeforeClickDate();
                    }

                    if (beforeClickDate.before_view != null){

                        //beforeClickDate.before_view.setBackgroundResource(R.color.white);
                        if (beforeClickDate.type == Today){

                            beforeClickDate.before_view.setBackgroundResource(R.drawable.circle_red);

                        }else if (beforeClickDate.type == OutsideDay){

                            beforeClickDate.before_view.setBackgroundResource(R.drawable.circle_gray);

                        }else if (beforeClickDate.type == Common){

                            beforeClickDate.before_view.setBackgroundResource(R.color.white);

                        }

                    }
                    if (getIsToday(m_position)){
                        rl_month_item.setBackgroundResource(R.drawable.hollow_circle_today_gray);
                        beforeClickDate.type = 1;
                    }else {
                        if (CalendarUtils.getInstance().getTodayOfMonth() == m_position){
                            beforeClickDate.type = 2;
                        }else {
                            beforeClickDate.type = 0;
                        }
                        rl_month_item.setBackgroundResource(R.drawable.hollow_circle_gray);
                    }
                    beforeClickDate.before_view = rl_month_item;
                    beforeClickDate.before_date = show_year+"-"+show_month+"-"+m_position;
                    beforeClickDate.year = show_year;
                    beforeClickDate.month = show_month;
                    beforeClickDate.day = m_position;

                    calendarDataControl.getClickDate(beforeClickDate.before_date);

                }
            });

            //Log.i("CalendarMonthView", LunarCalendar.getInstance().getLunarDate(show_year,(show_month-1),i,false)+"-------------initView--------"+i);
            //addView(view);

            Log.i("CalendarMonthView", "---------my--count----n-----------"+(i-1));
            addView(view, i-1, layoutParams);

        }

        if (functionConfig.isSHOW_OUTSIDE_DATE()){ //

            getBeforeYearMonth();

            int b_count = CalendarUtils.getInstance().getMonthOfDayCount(beforeYear, (beforeMonth-1));

            Log.i("CalendarMonthView", month_day_count+"-----------getBeforeYearMonth-------offset--------"+offset);

            for (int j=0; j<offset; j++){

                View view = LayoutInflater.from(getContext()).inflate(R.layout.calendar_month_item, null, false);
                view.setLayoutParams(layoutParams);
                //view.setVisibility(VISIBLE);
                final RelativeLayout rl_month_item = (RelativeLayout) view.findViewById(R.id.rl_month_item);
                TextView tv_day = (TextView) view.findViewById(R.id.tv_day);
                TextView tv_holiday = (TextView) view.findViewById(R.id.tv_holiday);
                TextView tv_chinese_day = (TextView) view.findViewById(R.id.tv_chinese_day);
                view.setBackgroundResource(R.color.gray);
                //tv_chinese_day.setLayoutParams(params_chinese_day);
                int b_day = b_count - (offset - j) + 1;
                Log.i("CalendarMonthView", b_day+"-----------getBeforeYearMonth---------------"+b_count);
                tv_day.setText(b_day+"");
                //tv_holiday.setVisibility(GONE);
                Log.i("CalendarMonthView", beforeYear+"-----------getBeforeYearMonth---------------"+(beforeMonth-1));
                if (functionConfig.isSHOW_CHINA_DATE()){
                    tv_chinese_day.setVisibility(VISIBLE);
                    tv_chinese_day.setText(LunarCalendar.getInstance().getLunarDate(beforeYear, beforeMonth,b_day,false));
                }else {
                    tv_chinese_day.setVisibility(GONE);
                }

                view.setTag(b_day);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        calendarDataControl.getClickDate(beforeYear+"-"+beforeMonth+"-"+v.getTag());
                        if (functionConfig.isCLICK_OUTSIDE_DATE_THING()){
                            if (now_Postion >= 1){
                                now_Postion = now_Postion - 1;
                                viewPager.setCurrentItem(now_Postion);
                            }
                        }

                    }
                });


                Log.i("CalendarMonthView", "---------my--count----b-----------"+(month_day_count+j));
                addView(view, month_day_count+j, layoutParams);


            }


            getAfterYearMonth();
            int a_count = CalendarUtils.getInstance().getMonthOfDayCount(afterYear, (afterMonth-1));
            int a_offset = getA_Offset();
            Log.i("CalendarMonthView", a_offset+"-----------getAfterYearMonth---------------"+a_count);
            for (int y=0; y<a_offset; y++){

                View view = LayoutInflater.from(getContext()).inflate(R.layout.calendar_month_item, null, false);
                view.setLayoutParams(layoutParams);
                //view.setVisibility(VISIBLE);
                final RelativeLayout rl_month_item = (RelativeLayout) view.findViewById(R.id.rl_month_item);
                TextView tv_day = (TextView) view.findViewById(R.id.tv_day);
                TextView tv_holiday = (TextView) view.findViewById(R.id.tv_holiday);
                TextView tv_chinese_day = (TextView) view.findViewById(R.id.tv_chinese_day);
                view.setBackgroundResource(R.color.gray);
                //tv_chinese_day.setLayoutParams(params_chinese_day);
                int a_day = y+1;
                Log.i("CalendarMonthView", a_day+"-----------getAfterYearMonth---------------"+b_count);
                tv_day.setText(a_day+"");
                //tv_holiday.setVisibility(GONE);
                if (functionConfig.isSHOW_CHINA_DATE()){
                    tv_chinese_day.setVisibility(VISIBLE);
                    tv_chinese_day.setText(LunarCalendar.getInstance().getLunarDate(afterYear, afterMonth,a_day,false));
                }else {
                    tv_chinese_day.setVisibility(GONE);
                }
                view.setTag(a_day);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        calendarDataControl.getClickDate(afterYear+"-"+afterMonth+"-"+v.getTag());
                        if (functionConfig.isCLICK_OUTSIDE_DATE_THING()){
                            now_Postion = now_Postion + 1;
                            viewPager.setCurrentItem(now_Postion);
                        }

                    }
                });


                Log.i("CalendarMonthView", "---------my--count----a-----------"+((month_day_count+offset)+y));
                addView(view, (month_day_count+offset)+y, layoutParams);

            }

        }



    }

    private int getA_Offset(){

        int month_day_count = CalendarUtils.getInstance().getMonthOfDayCount(show_year, (show_month-1)); // 一个月有几天
        Calendar a_calendar = Calendar.getInstance();
        a_calendar.set(show_year, (show_month-1), month_day_count);
        int a_week = a_calendar.get(Calendar.DAY_OF_WEEK);
        return 7-a_week;

    }

    private void getBeforeYearMonth(){

        if (show_month == 1){

            if (show_year != 1970){

                beforeMonth = 12;
                beforeYear = show_year - 1;

            }


        }else {

            beforeMonth = show_month - 1;
            beforeYear = show_year;

        }

    }

    private void getAfterYearMonth(){

        if (show_month == 12){
            afterMonth = 1;
            afterYear = show_year + 1;
        }else {
            afterMonth = show_month + 1;
            afterYear = show_year;
        }

    }

    private boolean getIsToday(int i){

        boolean isToday = false;

        if (CalendarUtils.getInstance().getTodayOfMonth() == i){

            if (CalendarUtils.getInstance().getMonthOfYear() == (show_month-1) && CalendarUtils.getInstance().getYear() == show_year){

                isToday = true;

            }else {

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
        int a_offset = getA_Offset();

        if (functionConfig.isSHOW_OUTSIDE_DATE()){
            for (int i=offset;i<=(count-a_offset);i++){

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

            for (int i=0;i<offset;i++){
                int viewPos = count - a_offset - offset + i;
                View view = getChildAt(viewPos);
                //view.setVisibility(VISIBLE);
                int height = view.getMeasuredHeight();
                int width = view.getMeasuredWidth();
                mPosX = l + i%7*month_width;
                mPosY = t + i/7*month_width;
                view.layout(mPosX, mPosY, mPosX+month_width, mPosY+month_width);
            }

            for (int i=0;i<a_offset;i++){
                int viewPos = count-a_offset+i;
                View view = getChildAt(viewPos);
                //view.setVisibility(VISIBLE);
                int height = view.getMeasuredHeight();
                int width = view.getMeasuredWidth();
                mPosX = l + viewPos%7*month_width;
                mPosY = t + viewPos/7*month_width;
                view.layout(mPosX, mPosY, mPosX+month_width, mPosY+month_width);
            }
        }else {

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
