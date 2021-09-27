package com.example.data

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.domain.CourseListModel
import com.example.myapplication.domain.TheoryList

@Dao
interface listDao {

    @Query("SELECT * FROM practical_course_table")
    fun getPracticalCourseList(): List<CourseListModel>

    @Query("SELECT * FROM theory_course_table")
    fun getTheoryCourseList(): List<TheoryList>
}