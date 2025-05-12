package com.example.edulearn.teacher_info.DetailTeacher1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.edulearn.R;

public class Teacher1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher1);


        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        TextView email = findViewById(R.id.email_btn);

        // Set up a click listener for the button to open the link in the browser
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the URL you want to open
                String urlEmail = "https://mail.google.com/mail/u/1/#inbox?compose=GTvVlcSHxwCnLLSlznLTGMQpbPgcFhFLQhBZTXhfmLnlfmkqHFdkwbmpnCWQtgKhzfbJbrSZnbPlX"; // Replace with your desired URL

                // Create an Intent to open the URL in the default web browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlEmail));

                // Start the web browser activity
                startActivity(intent);
            }
        });

        TextView details = findViewById(R.id.detail);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teacher1.this, DetailTeacher1.class);
                startActivity(intent);
            }
        });

        TextView badges = findViewById(R.id.badge);
        badges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teacher1.this, BadgesTeacher1.class);
                startActivity(intent);
            }
        });

        TextView blog = findViewById(R.id.blog_entries);
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teacher1.this, BlogTeacher1.class);
                startActivity(intent);
            }
        });


    }
}