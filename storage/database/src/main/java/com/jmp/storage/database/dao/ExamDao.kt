package com.jmp.storage.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.jmp.storage.database.DatabaseConstants
import com.jmp.storage.database.entity.ExamEntity
import com.jmp.storage.database.entity.ExamWithQuestions

@Dao
interface ExamDao {

    @Upsert
    suspend fun save(exam: ExamEntity): Long

    @Transaction
    @Query(DatabaseConstants.GET_EXAMS)
    suspend fun getAll(): List<ExamWithQuestions>

    @Delete
    suspend fun delete(exam: ExamEntity)

    @Query(DatabaseConstants.CLEAR_EXAMS)
    suspend fun clear()
}
