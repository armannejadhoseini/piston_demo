package com.example.data.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "saved_course_page")
data class SavedCourseModel(
    @PrimaryKey
    val id: Long,
    val course_type: String,
    val title: String,
    val body: String,
    val pageNum: Long,
    val image: Bitmap
)

