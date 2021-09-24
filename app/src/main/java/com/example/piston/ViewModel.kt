package com.example.piston

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.data.RoomLectureDatabase
import com.example.data.lecture_items
import kotlinx.coroutines.*
import java.io.File

class ViewModel(application: Application) : AndroidViewModel(application) {

    val db = Room.databaseBuilder(
        application.applicationContext,
        RoomLectureDatabase::class.java,
        "lecture_content"
    )
        .build()



    var list = listOf<lecture_items>()

    fun getListFromDb() {
        GlobalScope.launch {
            list = db.itemDao().getAll()

        }
    }


}