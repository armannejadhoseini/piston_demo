package com.example.myapplication.domain


interface LectureMapper {
    fun PracticalListToLectureList(practicalList: List<CourseListModel>): MutableList<LectureList>
    fun TheoryListToLectureList(theoryList: List<TheoryList>): MutableList<LectureList>
    }
