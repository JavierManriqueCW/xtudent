package com.jmp.storage.database.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.jmp.storage.database.entity.ExamQuestionEntity

@Dao
interface ExamQuestionDao {

    @Upsert
    suspend fun save(questions: List<ExamQuestionEntity>)
}
