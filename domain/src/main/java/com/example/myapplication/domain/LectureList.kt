package com.example.myapplication.domain

import android.graphics.Bitmap


data class LectureList(
    val id: Int,
    val image: Bitmap,
    val title: String,
    val page1: String,
    val page2: String,
    val page3: String,
    val page4: String,
    val page5: String,
    val page6: String,
)
