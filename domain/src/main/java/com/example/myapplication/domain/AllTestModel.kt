package com.example.myapplication.domain


data class AllTestModel(
    val id: Long,
    val quiz_number : Long,
    val title: String,
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val true_answer: Long,
    val image: ByteArray?
)