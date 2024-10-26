package com.jmp.common.usecase.exams

import com.jmp.commons.utils.model.Exam
import kotlinx.coroutines.flow.Flow

interface GetExamsState {
    suspend operator fun invoke(): Flow<List<Exam>>
}
