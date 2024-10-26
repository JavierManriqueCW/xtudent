package com.jmp.common.usecase.exams

import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure

interface FetchExams {
    suspend operator fun invoke(): Either<Failure, Unit>
}
