package com.woch.wangqiwahahah.library;

/**
 * Created by wangqiWahahah on 2017/6/7.
 */

public interface CalendarDataControl {

    public abstract void getClickDate(String s_date);

    public abstract int[] getShowTime();

    public abstract void getRangeClickDate(String min_date, String max_date);

}
