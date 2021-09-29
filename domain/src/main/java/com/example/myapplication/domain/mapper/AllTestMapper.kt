package com.example.myapplication.domain.mapper

import com.example.myapplication.domain.model.AllTestModel
import com.example.myapplication.domain.model.TestModel

interface AllTestMapper {
    fun AllTestEntityToTestModel(tempList: List<AllTestModel>): List<TestModel>
}