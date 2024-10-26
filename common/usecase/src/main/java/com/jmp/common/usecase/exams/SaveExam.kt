package com.jmp.common.usecase.exams

import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure

interface SaveExam {
    suspend operator fun invoke(exam: Exam): Either<Failure, Long>
}
