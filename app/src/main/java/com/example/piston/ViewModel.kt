package com.example.piston

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.data.mappers.LectureMapper_Imp
import com.example.data.db.RoomDatabase
import com.example.data.entities.*
import com.example.data.mappers.AllTestModelMapper_Imp
import com.example.myapplication.domain.model.LectureList
import com.example.myapplication.domain.model.QuizModel
import com.example.myapplication.domain.model.TestPercentEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ViewModel(application: Application) : AndroidViewModel(application) {
    val db = Room.databaseBuilder(
        application.applicationContext,
        RoomDatabase::class.java,
        "lecture_content"
    )
        .createFromAsset("database/database.db")
        .fallbackToDestructiveMigrationOnDowngrade()
        .build()


    private val LectureMapper_Imp = LectureMapper_Imp()
    private val allTestModelMapper_Imp = AllTestModelMapper_Imp()
    var practical_list = listOf<LectureList>()
    var theory_list = listOf<LectureList>()


    fun initDb() {
        getTheoryListFromDb()
        getPracticalListFromDb()
    }


    fun examList(number: Int) = flow<List<QuizModel>?> {
        var examList = db.listDao().getExamList(number.toLong())
        emit(allTestModelMapper_Imp.AllTestEntityToTestModel(examList.toAllTestList()))
    }

    fun getQuizList2(number: Int): MutableLiveData<List<QuizModel>> {
        val liveData = MutableLiveData<List<QuizModel>>()
        viewModelScope.launch(Dispatchers.IO) {
            var quizList = db.listDao().getQuizList(number.toLong())
            liveData.postValue(allTestModelMapper_Imp.AllTestEntityToTestModel(quizList.toAllTestList()))
        }
        return liveData
    }

    fun quizList(number: Int) = flow<List<QuizModel>?> {
        var quizList = db.listDao().getQuizList(number.toLong())
        emit(allTestModelMapper_Imp.AllTestEntityToTestModel(quizList.toAllTestList()))
    }

    suspend fun getQuizList(number: Int): List<QuizModel> {
        var quizList = db.listDao().getQuizList(number.toLong())
        return allTestModelMapper_Imp.AllTestEntityToTestModel(quizList.toAllTestList())
    }


    val examPercentList = flow<List<TestPercentEntity>?> {
        val list = db.listDao().getExamPercentList().toTestPercent()
        emit(list)
    }

    val quizPercentList = flow<List<TestPercentEntity>?> {
        val list = db.listDao().getQuizPercentList().toTestPercent()
        emit(list)
    }

    fun setQuizPercent(id: Int, percent: Int) = viewModelScope.launch(Dispatchers.IO) {
        db.listDao().setQuizPercent(id.toLong(), percent.toLong())
    }

    fun setExamPercent(id: Int, percent: Int) = viewModelScope.launch(Dispatchers.IO) {
        db.listDao().setExamPercent(id.toLong(), percent.toLong())
    }

    fun getPracticalListFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
//            val tempList1 = db.listDao().getPracticalCourseList()
//            val tempList2 = db.listDao().getTestList(
//                intArrayOf(56, 76, 77, 98, 92, 225, 112, 57, 211, 43, 2, 221, 73, 70, 1, 244)
//            )
//            practical_list = LectureMapper_Imp.PracticalListToLectureList(tempList1, tempList2)
        }
    }

    fun getTheoryListFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
//            val tempList1 = db.listDao().getTheoryCourseList()
//            val tempList2 = db.listDao().getTestList(
//                intArrayOf(107, 201, 60, 198, 216, 84, 167, 245)
//            )
//            theory_list = LectureMapper_Imp.TheoryListToLectureList(tempList1, tempList2)
        }
    }
}