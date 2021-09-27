package com.example.piston

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.example.data.LectureMapper_Imp
import com.example.data.RoomDatabase
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
        GlobalScope.launch {
            val tempList = db.listDao().getPracticalCourseList()
            practical_list = LectureMapper_Imp.PracticalListToLectureList(tempList)
        }
    }
    fun getTheoryListFromDb() {
        GlobalScope.launch {
            val tempList = db.listDao().getTheoryCourseList()
            theory_list = LectureMapper_Imp.TheoryListToLectureList(tempList)
        }
    }



}