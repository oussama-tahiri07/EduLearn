package com.example.edulearn.models;

import com.google.gson.annotations.SerializedName;

public class LessonResource {
    @SerializedName("resource_id")
    private int resourceId;
    
    @SerializedName("file_name")
    private String fileName;
    
    @SerializedName("file_type")
    private String fileType;
    
    @SerializedName("lesson_title")
    private String lessonTitle;
    
    @SerializedName("course_name")
    private String courseName;
    
    @SerializedName("author_name")
    private String authorName;
    
    // Constructors
    public LessonResource() {}
    
    // Getters and Setters
    public int getResourceId() {
        return resourceId;
    }
    
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getFileType() {
        return fileType;
    }
    
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    public String getLessonTitle() {
        return lessonTitle;
    }
    
    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getAuthorName() {
        return authorName;
    }
    
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
