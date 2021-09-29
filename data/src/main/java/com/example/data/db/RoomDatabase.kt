package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.entities.*
open class SingletonHolder<T, A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator

    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}
@Database(
    entities = arrayOf(
        QuizEntity::class,
        ExamEntity::class,
        CourseListEntity::class,
        TheoryListEntity::class,
        ExamPercentEntity::class,
        QuizPercentEntity::class
    ), version = 2, exportSchema = false
)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun listDao(): listDao
}
