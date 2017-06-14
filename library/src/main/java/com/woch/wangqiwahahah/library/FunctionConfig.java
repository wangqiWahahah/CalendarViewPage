package com.woch.wangqiwahahah.library;

import android.graphics.Color;

/**
 * Created by wangqiWahahah on 2017/6/12.
 */

public class FunctionConfig {

    private boolean SHOW_OUTSIDE_DATE; //是否显示前后日期
    private int CLICK_DATE_STYLE;//点击样式
    private boolean OPEN_SELECT_RANGE;//是否可以选择日期范围
    private int SELECT_RANGE_DATE_STYLE;//选中日期范围样式
    private boolean SHOW_CHINA_DATE;//显示农历
    private boolean CLICK_OUTSIDE_DATE_THING;//点击非本月日期展现该月
    private boolean SHOW_BAR;//bar是否显示
    private String WEEKEND_COLOR;
    private boolean SET_WEEKEND_COLOR;//是否设置周末

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

    }

    public static class Builder {

        private boolean SHOW_OUTSIDE_DATE = false; //是否显示前后日期
        private int CLICK_DATE_STYLE = R.drawable.hollow_circle_gray;//点击样式
        private boolean OPEN_SELECT_RANGE = false;//是否可以选择日期范围
        private int SELECT_RANGE_DATE_STYLE = R.color.blue;//选中日期范围样式
        private boolean SHOW_CHINA_DATE = true;//显示农历
        private boolean SET_WEEKEND_COLOR = true;//是否设置周末
        private boolean CLICK_OUTSIDE_DATE_THING = false;//点击非本月日期展现该月
        private boolean SHOW_BAR = true;//bar是否显示
        private String WEEKEND_COLOR = "#D9D9D9"; // 周末的背景颜色

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

        public Builder setSELECT_RANGE_DATE_STYLE(int SELECT_RANGE_DATE_STYLE) {
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
        return SELECT_RANGE_DATE_STYLE;
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
