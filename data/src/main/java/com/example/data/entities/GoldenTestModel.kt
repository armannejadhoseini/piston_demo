package com.example.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "golden_quiz")
data class GoldenTestModel(
    @PrimaryKey val id : Long, val part : Long, val title : String, val answer : String) {


    constructor(part: Long,title: String,answer: String) :this(0,part, title, answer)

}
