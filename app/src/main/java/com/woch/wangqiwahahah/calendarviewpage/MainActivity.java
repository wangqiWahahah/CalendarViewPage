package com.woch.wangqiwahahah.calendarviewpage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.woch.wangqiwahahah.library.CalendarDataControl;
import com.woch.wangqiwahahah.library.FunctionConfig;
import com.woch.wangqiwahahah.library.MyCalendarView;

public class MainActivity extends AppCompatActivity implements CalendarDataControl{

    private MyCalendarView m_calendar_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FunctionConfig functionConfig = new FunctionConfig.Builder()
                //.setCLICK_DATE_STYLE()
                .setCLICK_OUTSIDE_DATE_THING(true)
                .setOPEN_SELECT_RANGE(true)
                .setSELECT_RANGE_DATE_STYLE("#b36d61")
                .setSHOW_CHINA_DATE(true)
                .setSHOW_OUTSIDE_DATE(true)
                .setSHOW_BAR(false)
                .setWEEKEND_COLOR("#d7ecf1")
                .setSHOW_OUTSIDE_TODAY(false)
                .build();

        m_calendar_view = (MyCalendarView) findViewById(R.id.m_calendar_view);
        //functionConfig 要在 setControl 之前
        m_calendar_view.setFunctionConfig(functionConfig);
        m_calendar_view.setControl(this);
        //m_calendar_view.setShowTime(2017, 5);

    }

    @Override
    public void getClickDate(String s_date) {
        Log.i("MainActivity", "----------------"+s_date);
    }

    @Override
    public int[] getShowTime() {

        return new int[]{2017, 5};

    }

    @Override
    public void getRangeClickDate(String min_date, String max_date) {
        Log.i("MainActivity", min_date+"----------------"+max_date);
    }

}
