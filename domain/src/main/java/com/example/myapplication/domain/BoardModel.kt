package com.example.myapplication.domain


data class BoardModel (
    val id: Int,
    val type: Int,
    val title:String,
    val image : ByteArray?
        )