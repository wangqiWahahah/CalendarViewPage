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
                //.setOPEN_SELECT_RANGE()
                //.setSELECT_RANGE_DATE_STYLE()
                .setSHOW_CHINA_DATE(false)
                .setSHOW_OUTSIDE_DATE(true)
                .setWEEKEND_GRAY(true)
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

}
