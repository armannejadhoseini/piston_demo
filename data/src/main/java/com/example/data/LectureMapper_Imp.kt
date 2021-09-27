package com.example.data

import android.graphics.BitmapFactory
import com.example.myapplication.domain.CourseListModel
import com.example.myapplication.domain.LectureList
import com.example.myapplication.domain.LectureMapper
import com.example.myapplication.domain.TheoryList

class LectureMapper_Imp : LectureMapper {
    override fun PracticalListToLectureList(practicalList: List<CourseListModel>): MutableList<LectureList> {
        val LectureList = mutableListOf<LectureList>()
        practicalList.forEach { item ->
            LectureList.add(
                LectureList(
                    item.id.toInt(),
                    BitmapFactory.decodeByteArray(item.image, 0, item.image!!.size),
                    item.title,
                    item.page1,
                    item.page2.toString(),
                    item.page3,
                    item.page4,
                    item.page5,
                    item.page6
                )
            )
        }
        return LectureList
    }

    override fun TheoryListToLectureList(theoryList: List<TheoryList>): MutableList<LectureList> {
        val LectureList = mutableListOf<LectureList>()
        theoryList.forEach { item ->
            LectureList.add(
                LectureList(
                    item.id.toInt(),
                    BitmapFactory.decodeByteArray(item.image, 0, item.image!!.size),
                    item.title,
                    item.page1,
                    item.page2,
                    item.page3,
                    item.page4,
                    item.page5,
                    item.page6
                )
            )
        }
        return LectureList
    }
}


