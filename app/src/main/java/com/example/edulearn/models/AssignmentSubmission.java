package com.example.edulearn.models;

import com.google.gson.annotations.SerializedName;

public class AssignmentSubmission {
    @SerializedName("submissionId")
    private int submissionId;
    
    @SerializedName("assignmentTitle")
    private String assignmentTitle;
    
    @SerializedName("studentId")
    private int studentId;
    
    @SerializedName("studentName")
    private String studentName;
    
    @SerializedName("fileName")
    private String fileName;
    
    @SerializedName("fileType")
    private String fileType;
    
    @SerializedName("grade")
    private int grade;
    
    @SerializedName("isGraded")
    private boolean isGraded;
    
    @SerializedName("submissionDate")
    private String submissionDate;
    
    // Constructors
    public AssignmentSubmission() {}
    
    // Getters and Setters
    public int getSubmissionId() {
        return submissionId;
    }
    
    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }
    
    public String getAssignmentTitle() {
        return assignmentTitle;
    }
    
    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
    
    public int getGrade() {
        return grade;
    }
    
    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    public boolean isGraded() {
        return isGraded;
    }
    
    public void setGraded(boolean graded) {
        isGraded = graded;
    }
    
    public String getSubmissionDate() {
        return submissionDate;
    }
    
    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }
}
