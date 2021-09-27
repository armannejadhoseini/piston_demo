package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exam_percent")
data class TestListModel(
    @PrimaryKey
    var id: Long,
    var name: String?,
    var percent: Long?
)
