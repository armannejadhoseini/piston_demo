package com.example.data.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class GuideLineModel(
    @PrimaryKey
    val id: Long, val title: String, val text: String,
    val image: Bitmap
)

@Entity(tableName = "guideline")
data class GuideLine(@PrimaryKey
                     val id: Long, val title: String, val text: String,
                     @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
                     val image: ByteArray)
