package com.example.myapplication.domain

import android.graphics.BitmapFactory
import com.example.data.CourseListModel
import com.example.data.LectureList
import com.example.data.TheoryList

class Repository {

    fun PracticalListToLectureList(practicalList: List<CourseListModel>): MutableList<LectureList> {
        val LectureList = mutableListOf<LectureList>()
        practicalList.forEach { item ->
            LectureList.add(LectureList(
                item.id.toInt(),
                BitmapFactory.decodeByteArray(item.image,0,item.image!!.size),
                item.title,
                item.page1,
                item.page2.toString(),
                item.page3,
                item.page4,
                item.page5,
                item.page6
            ))
        }
        return LectureList
    }
    fun TheoryListToLectureList(theoryList: List<TheoryList>): MutableList<LectureList> {
        val LectureList = mutableListOf<LectureList>()
        theoryList.forEach { item ->
            LectureList.add(LectureList(
                item.id.toInt(),
                BitmapFactory.decodeByteArray(item.image,0,item.image!!.size),
                item.title,
                item.page1,
                item.page2,
                item.page3,
                item.page4,
                item.page5,
                item.page6
            ))
        }
        return  LectureList
    }
}