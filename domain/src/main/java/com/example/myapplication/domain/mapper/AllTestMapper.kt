package com.example.myapplication.domain.mapper

import com.example.myapplication.domain.model.AllTestModel
import com.example.myapplication.domain.model.QuizModel

interface AllTestMapper {
    fun AllTestEntityToTestModel(tempList: List<AllTestModel>): List<QuizModel>
}