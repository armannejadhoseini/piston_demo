package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Board_table")
data class BoardModel(
    @PrimaryKey
    val id: Long,
    val type: String,
    val title: String,
    @ColumnInfo(name = "image",typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray?
    )
