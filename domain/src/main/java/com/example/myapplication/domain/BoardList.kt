package com.example.myapplication.domain

import android.graphics.Bitmap

data class BoardList (
    val id: Int,
    val type: Int,
    val title:String,
    val image : Bitmap
        )