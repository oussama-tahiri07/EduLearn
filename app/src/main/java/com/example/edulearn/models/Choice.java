package com.example.edulearn.models;

import com.google.gson.annotations.SerializedName;

public class Choice {
    @SerializedName("id")
    private long id;
    
    @SerializedName("value")
    private String value;
    
    // Constructors
    public Choice() {}
    
    public Choice(String value) {
        this.value = value;
    }
    
    // Getters and Setters
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
}
