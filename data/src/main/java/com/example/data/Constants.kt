package com.example.data

import com.example.data.Screen

object Constants {
    val BottomNavigationItems = listOf(
        Screen.Home,
        Screen.Lessons,
        Screen.Quizes,
        Screen.More
    )
    val reading_list = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    val quizRange = IntRange(start = 1,endInclusive = 10)
    val examRange = IntRange(start = 1,endInclusive = 20)

}