package com.woch.wangqiwahahah.library;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqiWahahah on 2017/6/7.
 */

public class CalendarPageAdapter extends PagerAdapter {

    private Context context;

    private int show_year, show_month;
    private int m_position;
    private BeforeClickDate beforeClickDate;
    private CalendarDataControl calendarDataControl;
    private FunctionConfig functionConfig;
    private ViewPager viewPager;
    private int now_position;
    private RangeClickDate rangeClickDate;
    private List<CalendarMonthView> calendarMonthViewList = new ArrayList<>();

    public CalendarPageAdapter(Context context, int show_year, int show_month, int m_position, BeforeClickDate beforeClickDate, CalendarDataControl calendarDataControl, FunctionConfig functionConfig, RangeClickDate rangeClickDate){

        this.context = context;
        this.show_month = show_month;
        this.show_year = show_year;
        this.m_position = m_position;
        this.beforeClickDate = beforeClickDate;
        this.calendarDataControl = calendarDataControl;
        this.functionConfig = functionConfig;
        this.rangeClickDate = rangeClickDate;

    }

    public void setShowYearMonth(int show_year, int show_month, int m_position){

        this.show_month = show_month;
        this.show_year = show_year;
        this.m_position = m_position;

    }

    private CalendarEventAdapter calendarEventAdapter;

    public void setEventAdapter(CalendarEventAdapter calendarEventAdapter){

        this.calendarEventAdapter = calendarEventAdapter;

    }

    public void setViewPage(ViewPager viewPager){

        this.viewPager = viewPager;

    }

    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 200;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        // TODO Auto-generated method stub
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub

        if (m_position == -1){
            m_position = position;
        }

        int m_year = show_year;
        int m_month = show_month;
        int[] year_month = CalendarUtils.getInstance().tryToCalculateMonth(m_position, position, show_year, show_month, m_year, m_month);
        CalendarMonthView calendarMonthView = new CalendarMonthView(context, calendarDataControl, beforeClickDate, functionConfig, position, rangeClickDate);
        //calendarMonthViewList.add(calendarMonthView);
        calendarMonthView.setViewPage(viewPager, this);
        calendarMonthView.setCalendarEventAdapter(calendarEventAdapter);
        //calendarMonthView.setReLayout(0);
        //Log.i("CalendarMonthView", m_position+"---------CalendarPageAdapter---position------"+position);
        //Log.i("CalendarMonthView", year_month[0]+"---------CalendarPageAdapter---------"+year_month[1]);
        Log.i("CalendarMonthView", m_position+"---------CalendarPageAdapter---position------"+position);
        calendarMonthView.setPositionMonth(year_month[0], year_month[1]);
        ViewPager.LayoutParams params = new ViewPager.LayoutParams();
        params.width = ViewPager.LayoutParams.MATCH_PARENT;
        params.height = ViewPager.LayoutParams.WRAP_CONTENT;
        calendarMonthView.setLayoutParams(params);
        container.addView(calendarMonthView);
        return calendarMonthView;

    }

}
