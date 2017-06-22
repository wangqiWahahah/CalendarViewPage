package com.woch.wangqiwahahah.library;

import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.litesuits.orm.db.enums.Relation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangqiWahahah on 2017/6/15.
 */

@Table("table_calendar_Event_Entity")
public class CalendarEventEntity implements Serializable{

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int _id;
    private String use_id;
    private String date;//格式 2017-1-2
    @Mapping(Relation.OneToMany)
    private List<CalendarEventItem> calendarEventItems;

    public List<CalendarEventItem> getCalendarEventItems() {
        return calendarEventItems;
    }

    public void setCalendarEventItems(List<CalendarEventItem> calendarEventItems) {
        this.calendarEventItems = calendarEventItems;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUse_id() {
        return use_id;
    }

    public void setUse_id(String use_id) {
        this.use_id = use_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
