package com.jmp.examsfeature.domain.exams.interactor

import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import javax.inject.Inject

open class DeleteExam @Inject constructor(
    private val repository: ExamsRepository
) {

    suspend operator fun invoke(exam: Exam): Either<Failure, Unit> {
        return repository.deleteExam(exam)
    }
}
