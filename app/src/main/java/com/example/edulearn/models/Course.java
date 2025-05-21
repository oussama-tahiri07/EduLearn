package com.example.edulearn.models;

import com.google.gson.annotations.SerializedName;

public class Course {
    @SerializedName("id")
    private int id;
    
    @SerializedName("courseName")
    private String courseName;
    
    @SerializedName("courseDescription")
    private String courseDescription;
    
    @SerializedName("courseDuration")
    private float courseDuration;
    
    @SerializedName("instructorName")
    private String instructorName;
    
    @SerializedName("numberOfStudents")
    private int numberOfStudents;
    
    @SerializedName("numberofLessons")
    private int numberOfLessons;
    
    // Constructors
    public Course() {}
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getCourseDescription() {
        return courseDescription;
    }
    
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
    
    public float getCourseDuration() {
        return courseDuration;
    }
    
    public void setCourseDuration(float courseDuration) {
        this.courseDuration = courseDuration;
    }
    
    public String getInstructorName() {
        return instructorName;
    }
    
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
    
    public int getNumberOfStudents() {
        return numberOfStudents;
    }
    
    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
    
    public int getNumberOfLessons() {
        return numberOfLessons;
    }
    
    public void setNumberOfLessons(int numberOfLessons) {
        this.numberOfLessons = numberOfLessons;
    }
}
