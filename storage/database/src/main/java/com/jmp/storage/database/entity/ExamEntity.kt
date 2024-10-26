package com.jmp.storage.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmp.storage.database.DatabaseConstants

@Entity(tableName = DatabaseConstants.EXAMS_TABLE)
data class ExamEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exam_id")
    val examId: Long = 0,
    val name: String,
    val description: String,
    val image: String
)
