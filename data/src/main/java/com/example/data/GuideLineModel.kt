package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guideline_table")
data class GuideLineModel(
    @PrimaryKey
    val id: Long,
    val title: String,
    val text: String,
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray
)




