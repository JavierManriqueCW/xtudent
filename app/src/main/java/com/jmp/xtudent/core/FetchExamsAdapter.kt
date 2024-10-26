package com.jmp.xtudent.core

import com.jmp.common.usecase.exams.FetchExams
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import javax.inject.Inject

class FetchExamsAdapter @Inject constructor(
    private val useCase: com.jmp.examsfeature.domain.exams.interactor.FetchExams
) : FetchExams {

    override suspend fun invoke(): Either<Failure, Unit> = useCase()
}
