package com.example.data.db

import androidx.annotation.IntRange
import androidx.annotation.LongDef
import androidx.room.Dao
import androidx.room.Query
import com.example.data.entities.ExamEntity
import com.example.data.entities.ExamPercentEntity
import com.example.data.entities.QuizEntity
import com.example.data.entities.QuizPercentEntity
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
    fun getExamPercentList(): List<ExamPercentEntity>

    @Query("SELECT * FROM quiz_percent")
    fun getQuizPercentList(): List<QuizPercentEntity>

    @Query("update exam_percent set percent = (:percent) where id In (:id)")
    fun setExamPercent(id: Long, percent: Long)

    @Query("update quiz_percent set percent = (:percent) where id In (:id)")
    fun setQuizPercent(id: Long, percent: Long)

    @Query("select * from quiz_table where quiz_number in (:quizNumber)")
    fun getQuizList(
        @IntRange(from = 1, to = 10)
        quizNumber: Long
    ): List<QuizEntity>

    @Query("select * from exam_tabel where exam_number in (:examNumber)")
    fun getExamList(
        @IntRange(from = 1, to = 20)
        examNumber: Long
    ): List<ExamEntity>

    


}