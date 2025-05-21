package com.example.edulearn.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizSubmission {
    @SerializedName("quiz")
    private Quiz quiz;
    
    @SerializedName("questions")
    private List<Question> questions;
    
    // Constructors
    public QuizSubmission() {}
    
    // Getters and Setters
    public Quiz getQuiz() {
        return quiz;
    }
    
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    
    public List<Question> getQuestions() {
        return questions;
    }
    
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
