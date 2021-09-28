package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entities.*

@Database(
    entities = arrayOf(
//        AllTestModel::class,
        Board::class,
//        CourseListModel::class,
//        GoldenTestModel::class,
        GuideLine::class,
//        SavedCourseModel::class,
//        TestListModel::class,
    ), version = 2,exportSchema = false
)
abstract class RoomAppDatabase : RoomDatabase() {
    abstract fun itemDao(): AppDao
}