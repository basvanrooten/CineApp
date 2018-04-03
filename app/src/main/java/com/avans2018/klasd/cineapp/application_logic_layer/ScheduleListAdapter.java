package com.avans2018.klasd.cineapp.application_logic_layer;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.domain_layer.MovieSchedule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Tom on 28-3-2018. Voor gebruik in DetailActivity om Schedules weer te geven in lijst
 */

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ScheduleViewHolder>{

    private Context mContext;
    private ArrayList<MovieSchedule> scheduleList;
    private OnItemClickListener listener;
    private final static String TAG = "ScheduleListAdapter";

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ScheduleListAdapter(Context mContext, ArrayList<MovieSchedule> scheduleList) {
        this.mContext = mContext;
        this.scheduleList = scheduleList;
    }

    @Override
    public ScheduleListAdapter.ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG,"onCreateViewHolder() called.");
        View v = LayoutInflater.from(mContext).inflate(R.layout.schedule_row, parent, false);
        return new ScheduleListAdapter.ScheduleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ScheduleListAdapter.ScheduleViewHolder holder, int position) {
        Log.i(TAG,"onBindViewHolder() called.");

        MovieSchedule schedule = scheduleList.get(position);

        DateFormat df = new SimpleDateFormat("dd-MM");
        String dateAsString = df.format(schedule.getDate());

        String scheduleDate = dateAsString;
        String startTime = "" + schedule.getStartTime();
        String endTime = "" + schedule.getEndTime();
        String takenPerc = "" + schedule.getTheater().getFreeSeats() + mContext.getString(R.string.schedule_availability);

        if(schedule.getDate().getTime() < System.currentTimeMillis()){
            holder.scheduleListDate.setTextColor(Color.GRAY);
            holder.scheduleListStartTime.setTextColor(Color.GRAY);
            holder.until.setTextColor(Color.GRAY);
            holder.scheduleListEndTime.setTextColor(Color.GRAY);
            holder.scheduleListTakenPerc.setTextColor(Color.GRAY);
            holder.scheduleListTakenPerc.setText(mContext.getString(R.string.unavailable));
        } else if(schedule.getTakenPerc() == 0){
            holder.scheduleListDate.setTextColor(Color.GRAY);
            holder.scheduleListStartTime.setTextColor(Color.GRAY);
            holder.until.setTextColor(Color.GRAY);
            holder.scheduleListEndTime.setTextColor(Color.GRAY);
            holder.scheduleListTakenPerc.setTextColor(Color.GRAY);
            holder.scheduleListTakenPerc.setText(mContext.getString(R.string.fully_booked));
        } else {
            holder.scheduleListDate.setTextColor(Color.WHITE);
            holder.scheduleListStartTime.setTextColor(Color.WHITE);
            holder.until.setTextColor(Color.WHITE);
            holder.scheduleListEndTime.setTextColor(Color.WHITE);
            holder.scheduleListTakenPerc.setTextColor(Color.WHITE);
            holder.scheduleListTakenPerc.setText(takenPerc);
        }
        holder.scheduleListDate.setText(scheduleDate);
        holder.scheduleListStartTime.setText(startTime);
        holder.scheduleListEndTime.setText(endTime);
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {

        private TextView scheduleListDate;
        private TextView scheduleListStartTime;
        private TextView until;
        private TextView scheduleListEndTime;
        private TextView scheduleListTakenPerc;

        private ScheduleViewHolder(View itemView) {
            super(itemView);
            scheduleListDate = itemView.findViewById(R.id.scheduleListDate);
            scheduleListStartTime = itemView.findViewById(R.id.scheduleListStartTime);
            until = itemView.findViewById(R.id.scheduleListUntil);
            scheduleListEndTime = itemView.findViewById(R.id.scheduleListEndTime);
            scheduleListTakenPerc = itemView.findViewById(R.id.scheduleListTakenPerc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }



}
