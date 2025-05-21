package com.example.edulearn.models;

import com.google.gson.annotations.SerializedName;

public class Student {
    @SerializedName("studentName")
    private String studentName;
    
    @SerializedName("studentEmail")
    private String studentEmail;
    
    @SerializedName("numberOfAttendedLessons")
    private int numberOfAttendedLessons;
    
    // Constructors
    public Student() {}
    
    // Getters and Setters
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getStudentEmail() {
        return studentEmail;
    }
    
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
    
    public int getNumberOfAttendedLessons() {
        return numberOfAttendedLessons;
    }
    
    public void setNumberOfAttendedLessons(int numberOfAttendedLessons) {
        this.numberOfAttendedLessons = numberOfAttendedLessons;
    }
}
