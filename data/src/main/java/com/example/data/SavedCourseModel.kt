package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_course_page")
data class SavedCourseModel(
    @PrimaryKey(autoGenerate = true)
    val course_id: Long? = 0,
    val course_type: Long?,
    val course_page: Long?
)

