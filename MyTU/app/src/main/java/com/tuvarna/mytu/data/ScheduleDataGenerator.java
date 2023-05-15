package com.tuvarna.mytu.data;

import com.tuvarna.mytu.models.ScheduleClass;
import com.tuvarna.mytu.models.ScheduleDay;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDataGenerator {
    public List<ScheduleDay> generate() {
        List<ScheduleDay> days = new ArrayList<>();

        ScheduleDay day = new ScheduleDay("Понеделник");
        day.addClass(new ScheduleClass("Програмиране за мобилни и интернет устройства",
                "201 ТВ", "9:15", "11:00"));
        day.addClass(new ScheduleClass("Математика",
                "201 ТВ", "11:15", "13:00"));
        day.addClass(new ScheduleClass("Бази данни",
                "201 ТВ", "13:30", "15:00"));
        days.add(day);

        day = new ScheduleDay("Вторник");
        day.addClass(new ScheduleClass("Извличане на информация в интернет",
                "201 ТВ", "9:15", "11:00"));
        day.addClass(new ScheduleClass("Математика",
                "201 ТВ", "11:15", "13:00"));
        day.addClass(new ScheduleClass("Бази данни",
                "201 ТВ", "13:30", "15:00"));
        day.addClass(new ScheduleClass("Логика и автомати",
                "201 ТВ", "13:30", "15:00"));
        days.add(day);


        day = new ScheduleDay("Сряда");
        day.addClass(new ScheduleClass("Извличане на информация в интернет",
                "201 ТВ", "9:15", "11:00"));
        day.addClass(new ScheduleClass("Математика",
                "201 ТВ", "11:15", "13:00"));
        day.addClass(new ScheduleClass("Бази данни",
                "201 ТВ", "13:30", "15:00"));
        day.addClass(new ScheduleClass("Логика и автомати",
                "201 ТВ", "13:30", "15:00"));
        days.add(day);

        day = new ScheduleDay("Четвъртък");
        day.addClass(new ScheduleClass("Извличане на информация в интернет",
                "201 ТВ", "9:15", "11:00"));
        day.addClass(new ScheduleClass("Математика",
                "201 ТВ", "11:15", "13:00"));
        day.addClass(new ScheduleClass("Бази данни",
                "201 ТВ", "13:30", "15:00"));
        day.addClass(new ScheduleClass("Логика и автомати",
                "201 ТВ", "13:30", "15:00"));
        days.add(day);

        day = new ScheduleDay("Петък");
        day.addClass(new ScheduleClass("Извличане на информация в интернет",
                "201 ТВ", "9:15", "11:00"));
        day.addClass(new ScheduleClass("Математика",
                "201 ТВ", "11:15", "13:00"));
        day.addClass(new ScheduleClass("Бази данни",
                "201 ТВ", "13:30", "15:00"));
        day.addClass(new ScheduleClass("Логика и автомати",
                "201 ТВ", "13:30", "15:00"));
        days.add(day);

        return days;
    }
}
