package com.example.edulearn.models

import java.util.Date

data class CalendarEvent(
    val id: Int,
    val title: String,
    val description: String,
    val date: Date,
    val courseName: String,
    val assignmentId: Int
)
