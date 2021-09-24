package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LectureDao {
    @Insert
    fun insertAll(lectureItems: lecture_items)

    @Query("SELECT * FROM lecture_contents")
    fun getAll(): List<lecture_items>
}