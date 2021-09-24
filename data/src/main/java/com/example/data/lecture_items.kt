package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lecture_contents")
data class lecture_items(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val page1: String,
    val page2: String,
    val page3: String,
    val page4: String,
    val page5: String,
    val page6: String,

    )
