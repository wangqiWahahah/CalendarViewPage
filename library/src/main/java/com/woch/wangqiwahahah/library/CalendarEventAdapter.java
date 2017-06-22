package com.woch.wangqiwahahah.library;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.litesuits.orm.log.OrmLog;

import java.util.List;

/**
 * Created by wangqiWahahah on 2017/6/15.
 */

public class CalendarEventAdapter extends RecyclerView.Adapter {

    private FunctionConfig functionConfig;
    private Context context;


    public CalendarEventAdapter(Context context, FunctionConfig functionConfig){

        this.functionConfig = functionConfig;
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(functionConfig.getEVENT_STYLE(), null, false);
        return new CalendarEventItemViewHodle(view);
    }

    public void setDate(String date, String use_id){

        List<CalendarEventEntity> calendarEventEntities = DatabaseManager.getInstance(functionConfig, context).getQueryByWhereValueAndtValue(CalendarEventEntity.class, "use_id", use_id, "date", date);
        OrmLog.i("OrmLog", calendarEventEntities);
        if (calendarEventEntities != null && calendarEventEntities.size() >0){
            calendarEventEntity = calendarEventEntities.get(0);
            Log.i("OrmLog", "--calendarEventEntities--"+calendarEventEntities.size());
            Log.i("OrmLog", "--calendarEventEntity--"+calendarEventEntity.getCalendarEventItems().size());
            Log.i("OrmLog", "----"+calendarEventEntity.getCalendarEventItems().get(0).getContent());
        }else {
            Log.i("OrmLog", "--calendarEventEntities-null-");
        }
        notifyDataSetChanged();


    }

    private CalendarEventEntity calendarEventEntity;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        CalendarEventItemViewHodle calendarEventItemViewHodle = (CalendarEventItemViewHodle) holder;
        if (functionConfig.getIMAGE_ONE() != -1)
            calendarEventItemViewHodle.iv_one.setImageDrawable(ContextCompat.getDrawable(context, functionConfig.getIMAGE_ONE()));
        if (functionConfig.getIMAGE_TWO() != -1)
            calendarEventItemViewHodle.iv_two.setImageDrawable(ContextCompat.getDrawable(context, functionConfig.getIMAGE_TWO()));
        calendarEventItemViewHodle.tv_content.setText(calendarEventEntity.getCalendarEventItems().get(position).getContent());
        calendarEventItemViewHodle.tv_describe.setText(calendarEventEntity.getCalendarEventItems().get(position).getDescribe());
        calendarEventItemViewHodle.tv_time.setText(calendarEventEntity.getCalendarEventItems().get(position).getTime());
        calendarEventItemViewHodle.tv_title.setText(calendarEventEntity.getCalendarEventItems().get(position).getTitle());
        //calendarEventItemViewHodle.v_color.setBackgroundColor();

    }

    @Override
    public int getItemCount() {

        if (calendarEventEntity !=null){
            if (calendarEventEntity.getCalendarEventItems() !=null && calendarEventEntity.getCalendarEventItems().size() >0){
                return calendarEventEntity.getCalendarEventItems().size();
            }
        }
        return 0;

    }

    class CalendarEventItemViewHodle extends RecyclerView.ViewHolder{

        public View v_color;
        public TextView tv_title, tv_content, tv_time, tv_describe;
        public ImageView iv_one, iv_two;

        public CalendarEventItemViewHodle(View itemView) {
            super(itemView);

            v_color = itemView.findViewById(R.id.v_color);

            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_describe = (TextView) itemView.findViewById(R.id.tv_describe);

            iv_one = (ImageView) itemView.findViewById(R.id.iv_one);
            iv_two = (ImageView) itemView.findViewById(R.id.iv_two);

        }

    }

}
