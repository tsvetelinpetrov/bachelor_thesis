package com.tuvarna.mytu.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tuvarna.mytu.R;
import com.tuvarna.mytu.models.ScheduleClass;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassDisciplineViewHolder> {

    private final List<ScheduleClass> classes;
    private  String bgColor;
    private final Context context;

    public ClassAdapter(List<ScheduleClass> classes, Context context, String bgColor) {
        this.classes = classes;
        this.context = context;
        this.bgColor = bgColor;
    }

    @NonNull
    @Override
    public ClassDisciplineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_schedule_item, parent, false);
        return new ClassDisciplineViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ClassDisciplineViewHolder holder, int position) {
        ScheduleClass classes = this.classes.get(position);
        holder.disciplineName.setText(classes.getClassName());
        holder.classHall.setText(classes.getClassHall());
        holder.classTime.setText(classes.getClassStartTime() + " - " + classes.getClassEndTime());

        holder.itemView.findViewById(R.id.dayScheduleLayout).setBackgroundColor(Color.parseColor(bgColor));
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public static class ClassDisciplineViewHolder extends RecyclerView.ViewHolder {
        TextView disciplineName;
        TextView classTime;
        TextView classHall;
        public ClassDisciplineViewHolder(@NonNull View itemView) {
            super(itemView);
            disciplineName = itemView.findViewById(R.id.disciplineNameView);
            classHall = itemView.findViewById(R.id.classHallView);
            classTime = itemView.findViewById(R.id.classTimeView);
        }
    }
}