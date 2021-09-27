package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exam_table")
data class AllTestModel(
    @PrimaryKey
    val id: Long,
    val title: String,
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val true_answer: Long,
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray?
)