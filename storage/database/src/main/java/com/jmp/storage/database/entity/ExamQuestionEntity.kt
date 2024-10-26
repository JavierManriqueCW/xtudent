package com.jmp.storage.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmp.storage.database.DatabaseConstants

@Entity(tableName = DatabaseConstants.QUESTIONS_TABLE)
data class ExamQuestionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    var questionId: Long = 0,
    val question: String,
    val rightAnswer: String,
    val wrongAnswers: List<String>,
    @ColumnInfo(name = "exam_id_fk")
    val examIdFk: Long = 0
)
