package com.example.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_percent")
data class QuizPercentEntity(
    @PrimaryKey
    var id: Long,
    var name: String?,
    var percent: Long?
)