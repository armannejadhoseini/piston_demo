package com.example.myapplication.domain.mapper

import com.example.myapplication.domain.model.CourseListModel
import com.example.myapplication.domain.model.LectureList
import com.example.myapplication.domain.model.QuizModel
import com.example.myapplication.domain.model.TheoryList


interface LectureMapper {
    fun PracticalListToLectureList(
        practicalList: List<CourseListModel>,
        quizModelList: List<QuizModel>
    ): MutableList<LectureList>

    fun TheoryListToLectureList(
        theoryList: List<TheoryList>,
        quizModelList: List<QuizModel>
    ): MutableList<LectureList>
}
