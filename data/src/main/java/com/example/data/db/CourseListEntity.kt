package com.example.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "practical_course_table")
data class CourseListEntity(
    @PrimaryKey
    val id: Long,
    val type: Long?,
    @ColumnInfo(name = "image",typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray?,
    val title: String,
    val page1: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val page2: ByteArray,
    val page3: String,
    val page4: String,
    val page5: String,
    val page6: String,
    val course_is_done: Long?,
    val quiz_is_done: Long?
)