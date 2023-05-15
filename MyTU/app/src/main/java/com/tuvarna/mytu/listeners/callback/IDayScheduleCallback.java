package com.tuvarna.mytu.listeners.callback;

import com.tuvarna.mytu.models.ScheduleDay;

import java.util.List;

public interface IDayScheduleCallback {
    /**
     * Called when the day schedule data is successfully received.
     *
     * @param daySchedules The list of ScheduleDay objects representing the day schedules.
     */
    void onDayScheduleReceived(List<ScheduleDay> daySchedules);

    /**
     * Called when there is a failure in receiving the day schedule data.
     *
     * @param t The throwable object representing the failure.
     */
    void onDayScheduleReceiveFailure(Throwable t);
}