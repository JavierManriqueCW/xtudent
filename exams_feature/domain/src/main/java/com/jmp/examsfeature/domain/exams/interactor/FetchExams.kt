package com.jmp.examsfeature.domain.exams.interactor

import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import javax.inject.Inject

open class FetchExams @Inject constructor(
    private val repository: ExamsRepository
) {

    open suspend operator fun invoke(): Either<Failure, Unit> {
        return repository.fetchExams()
    }
}
