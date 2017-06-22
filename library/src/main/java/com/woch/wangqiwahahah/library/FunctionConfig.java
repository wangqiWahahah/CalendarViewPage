package com.woch.wangqiwahahah.library;

import android.graphics.Color;

import java.util.List;

/**
 * Created by wangqiWahahah on 2017/6/12.
 */

public class FunctionConfig {

    private boolean SHOW_OUTSIDE_DATE; //是否显示前后日期
    private int CLICK_DATE_STYLE;//点击样式
    private boolean OPEN_SELECT_RANGE;//是否可以选择日期范围
    private String SELECT_RANGE_DATE_STYLE;//选中日期范围样式
    private boolean SHOW_CHINA_DATE;//显示农历
    private boolean CLICK_OUTSIDE_DATE_THING;//点击非本月日期展现该月
    private boolean SHOW_BAR;//bar是否显示
    private String WEEKEND_COLOR;
    private boolean SET_WEEKEND_COLOR;//是否设置周末
    private boolean SHOW_OUTSIDE_TODAY;//是否显示不是当前月的今天
    private boolean SHOW_EVENT_LIST;//是否显示列表
    private int MONTH_STYLE;//日历item样式
    private List EVENT_ITEM_LIST;//事件list
    private int EVENT_STYLE;//事件item样式
    private int IMAGE_TWO;//设置iv_two
    private int IMAGE_ONE;//设置iv_one
    //private Application appContext;//设置Application
    private String DB_NAME;//设置DB_NAME
    private boolean show_log;//设置show_log
    private String use_id = "001";//设置use_id

    private FunctionConfig(final Builder builder){

        this.SHOW_OUTSIDE_DATE = builder.SHOW_OUTSIDE_DATE;
        this.CLICK_DATE_STYLE = builder.CLICK_DATE_STYLE;
        this.OPEN_SELECT_RANGE = builder.OPEN_SELECT_RANGE;
        this.SELECT_RANGE_DATE_STYLE = builder.SELECT_RANGE_DATE_STYLE;
        this.SHOW_CHINA_DATE = builder.SHOW_CHINA_DATE;
        this.SET_WEEKEND_COLOR = builder.SET_WEEKEND_COLOR;
        this.CLICK_OUTSIDE_DATE_THING = builder.CLICK_OUTSIDE_DATE_THING;
        this.SHOW_BAR = builder.SHOW_BAR;
        this.WEEKEND_COLOR = builder.WEEKEND_COLOR;
        this.SHOW_OUTSIDE_TODAY = builder.SHOW_OUTSIDE_TODAY;
        this.SHOW_EVENT_LIST = builder.SHOW_EVENT_LIST;
        this.MONTH_STYLE = builder.MONTH_STYLE;
        this.EVENT_ITEM_LIST = builder.EVENT_ITEM_LIST;
        this.EVENT_STYLE = builder.EVENT_STYLE;
        this.IMAGE_TWO = builder.IMAGE_TWO;
        this.IMAGE_ONE = builder.IMAGE_ONE;
        //this.appContext = builder.appContext;
        this.DB_NAME = builder.DB_NAME;
        this.show_log = builder.show_log;
        this.use_id = builder.use_id;

    }

    public static class Builder {

        private boolean SHOW_OUTSIDE_DATE = false; //是否显示前后日期
        private int CLICK_DATE_STYLE = R.drawable.hollow_circle_gray;//点击样式
        private boolean OPEN_SELECT_RANGE = false;//是否可以选择日期范围
        private String SELECT_RANGE_DATE_STYLE = "#b36d61";//选中日期范围样式
        private boolean SHOW_CHINA_DATE = true;//显示农历
        private boolean SET_WEEKEND_COLOR = true;//是否设置周末
        private boolean CLICK_OUTSIDE_DATE_THING = false;//点击非本月日期展现该月
        private boolean SHOW_BAR = true;//bar是否显示
        private String WEEKEND_COLOR = "#D9D9D9"; // 周末的背景颜色
        private boolean SHOW_OUTSIDE_TODAY = true;//是否显示不是当前月的今天
        private boolean SHOW_EVENT_LIST = true;//是否显示列表
        private int MONTH_STYLE = R.layout.calendar_month_item;//日历item样式
        private List EVENT_ITEM_LIST = null;//事件list
        private int EVENT_STYLE = R.layout.calendar_event_item;//事件item样式
        private int IMAGE_TWO = -1;//设置iv_two
        private int IMAGE_ONE = -1;//设置iv_one
        //private Application appContext;//设置Application
        private String DB_NAME = "dake_db";//设置DB_NAME
        private boolean show_log = true;//设置show_log
        private String use_id = "10086";//设置use_id

//        public Builder setAppContext(Application appContext) {
//            this.appContext = appContext;
//            return this;
//        }

        public Builder setUse_id(String use_id) {
            this.use_id = use_id;
            return this;
        }

        public Builder setDB_NAME(String DB_NAME) {
            this.DB_NAME = DB_NAME;
            return this;
        }

        public Builder setShow_log(boolean show_log) {
            this.show_log = show_log;
            return this;
        }

