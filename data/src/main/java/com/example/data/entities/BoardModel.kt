package com.example.data.entities

import android.graphics.Bitmap
import androidx.room.*
import java.util.*

@Entity(tableName = "Board_table")
data class BoardModel(
    @PrimaryKey
    val id: Long,
    val type: String, val title: String,
    @Ignore
    val image: Bitmap,
) {
//    constructor(type: String,title: String,image: Bitmap) : this(0, type, title, image)
}
@Entity(tableName = "board_table")
data class Board(
    @PrimaryKey
    val id: Long,
    val type: String,
    val title: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val imageBase64: ByteArray
)

