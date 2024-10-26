package com.jmp.examsfeature.domain.exams.repository

import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import kotlinx.coroutines.flow.Flow

interface ExamsRepository {

    suspend fun getExamsCachedState(): Flow<List<Exam>>

    suspend fun saveExam(exam: Exam): Either<Failure, Long>

    suspend fun fetchExams(): Either<Failure, Unit>

    suspend fun deleteExam(exam: Exam): Either<Failure, Unit>
}
