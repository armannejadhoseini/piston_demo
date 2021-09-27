package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(
    AllTestModel::class,
    BoardModel::class,
    CourseListModel::class,
    GoldenTestModel::class,
    GuideLineModel::class,
    TheoryList::class,
    //SavedCourseModel::class,
    TestListModel::class
), version = 2)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun listDao(): listDao
}
