package com.tuvarna.mytu.models;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDay {
    private String dayName;
    private List<ScheduleClass> classes;

    public ScheduleDay(String dayName) {
        this.dayName = dayName;
        this.classes = new ArrayList<>();
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public List<ScheduleClass> getClasses() {
        return classes;
    }

    public void setClasses(List<ScheduleClass> classes) {
        this.classes = classes;
    }

    public void addClass(ScheduleClass classes) {
        this.classes.add(classes);
    }

    @Override
    public String toString() {
        return "Day{" +
                "dayName='" + dayName + '\'' +
                ", classes=" + classes +
                '}';
    }
}
