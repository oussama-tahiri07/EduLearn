package com.example.edulearn.models;

import com.google.gson.annotations.SerializedName;

public class Quiz {
    @SerializedName("quizID")
    private long quizID;
    
    @SerializedName("quizTitle")
    private String quizTitle;
    
    @SerializedName("quizDuration")
    private String quizDuration;
    
    // Constructors
    public Quiz() {}
    
    public Quiz(String quizTitle, String quizDuration) {
        this.quizTitle = quizTitle;
        this.quizDuration = quizDuration;
    }
    
    // Getters and Setters
    public long getQuizID() {
        return quizID;
    }
    
    public void setQuizID(long quizID) {
        this.quizID = quizID;
    }
    
    public String getQuizTitle() {
        return quizTitle;
    }
    
    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }
    
    public String getQuizDuration() {
        return quizDuration;
    }
    
    public void setQuizDuration(String quizDuration) {
        this.quizDuration = quizDuration;
    }
}
