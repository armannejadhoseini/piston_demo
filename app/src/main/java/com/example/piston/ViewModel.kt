package com.example.piston

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.data.LectureMapper_Imp
import com.example.data.db.RoomDatabase
import com.example.myapplication.domain.LectureList
import kotlinx.coroutines.*

class ViewModel(application: Application) : AndroidViewModel(application) {

    val db = Room.databaseBuilder(
        application.applicationContext,
        RoomDatabase::class.java,
        "lecture_content"
    ).createFromAsset("database/database.db")
        .fallbackToDestructiveMigration()
        .build()

    private val LectureMapper_Imp = LectureMapper_Imp()
    var practical_list = listOf<LectureList>()
    var theory_list = listOf<LectureList>()

    fun getPracticalListFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val tempList1 = db.listDao().getPracticalCourseList()
            val tempList2 = db.listDao().getTestList(
                intArrayOf(56, 76, 77, 98, 92, 225, 112, 57, 211, 43, 2, 221, 73, 70, 1, 244)
            )
            practical_list = LectureMapper_Imp.PracticalListToLectureList(tempList1, tempList2)
        }
    }

    fun getTheoryListFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val tempList1 = db.listDao().getTheoryCourseList()
            val tempList2 = db.listDao().getTestList(
                intArrayOf(107, 201, 60, 198, 216, 84, 167, 245)
            )
            theory_list = LectureMapper_Imp.TheoryListToLectureList(tempList1, tempList2)
        }
    }
}