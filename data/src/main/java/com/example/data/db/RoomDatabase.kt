package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entities.*

@Database(entities = arrayOf(
    QuizEntity::class,
    ExamEntity::class,
    CourseListEntity::class,
    TheoryListEntity::class,
    ExamPercentEntity::class,
    QuizPercentEntity::class
), version = 2,exportSchema = false)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun listDao(): listDao
}
