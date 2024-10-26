package com.jmp.xtudent.core.di.fake


import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

open class FakeExamsRepositoryImplementation : ExamsRepository {

    override suspend fun getExamsCachedState(): Flow<List<Exam>> =
        flowOf()

    override suspend fun saveExam(exam: Exam): Either<Failure, Long> =
        Either.Success(0)

    override suspend fun fetchExams(): Either<Failure, Unit> =
        Either.Success(Unit)

    override suspend fun deleteExam(exam: Exam): Either<Failure, Unit> =
        Either.Success(Unit)
}
