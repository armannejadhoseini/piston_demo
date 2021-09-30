package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Board_table")
data class BoardEntity (
    @PrimaryKey
    val id: Int,
    val type: String,
    val title: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray?
        )
