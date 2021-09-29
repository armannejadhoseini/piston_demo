package com.example.myapplication.domain.mapper

import com.example.myapplication.domain.model.CourseListModel
import com.example.myapplication.domain.model.LectureList
import com.example.myapplication.domain.model.TestModel
import com.example.myapplication.domain.model.TheoryList


interface LectureMapper {
    fun PracticalListToLectureList(
        practicalList: List<CourseListModel>,
        testModelList: List<TestModel>
    ): MutableList<LectureList>

    fun TheoryListToLectureList(
        theoryList: List<TheoryList>,
        testModelList: List<TestModel>
    ): MutableList<LectureList>
}
