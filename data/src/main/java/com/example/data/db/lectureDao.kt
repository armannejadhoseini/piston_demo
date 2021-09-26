package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entities.Board
import com.example.data.entities.BoardModel
import com.example.data.entities.CourseListModel
import com.example.data.entities.GuideLine

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setAllBoard(boards: List<Board>)

    @Query("SELECT * FROM board_table")
    fun getBoards(): List<Board>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGuideLine(guides: List<GuideLine>)

}