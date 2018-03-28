package com.avans2018.klasd.cineapp.application_logic_layer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.domain_layer.MovieSchedule;

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

        holder.scheduleListTheater.setText("Zaal " + schedule.getTheater().getTheaterNumber());
        holder.scheduleListTime.setText("" + schedule.getTime());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {

        private TextView scheduleListTheater;
        private TextView scheduleListTime;

        private ScheduleViewHolder(View itemView) {
            super(itemView);
            scheduleListTheater = itemView.findViewById(R.id.scheduleListTheater);
            scheduleListTime = itemView.findViewById(R.id.scheduleListTime);

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
