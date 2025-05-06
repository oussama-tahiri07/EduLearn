package com.example.edulearn.mainHome;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.edulearn.moodle.Courses.MobileApp.dashboardMobileApp;
import com.example.edulearn.moodle.Courses.dashboardDatabases;
import com.example.edulearn.moodle.Courses.dashboardMachineLearning;
import com.example.edulearn.moodle.Courses.dashboardWebApp;
import com.example.edulearn.moodle.R;

public class FragmentDashboard extends Fragment {

    Button mobile, machine, web, database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mobile = view.findViewById(R.id.mobile);
        web = view.findViewById(R.id.web);
        machine = view.findViewById(R.id.machine);
        database = view.findViewById(R.id.database);

        machine.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), dashboardMachineLearning.class);
            startActivity(intent);
        });

        web.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), dashboardWebApp.class);
            startActivity(intent);
        });

        mobile.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), dashboardMobileApp.class);
            startActivity(intent);
        });

        database.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), dashboardDatabases.class);
            startActivity(intent);
        });

        return view;
    }
}