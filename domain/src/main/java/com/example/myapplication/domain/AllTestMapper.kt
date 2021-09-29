package com.example.myapplication.domain

interface AllTestMapper {
    fun AllTestEntityToTestModel(tempList: List<AllTestModel>): List<TestModel>

}