        public Builder setIMAGE_TWO(int IMAGE_TWO) {
            this.IMAGE_TWO = IMAGE_TWO;
            return this;
        }

        public Builder setIMAGE_ONE(int IMAGE_ONE) {
            this.IMAGE_ONE = IMAGE_ONE;
            return this;
        }

        public Builder setEVENT_STYLE(int EVENT_STYLE) {
            this.EVENT_STYLE = EVENT_STYLE;
            return this;
        }

        public Builder setEVENT_ITEM_LIST(List EVENT_ITEM_LIST) {
            this.EVENT_ITEM_LIST = EVENT_ITEM_LIST;
            return this;
        }

        public Builder setMONTH_STYLE(int MONTH_STYLE) {
            this.MONTH_STYLE = MONTH_STYLE;
            return this;
        }

        public Builder setSHOW_EVENT_LIST(boolean SHOW_EVENT_LIST) {
            this.SHOW_EVENT_LIST = SHOW_EVENT_LIST;
            return this;
        }

        public Builder setSHOW_OUTSIDE_TODAY(boolean SHOW_OUTSIDE_TODAY) {
            this.SHOW_OUTSIDE_TODAY = SHOW_OUTSIDE_TODAY;
            return this;
        }

        /**
         * @param WEEKEND_COLOR #ffffff 这样格式
         * @return
         */
        public Builder setWEEKEND_COLOR(String WEEKEND_COLOR) {
            this.WEEKEND_COLOR = WEEKEND_COLOR;
            return this;
        }

        public Builder setSHOW_BAR(boolean SHOW_BAR) {
            this.SHOW_BAR = SHOW_BAR;
            return this;
        }

        public Builder setSHOW_OUTSIDE_DATE(boolean SHOW_OUTSIDE_DATE) {
            this.SHOW_OUTSIDE_DATE = SHOW_OUTSIDE_DATE;
            return this;
        }

        public Builder setCLICK_DATE_STYLE(int CLICK_DATE_STYLE) {
            this.CLICK_DATE_STYLE = CLICK_DATE_STYLE;
            return this;
        }

        public Builder setOPEN_SELECT_RANGE(boolean OPEN_SELECT_RANGE) {
            this.OPEN_SELECT_RANGE = OPEN_SELECT_RANGE;
            return this;
        }

        public Builder setSELECT_RANGE_DATE_STYLE(String SELECT_RANGE_DATE_STYLE) {
            this.SELECT_RANGE_DATE_STYLE = SELECT_RANGE_DATE_STYLE;
            return this;
        }

        public Builder setSHOW_CHINA_DATE(boolean SHOW_CHINA_DATE) {
            this.SHOW_CHINA_DATE = SHOW_CHINA_DATE;
            return this;
        }

        public Builder isSET_WEEKEND_COLOR(boolean SET_WEEKEND_COLOR) {
            this.SET_WEEKEND_COLOR = SET_WEEKEND_COLOR;
            return this;
        }

        public Builder setCLICK_OUTSIDE_DATE_THING(boolean CLICK_OUTSIDE_DATE_THING) {
            this.CLICK_OUTSIDE_DATE_THING = CLICK_OUTSIDE_DATE_THING;
            return this;
        }

        public FunctionConfig build() {

            return new FunctionConfig(this);

        }

    }

//    public Application getAppContext() {
//        return appContext;
//    }

    public String getUse_id() {
        return use_id;
    }

    public String getDB_NAME() {
        return DB_NAME;
    }

    public boolean isShow_log() {
        return show_log;
    }

    public int getIMAGE_TWO() {
        return IMAGE_TWO;
    }

    public int getIMAGE_ONE() {
        return IMAGE_ONE;
    }

    public int getEVENT_STYLE() {
        return EVENT_STYLE;
    }

    public List getEVENT_ITEM_LIST() {
        return EVENT_ITEM_LIST;
    }

    public int getMONTH_STYLE() {
        return MONTH_STYLE;
    }

    public boolean isSHOW_EVENT_LIST() {
        return SHOW_EVENT_LIST;
    }

    public boolean isSHOW_OUTSIDE_TODAY() {
        return SHOW_OUTSIDE_TODAY;
    }

    public int getWEEKEND_COLOR() {
        return Color.parseColor(WEEKEND_COLOR);
    }

    public boolean isSHOW_BAR() {
        return SHOW_BAR;
    }

    public boolean isSHOW_OUTSIDE_DATE() {
        return SHOW_OUTSIDE_DATE;
    }

    public int getCLICK_DATE_STYLE() {
        return CLICK_DATE_STYLE;
    }

    public boolean isOPEN_SELECT_RANGE() {
        return OPEN_SELECT_RANGE;
    }

    public int getSELECT_RANGE_DATE_STYLE() {
        return Color.parseColor(SELECT_RANGE_DATE_STYLE);
    }

    public boolean isSHOW_CHINA_DATE() {
        return SHOW_CHINA_DATE;
    }

    public boolean isSET_WEEKEND_COLOR() {
        return SET_WEEKEND_COLOR;
    }

    public boolean isCLICK_OUTSIDE_DATE_THING() {
        return CLICK_OUTSIDE_DATE_THING;
    }



}
