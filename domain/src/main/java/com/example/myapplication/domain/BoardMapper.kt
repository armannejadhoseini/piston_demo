package com.example.myapplication.domain

interface BoardMapper {
    fun BoardEntityToBoardModel(boardEntityList: List<BoardModel>): List<BoardList>
}