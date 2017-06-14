package com.woch.wangqiwahahah.library;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangqiWahahah on 2017/6/9.
 */

public class CalendarUtils {

    private static CalendarUtils calendarUtils;

    private CalendarUtils(){

    }


    public static CalendarUtils getInstance(){

        if (calendarUtils == null){

            synchronized (CalendarUtils.class){

                if (calendarUtils == null){

                    calendarUtils = new CalendarUtils();

                }

            }

        }

        return calendarUtils;
    }


    public int getMonthOfDayCount(int year, int month){

        switch (month) {
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.OCTOBER:
            case Calendar.DECEMBER:
                return 31;
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                return 30;
            case Calendar.FEBRUARY:
                return ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) ? 29 : 28;
            default:
                throw new IllegalArgumentException("Invalid Month");
        }


    }

    public String getChineseWeek(Calendar calendar){

        int week = calendar.get(Calendar.DAY_OF_WEEK);
        String week_c = "星期一";
        switch (week){

            case 1:
                week_c = "星期日";
                break;

            case 2:
                week_c = "星期一";
                break;

            case 3:
                week_c = "星期二";
                break;

            case 4:
                week_c = "星期三";
                break;

            case 5:
                week_c = "星期四";
                break;

            case 6:
                week_c = "星期五";
                break;

            case 7:
                week_c = "星期六";
                break;

        }


        return week_c;

    }

    public int[] tryToCalculateMonth(int m_position, int position, int show_year, int show_month, int m_year, int m_month){

        if (m_position - position > 0){

            if (m_position - position + 1 > show_month){

                int multiple = (m_position - position - show_month)/12;
                m_year = m_year - multiple -1;
                m_month = 12 - (m_position - position - show_month)%12;

            } else {

                m_month = show_month - (m_position - position);

            }
            //Log.i("CalendarMonthView", m_year+"---------year---->-----"+m_month);

        }else if (m_position - position < 0){

            if (position - m_position +1 > 14 - m_month){

                int multiple = (position - m_position +1 - 14 + m_month)/12;
                m_year = multiple + 1 + m_year;
                m_month = (position - m_position +1 - 14 + m_month)%12 + 1;

            }else if (position - m_position + 1 == 14 - m_month){

                m_year = m_year + 1;
                m_month = 1;

            }else {

                m_month = m_month + position - m_position;

            }
            //Log.i("CalendarMonthView", m_year+"---------year---<------"+m_month);

        }else {
            m_year = show_year;
            m_month = show_month;
        }

        int[] year_month = new int[2];
        year_month[0] = m_year;
        year_month[1] = m_month;

        return year_month;


    }

    public int getTodayOfMonth(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;

    }

    public int getMonthOfYear(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int month = calendar.get(Calendar.MONTH);
        return month;

    }


    public int getYear(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        return year;

    }

//    public long get(){
//
//        return ;
//
//    }


}
