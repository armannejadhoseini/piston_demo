package com.example.data

import androidx.room.Dao
import androidx.room.Query
import com.example.data.entities.ExamEntity
import com.example.data.entities.QuizEntity
import com.example.myapplication.domain.CourseListModel
import com.example.myapplication.domain.TestModel
import com.example.myapplication.domain.TheoryList

@Dao
interface listDao {
    @Query("SELECT * FROM practical_course_table")
    fun getPracticalCourseList(): List<CourseListModel>

    @Query("SELECT * FROM theory_course_table")
    fun getTheoryCourseList(): List<TheoryList>

    @Query("SELECT * FROM quiz_table WHERE id IN (:id)")
    fun getTestList(id: IntArray): List<TestModel>

    @Query("SELECT * FROM exam_percent")
    fun getExamListModel(): List<ExamEntity>

    @Query("SELECT * FROM quiz_percent")
    fun getQuizListModel(): List<QuizEntity>
}