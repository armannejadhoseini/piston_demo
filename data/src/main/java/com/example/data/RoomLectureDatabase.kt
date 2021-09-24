package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(lecture_items::class), version = 1)
abstract class RoomLectureDatabase: RoomDatabase() {
    abstract fun itemDao(): LectureDao
}