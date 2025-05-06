package com.example.edulearn.Courses.MobileApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.edulearn.R;

public class dashboardMobileApp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_mobile_app);

        Button courseNo1 = findViewById(R.id.intro);
        courseNo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboardMobileApp.this, MobileCourseIntroduction.class);
                startActivity(intent);
            }
        });

        Button CourseNo2 = findViewById(R.id.courseNo2);
        CourseNo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboardMobileApp.this, MobileCourseNo2.class);
                startActivity(intent);
            }
        });

        Button back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}