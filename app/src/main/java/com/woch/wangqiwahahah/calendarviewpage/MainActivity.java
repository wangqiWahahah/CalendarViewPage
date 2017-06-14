package com.woch.wangqiwahahah.calendarviewpage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.woch.wangqiwahahah.library.FunctionConfig;
import com.woch.wangqiwahahah.library.MyCalendarView;

public class MainActivity extends AppCompatActivity {

    private MyCalendarView m_calendar_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FunctionConfig functionConfig = new FunctionConfig.Builder().build();
        m_calendar_view = (MyCalendarView) findViewById(R.id.m_calendar_view);
        m_calendar_view.setShowTime(2017, 5);

    }
}
