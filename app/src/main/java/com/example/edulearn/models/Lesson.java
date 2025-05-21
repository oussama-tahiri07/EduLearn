package com.example.edulearn.models;

import com.google.gson.annotations.SerializedName;

public class Lesson {
    @SerializedName("id")
    private int id;
    
    @SerializedName("title")
    private String title;
    
    @SerializedName("description")
    private String description;
    
    @SerializedName("courseName")
    private String courseName;
    
    @SerializedName("numberOfAttendees")
    private int numberOfAttendees;
    
    // Constructors
    public Lesson() {}
    
    public Lesson(String title, String description) {
        this.title = title;
        this.description = description;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }
    
    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }
}
