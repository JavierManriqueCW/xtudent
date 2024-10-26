package com.jmp.examsfeature.data.exams

import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.commons.utils.types.doOnSuccess
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import com.jmp.storage.database.XtudentDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExamsRepositoryImplementation @Inject constructor(
    private val cacheMemory: ExamsCacheMemory,
    private val database: XtudentDatabase,
    private val mapper: com.jmp.storage.database.mapper.ExamEntityMapper
) : ExamsRepository {

    override suspend fun getExamsCachedState(): Flow<List<Exam>> = cacheMemory.getExamsCachedState()

    override suspend fun saveExam(exam: Exam): Either<Failure, Long> =
        saveExamToDb(exam)
            .doOnSuccess { cacheMemory.saveExam(exam.copy(id = it)) }

    override suspend fun deleteExam(exam: Exam): Either<Failure, Unit> =
        deleteExamFromDb(exam)
            .doOnSuccess { cacheMemory.deleteExam(exam.id) }

    override suspend fun fetchExams(): Either<Failure, Unit> =
        when (val result = fetchExamsFromDb()) {
            is Either.Success -> cacheMemory.saveExams(result.data).run { Either.Success(Unit) }
            is Either.Error -> result
        }

    private suspend fun saveExamToDb(exam: Exam): Either<Failure, Long> =
        runCatching {
            database.examDao().save(mapper.mapExamToEntity(exam)).run {
                database.examQuestionDao().save(
                    mapper.mapQuestionsToEntity(exam.examQuestions, this)
                )
                Either.Success(this)
            }
        }.fold(
            onSuccess = { it },
            onFailure = { Either.Error(Failure.CouldNotSaveExam) }
        )

    private suspend fun fetchExamsFromDb(): Either<Failure, List<Exam>> =
        try {
            database.examDao().getAll().run {
                if (isEmpty())
                    Either.Success(listOf())
                else {
                    Either.Success(mapper.mapExamsFromEntity(this))
                }
            }
        } catch (e: Throwable) {
            Either.Error(Failure.CouldNotGetExamsFromDatabase)
        }

    private suspend fun deleteExamFromDb(exam: Exam): Either<Failure, Unit> =
        runCatching {
            database.examDao().delete(mapper.mapExamToEntity(exam))
        }.fold(
            onSuccess = { Either.Success(Unit) },
            onFailure = { Either.Error(Failure.CouldNotDeleteExamFromDatabase) }
        )
}
