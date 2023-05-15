package com.tuvarna.mytu.repositories;

import com.tuvarna.mytu.listeners.callback.IDayScheduleCallback;

public interface IDayScheduleRepository {
    /**
     * Retrieves all classes for a specific day schedule.
     *
     * @param callback   The callback to be invoked with the retrieved classes.
     * @param course     The course for which the classes should be retrieved.
     * @param specialty  The specialty for which the classes should be retrieved.
     * @param group      The group for which the classes should be retrieved.
     */
    void getAllClasses(IDayScheduleCallback callback, String course, String specialty, String group);
}