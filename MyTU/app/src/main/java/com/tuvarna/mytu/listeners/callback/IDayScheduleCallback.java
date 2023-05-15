package com.tuvarna.mytu.listeners.callback;

import com.tuvarna.mytu.models.ScheduleDay;

import java.util.List;

public interface IDayScheduleCallback {
    void onDayScheduleReceived(List<ScheduleDay> daySchedules);
    void onDayScheduleReceiveFailure(Throwable t);
}
