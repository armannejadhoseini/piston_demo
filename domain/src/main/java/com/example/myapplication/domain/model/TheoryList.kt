package com.example.myapplication.domain.model


data class TheoryList(
    val id: Long,
    val type: Long?,
    val image: ByteArray?,
    val title: String,
    val page1: String,
    val page2: String,
    val page3: String,
    val page4: String,
    val page5: String,
    val page6: String,
    val course_is_done: Long?,
    val quiz_is_done: Long?
)
