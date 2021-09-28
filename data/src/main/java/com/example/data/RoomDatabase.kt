package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entities.AllTestEntity
import com.example.data.entities.CourseListEntity
import com.example.data.entities.TheoryListEntity

@Database(entities = arrayOf(
    AllTestEntity::class,
    CourseListEntity::class,
    TheoryListEntity::class,
), version = 2)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun listDao(): listDao
}
