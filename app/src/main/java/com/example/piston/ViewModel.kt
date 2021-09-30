package com.example.piston

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.data.BoardMapper_Imp
import com.example.data.LectureMapper_Imp
import com.example.data.RoomDatabase
import com.example.myapplication.domain.BoardList
import com.example.myapplication.domain.BoardModel
import com.example.myapplication.domain.LectureList
import kotlinx.coroutines.*

class ViewModel(application: Application) : AndroidViewModel(application) {


    private val LectureMapper_Imp = LectureMapper_Imp()
    private val BoardMapper_Imp = BoardMapper_Imp()
    private val applicationContext = application
    var practical_list = listOf<LectureList>()
    var theory_list = listOf<LectureList>()
    var all_board_list = listOf<BoardList>()

    fun getPracticalListFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val tempList1 = database.getInstance(applicationContext).listDao().getPracticalCourseList()
            val tempList2 = database.getInstance(applicationContext).listDao().getTestList(
                intArrayOf(56, 76, 77, 98, 92, 225, 112, 57, 211, 43, 2, 221, 73, 70, 1, 244)
            )
            practical_list = LectureMapper_Imp.PracticalListToLectureList(tempList1, tempList2)
        }
    }

    fun getTheoryListFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val tempList1 = database.getInstance(applicationContext).listDao().getTheoryCourseList()
            val tempList2 = database.getInstance(applicationContext).listDao().getTestList(
                intArrayOf(107, 201, 60, 198, 216, 84, 167, 245)
            )
            theory_list = LectureMapper_Imp.TheoryListToLectureList(tempList1, tempList2)
        }
    }

    fun getAllBoardsListFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val tempList = database.getInstance(applicationContext).listDao().getAllBoradsList()
            all_board_list = BoardMapper_Imp.BoardEntityToBoardModel(tempList)
        }
    }

    object database {

        @Volatile private var INSTANCE: RoomDatabase? = null

        fun getInstance(application: Application): RoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(application).also { INSTANCE = it }
            }

        private fun buildDatabase(application: Application) =
            Room.databaseBuilder(application.applicationContext,
                RoomDatabase::class.java, "lecture_content")
                .createFromAsset("database/database.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}