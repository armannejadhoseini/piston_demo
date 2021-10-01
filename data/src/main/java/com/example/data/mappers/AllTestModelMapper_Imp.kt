package com.example.data.mappers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.myapplication.domain.mapper.AllTestMapper
import com.example.myapplication.domain.model.AllTestModel
import com.example.myapplication.domain.model.QuizModel


fun convertByteArrayToBitmap(byteArray: ByteArray?): Bitmap? {
    byteArray?.let {
        return BitmapFactory.decodeByteArray(it,0,it.size)
    }
    return null
}
class AllTestModelMapper_Imp: AllTestMapper {
    override fun AllTestEntityToTestModel(tempList: List<AllTestModel>): List<QuizModel> {
        val list = mutableListOf<QuizModel>()
        tempList.forEach { item ->
            list.add(
                QuizModel(
                    item.id.toInt(),
                    item.quiz_number.toInt(),
                    item.title,
                    item.answer1,
                    item.answer2,
                    item.answer3,
                    item.answer4,
                    item.true_answer.toInt(),
                    convertByteArrayToBitmap(item.image)
                )
            )
        }
        return list
    }
}