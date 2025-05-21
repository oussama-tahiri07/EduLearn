package com.example.edulearn.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {
    @SerializedName("id")
    private long id;
    
    @SerializedName("type")
    private String type;
    
    @SerializedName("content")
    private String content;
    
    @SerializedName("choices")
    private List<Choice> choices;
    
    @SerializedName("correctAnswer")
    private String correctAnswer;
    
    // Constructors
    public Question() {}
    
    // Getters and Setters
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public List<Choice> getChoices() {
        return choices;
    }
    
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
    
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
