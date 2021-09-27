package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theory_course_table")
data class TheoryListEntity(
    @PrimaryKey
    val id: Long,
    val type: Long?,
    @ColumnInfo(name = "image",typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray?,
    val title: String,
    val page1: String,
    val page2: String,
    val page3: String,
    val page4: String,
    val page5: String,
    val page6: String,
    val course_is_done: Long?,
    val quiz_is_done: Long?
)
