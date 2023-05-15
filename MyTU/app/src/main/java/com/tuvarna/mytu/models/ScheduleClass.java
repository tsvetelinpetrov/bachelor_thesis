package com.tuvarna.mytu.models;

public class ScheduleClass {
    private String className;
    private String classHall;
    private String classStartTime;
    private String classEndTime;

    public ScheduleClass(String className, String classHall, String classStartTime, String classEndTime) {
        this.className = className;
        this.classHall = classHall;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassHall() {
        return classHall;
    }

    public void setClassHall(String classHall) {
        this.classHall = classHall;
    }

    public String getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(String classStartTime) {
        this.classStartTime = classStartTime;
    }

    public String getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(String classEndTime) {
        this.classEndTime = classEndTime;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "className='" + className + '\'' +
                ", classHall='" + classHall + '\'' +
                ", classStartTime='" + classStartTime + '\'' +
                ", classEndTime='" + classEndTime + '\'' +
                '}';
    }
}

