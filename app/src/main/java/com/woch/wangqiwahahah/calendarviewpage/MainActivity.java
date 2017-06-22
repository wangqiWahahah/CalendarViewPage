package com.woch.wangqiwahahah.calendarviewpage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.woch.wangqiwahahah.library.CalendarDataControl;
import com.woch.wangqiwahahah.library.CalendarEventEntity;
import com.woch.wangqiwahahah.library.CalendarEventItem;
import com.woch.wangqiwahahah.library.FunctionConfig;
import com.woch.wangqiwahahah.library.MyCalendarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CalendarDataControl{

    private MyCalendarView m_calendar_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化数据库查看工具
        //浏览器中访问  chrome://inspect/#devices
        try{
            Stetho.initializeWithDefaults(this);
        }catch (Exception e){
            e.printStackTrace();
        }

        List<CalendarEventItem> calendarEventItemList = new ArrayList<>();
        for (int i=0; i< 5; i++){
            CalendarEventItem calendarEventItem = new CalendarEventItem();
            calendarEventItem.setContent("setContent"+i);
            calendarEventItem.setDescribe("setDescribe"+i);
            calendarEventItem.setTime("setTime"+i);
            calendarEventItem.setType(i);
            calendarEventItem.setPriority(1);
            calendarEventItem.setTitle("setTitle"+i);
            calendarEventItemList.add(calendarEventItem);
        }
        List<CalendarEventEntity> calendarEventEntityList = new ArrayList<>();
        CalendarEventEntity calendarEventEntity1 = new CalendarEventEntity();
        calendarEventEntity1.setDate("2017-6-21");
        calendarEventEntity1.setUse_id("10086");
        calendarEventEntity1.setCalendarEventItems(calendarEventItemList);

        CalendarEventEntity calendarEventEntity2 = new CalendarEventEntity();
        calendarEventEntity2.setDate("2017-6-22");
        calendarEventEntity2.setUse_id("10086");
        calendarEventEntity2.setCalendarEventItems(calendarEventItemList);

        CalendarEventEntity calendarEventEntity3 = new CalendarEventEntity();
        calendarEventEntity3.setDate("2017-6-23");
        calendarEventEntity3.setUse_id("10086");
        calendarEventEntity3.setCalendarEventItems(calendarEventItemList);

        CalendarEventEntity calendarEventEntity4 = new CalendarEventEntity();
        calendarEventEntity4.setDate("2017-6-24");
        calendarEventEntity4.setUse_id("10086");
        calendarEventEntity4.setCalendarEventItems(calendarEventItemList);

        CalendarEventEntity calendarEventEntity5 = new CalendarEventEntity();
        calendarEventEntity5.setDate("2017-6-25");
        calendarEventEntity5.setUse_id("10086");
        calendarEventEntity5.setCalendarEventItems(calendarEventItemList);

        calendarEventEntityList.add(calendarEventEntity1);
        calendarEventEntityList.add(calendarEventEntity2);
        calendarEventEntityList.add(calendarEventEntity3);
        calendarEventEntityList.add(calendarEventEntity4);
        calendarEventEntityList.add(calendarEventEntity5);

        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setCLICK_DATE_STYLE(R.drawable.hollow_circle_gray_main)
                .setCLICK_OUTSIDE_DATE_THING(true)
                .setMONTH_STYLE(R.layout.calendar_month_item_main)
                //.setOPEN_SELECT_RANGE(true)
                //.setSELECT_RANGE_DATE_STYLE("#b36d61")
                .setSHOW_CHINA_DATE(true)
                .setSHOW_OUTSIDE_DATE(true)
                .setSHOW_BAR(true)
                .setWEEKEND_COLOR("#d7ecf1")
                .setSHOW_OUTSIDE_TODAY(false)
                .setSHOW_EVENT_LIST(true)
                .setEVENT_ITEM_LIST(calendarEventEntityList)
                .setUse_id("10086")
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
