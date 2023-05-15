package com.tuvarna.mytu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tuvarna.mytu.R;
import com.tuvarna.mytu.models.ScheduleDay;

import java.util.List;

public class DayScheduleAdapter extends RecyclerView.Adapter<DayScheduleAdapter.DayViewHolder>{
    private final List<ScheduleDay> daysSchedule;
    private final Context context;
    RecyclerView recyclerView;
    public DayScheduleAdapter(List<ScheduleDay> daysSchedule, Context context) {
        this.daysSchedule = daysSchedule;
        this.context = context;
    }
    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_schedule_item, parent, false);
        return new DayViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        ScheduleDay daySchedule = daysSchedule.get(position);
        holder.dayName.setText(daySchedule.getDayName().toLowerCase().replaceAll(".", "$0\n"));
        recyclerView = holder.itemView.findViewById(R.id.classesRV);
        recyclerView.setAdapter(new ClassAdapter(daySchedule.getClasses(), context, daySchedule.getBgColor()));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public int getItemCount() {
        return daysSchedule.size();
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {
        TextView dayName;
        RecyclerView class1;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayName = itemView.findViewById(R.id.dayName);
            class1 = itemView.findViewById(R.id.classesRV);
        }
    }
}
