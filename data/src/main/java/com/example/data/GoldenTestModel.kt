package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "golden_quiz")
data class GoldenTestModel(
    @PrimaryKey
    val id: Long,
    val part: Long?,
    val title: String?,
    val text: String?
)
