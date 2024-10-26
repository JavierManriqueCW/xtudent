package com.jmp.storage.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ExamWithQuestions(
    @Embedded
    val exam: ExamEntity,
    @Relation(parentColumn = "exam_id", entityColumn = "exam_id_fk")
    val questions: List<ExamQuestionEntity> = listOf()
)
