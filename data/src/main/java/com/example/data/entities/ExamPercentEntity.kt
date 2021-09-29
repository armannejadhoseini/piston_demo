package com.example.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "exam_percent")
data class ExamPercentEntity(
    @PrimaryKey
    var id: Long,
    var name: String?,
    var percent: Long?,
)


