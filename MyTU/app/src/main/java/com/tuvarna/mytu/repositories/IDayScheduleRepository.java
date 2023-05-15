package com.tuvarna.mytu.repositories;

import com.tuvarna.mytu.listeners.callback.IDayScheduleCallback;

public interface IDayScheduleRepository {
    void getAllClasses(IDayScheduleCallback callback, String course, String specialty, String group);
}
