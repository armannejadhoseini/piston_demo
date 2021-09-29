package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entities.*

@Database(entities = arrayOf(
    AllTestEntity::class,
    CourseListEntity::class,
    TheoryListEntity::class,
    ExamEntity::class,
    QuizEntity::class
), version = 2,exportSchema = false)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun listDao(): listDao
}
