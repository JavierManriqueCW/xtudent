package com.jmp.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jmp.storage.database.converter.Converters
import com.jmp.storage.database.dao.ExamDao
import com.jmp.storage.database.dao.ExamQuestionDao
import com.jmp.storage.database.entity.ExamEntity
import com.jmp.storage.database.entity.ExamQuestionEntity

@Database(
    entities = [
        ExamEntity::class,
        ExamQuestionEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converters::class)
abstract class XtudentDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: XtudentDatabase? = null
        private const val DATABASE_NAME = "xtudent.db"

        fun getInstance(context: Context): XtudentDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    XtudentDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun examDao(): ExamDao

    abstract fun examQuestionDao(): ExamQuestionDao
}
