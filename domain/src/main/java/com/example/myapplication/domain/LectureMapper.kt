package com.example.myapplication.domain


interface LectureMapper {
    fun PracticalListToLectureList(practicalList: List<CourseListModel>, testModelList: List<TestModel>): MutableList<LectureList>
    fun TheoryListToLectureList(theoryList: List<TheoryList>, testModelList: List<TestModel>): MutableList<LectureList>
    }
