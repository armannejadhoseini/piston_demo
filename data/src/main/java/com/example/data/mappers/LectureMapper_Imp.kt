package com.example.data.mappers

import android.graphics.BitmapFactory
import com.example.myapplication.domain.model.LectureList
import com.example.myapplication.domain.mapper.LectureMapper
import com.example.myapplication.domain.model.QuizModel
import com.example.myapplication.domain.model.TheoryList
import com.example.myapplication.domain.model.CourseListModel


class LectureMapper_Imp : LectureMapper {
    override fun PracticalListToLectureList(
        practicalList: List<CourseListModel>,
        quizModelList: List<QuizModel>
    ): MutableList<LectureList> {
        val LectureList = mutableListOf<LectureList>()
        practicalList.forEachIndexed { index, item ->
            LectureList.add(
                LectureList(
                    item.id.toInt(),
                    BitmapFactory.decodeByteArray(item.image, 0, item.image!!.size),
                    item.title,
                    item.page1,
                    String(item.page2),
                    item.page3,
                    item.page4,
                    item.page5,
                    item.page6,
                    item.id.toInt(),
                    quizModelList[index].title,
                    quizModelList[index].answer1,
                    quizModelList[index].answer2,
                    quizModelList[index].answer3,
                    quizModelList[index].answer4,
                    quizModelList[index].true_answer,
                    item.id.toInt(),
                    quizModelList[index + 1].title,
                    quizModelList[index + 1].answer1,
                    quizModelList[index + 1].answer2,
                    quizModelList[index + 1].answer3,
                    quizModelList[index + 1].answer4,
                    quizModelList[index + 1].true_answer
                )
            )
        }
        return LectureList
    }

    override fun TheoryListToLectureList(
        theoryList: List<TheoryList>,
        quizModelList: List<QuizModel>
    ): MutableList<LectureList> {
        val LectureList = mutableListOf<LectureList>()
        theoryList.forEachIndexed { index, item ->
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
                    item.page6,
                    item.id.toInt(),
                    quizModelList[index].title,
                    quizModelList[index].answer1,
                    quizModelList[index].answer2,
                    quizModelList[index].answer3,
                    quizModelList[index].answer4,
                    quizModelList[index].true_answer,
                    item.id.toInt(),
                    quizModelList[index + 1].title,
                    quizModelList[index + 1].answer1,
                    quizModelList[index + 1].answer2,
                    quizModelList[index + 1].answer3,
                    quizModelList[index + 1].answer4,
                    quizModelList[index + 1].true_answer
                )
            )
        }
        return LectureList
    }
}


