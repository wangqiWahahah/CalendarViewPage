package com.woch.wangqiwahahah.library;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangqiWahahah on 2017/6/7.
 */

public class CalendarPageAdapter extends PagerAdapter {

    private Context context;

    private int show_year, show_month;
    private int m_position;

    public CalendarPageAdapter(Context context, int show_year, int show_month, int m_position){

        this.context = context;
        this.show_month = show_month;
        this.show_year = show_year;
        this.m_position = m_position;

    }

    public void setShowYearMonth(int show_year, int show_month, int m_position){

        this.show_month = show_month;
        this.show_year = show_year;
        this.m_position = m_position;

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
        CalendarDataControl calendarDataControl = null;
        CalendarMonthView calendarMonthView = new CalendarMonthView(context, calendarDataControl);
        //calendarMonthView.setReLayout(0);
        //Log.i("CalendarMonthView", m_position+"---------CalendarPageAdapter---position------"+position);
        //Log.i("CalendarMonthView", year_month[0]+"---------CalendarPageAdapter---------"+year_month[1]);
        calendarMonthView.setPositionMonth(year_month[0], year_month[1]);
        ViewPager.LayoutParams params = new ViewPager.LayoutParams();
        params.width = ViewPager.LayoutParams.MATCH_PARENT;
        params.height = ViewPager.LayoutParams.WRAP_CONTENT;
        calendarMonthView.setLayoutParams(params);
        container.addView(calendarMonthView);
        return calendarMonthView;

    }

}
