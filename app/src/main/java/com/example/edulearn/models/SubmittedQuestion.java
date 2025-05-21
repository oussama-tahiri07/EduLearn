package com.example.edulearn.models;

import com.google.gson.annotations.SerializedName;

public class SubmittedQuestion {
    @SerializedName("SubmittedQuestionId")
    private long submittedQuestionId;
    
    @SerializedName("studentAnswer")
    private String studentAnswer;
    
    @SerializedName("question")
    private Question question;
    
    // Constructors
    public SubmittedQuestion() {}
    
    public SubmittedQuestion(Question question, String studentAnswer) {
        this.question = question;
        this.studentAnswer = studentAnswer;
    }
    
    // Getters and Setters
    public long getSubmittedQuestionId() {
        return submittedQuestionId;
    }
    
    public void setSubmittedQuestionId(long submittedQuestionId) {
        this.submittedQuestionId = submittedQuestionId;
    }
    
    public String getStudentAnswer() {
        return studentAnswer;
    }
    
    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }
    
    public Question getQuestion() {
        return question;
    }
    
    public void setQuestion(Question question) {
        this.question = question;
    }
}
