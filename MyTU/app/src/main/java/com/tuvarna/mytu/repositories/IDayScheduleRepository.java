package com.tuvarna.mytu.repositories;

public interface IDayScheduleRepository {
    void getAllClasses(IDayScheduleCallback callback, String course, String specialty, String group);
}
