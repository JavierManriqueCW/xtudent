package com.jmp.examsfeature.domain.exams.interactor

import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import javax.inject.Inject

open class SaveExam @Inject constructor(
    private val repository: ExamsRepository
) {

    open suspend operator fun invoke(exam: Exam): Either<Failure, Long> =
        repository.saveExam(exam)
}
