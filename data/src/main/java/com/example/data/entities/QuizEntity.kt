package com.example.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_table")
data class QuizEntity(
    @PrimaryKey
    val id: Long,
    val quiz_number : Long?,
    val title: String,
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val true_answer: Long,
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray?
)

