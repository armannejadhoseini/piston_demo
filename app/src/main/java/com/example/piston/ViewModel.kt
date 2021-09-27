package com.example.piston

import android.app.Application
import android.util.Log
import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.example.data.RoomDatabase
import com.example.data.CourseListModel
import com.example.data.LectureList
import com.example.data.TheoryList
import com.example.myapplication.domain.Repository
import kotlinx.coroutines.*

class ViewModel(application: Application) : AndroidViewModel(application) {

    val db = Room.databaseBuilder(
        application.applicationContext,
        RoomDatabase::class.java,
        "lecture_content"
    ).createFromAsset("database/database.db")
        .fallbackToDestructiveMigration()
        .build()

    private val repository = Repository()
    var practical_list = listOf<LectureList>()
    var theory_list = listOf<LectureList>()

    val theory_images = listOf<Int>(
        R.drawable.theory0,
        R.drawable.theory1,
        R.drawable.theory2,
        R.drawable.theory3
    )
    val practical_images = listOf<Int>(
        R.drawable.practical0,
        R.drawable.practical1,
        R.drawable.practical2,
        R.drawable.practical3,
        R.drawable.practical4,
        R.drawable.practical5,
        R.drawable.practical6,
        R.drawable.practical7
    )

    fun getPracticalListFromDb() {
        GlobalScope.launch {
            val tempList = db.listDao().getPracticalCourseList()
            practical_list = repository.PracticalListToLectureList(tempList)
        }
    }
    fun getTheoryListFromDb() {
        GlobalScope.launch {
            val tempList = db.listDao().getTheoryCourseList()
            theory_list = repository.TheoryListToLectureList(tempList)
        }
    }





}