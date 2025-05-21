package com.example.edulearn.models;

import com.google.gson.annotations.SerializedName;

public class Assignment {
    @SerializedName("id")
    private int id;
    
    @SerializedName("title")
    private String title;
    
    @SerializedName("description")
    private String description;
    
    @SerializedName("dueDate")
    private String dueDate;
    
    @SerializedName("courseName")
    private String courseName;
    
    @SerializedName("numberOfSubmissions")
    private int numberOfSubmissions;
    
    // Constructors
    public Assignment() {}
    
    public Assignment(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
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
    
    public String getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public int getNumberOfSubmissions() {
        return numberOfSubmissions;
    }
    
    public void setNumberOfSubmissions(int numberOfSubmissions) {
        this.numberOfSubmissions = numberOfSubmissions;
    }
}
