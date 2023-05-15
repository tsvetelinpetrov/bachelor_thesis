package com.tuvarna.mytu.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.tuvarna.mytu.R;
import com.tuvarna.mytu.adapters.ClassAdapter;
import com.tuvarna.mytu.adapters.DayScheduleAdapter;
import com.tuvarna.mytu.models.ScheduleClass;
import com.tuvarna.mytu.models.ScheduleDay;
import com.tuvarna.mytu.repositories.DayScheduleRepository;
import com.tuvarna.mytu.listeners.callback.IDayScheduleCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment implements IDayScheduleCallback{

    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    DayScheduleAdapter dayScheduleAdapter;
    ClassAdapter classAdapter;

    Spinner spinnerCourse;
    Spinner spinnerSpecialty;
    Spinner spinnerGroup;
    Button buttonFilter;
    ArrayList<Spinner> spinners;

    DayScheduleRepository dayScheduleRepository;

    List<ScheduleDay> days;


    public CalendarFragment() {
        // Required empty public constructor
    }


    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        recyclerView = view.findViewById(R.id.dayScheduleRV);
        days = new ArrayList<>();

        ScheduleDay day = new ScheduleDay("Понеделник", "#00ffff");
        day.addClass(new ScheduleClass("Програмиране за мобилни и интернет устройства", "201 ТВ", "9:15", "11:00"));

        days.add(day);
        days.add(new ScheduleDay("Вторник", "#ffff00"));
        days.add(new ScheduleDay("Сряда", "#00ff00"));
        days.add(new ScheduleDay("Четвъртък", "#00ff00"));
        days.add(new ScheduleDay("Петък", "#00ff00"));
        dayScheduleAdapter = new DayScheduleAdapter(days, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(dayScheduleAdapter);

        dayScheduleRepository = new DayScheduleRepository();

        spinnerCourse = (Spinner) view.findViewById(R.id.spinnerCourse);
        spinnerSpecialty = (Spinner) view.findViewById(R.id.spinnerSpecialty);
        spinnerGroup = (Spinner) view.findViewById(R.id.spinnerGroup);

        spinners = new ArrayList<>(Arrays.asList(spinnerCourse, spinnerSpecialty, spinnerGroup));
        ArrayList<Integer> arrays = new ArrayList<>(Arrays.asList(R.array.course_array, R.array.specialty_array, R.array.group_array));

        for (int i = 0; i < spinners.size(); i++) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    arrays.get(i), android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinners.get(i).setAdapter(adapter);
        }

        buttonFilter = view.findViewById(R.id.btnShowSchedule);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Spinner spinner: spinners) {
                    if(spinner.getVisibility() == View.GONE) {
                        buttonFilter.setText(R.string.show_schedule);
                        spinner.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                    else if(spinner.getVisibility() == View.VISIBLE) {
                        buttonFilter.setText(R.string.hidden_schedule);
                        spinner.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        dayScheduleRepository.getAllClasses(CalendarFragment.this, spinner.getSelectedItem().toString(), spinner.getSelectedItem().toString(), spinner.getSelectedItem().toString());
                        Log.i("19621795_", spinner.getSelectedItem().toString());
                    }
                }
            }
        });
    }

    @Override
    public void onDayScheduleReceived(List<ScheduleDay> daySchedules) {
        Log.i("19621795_", "DaySchedules received");
    }

    @Override
    public void onDayScheduleReceiveFailure(Throwable t) {
        Log.e("19621795_", "DaySchedules not received: " + t.getMessage());
        Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}