package com.jmp.examsfeature.domain.exams.interactor

import com.jmp.commons.utils.model.Exam
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GetExamsState @Inject constructor(
    private val repository: ExamsRepository
) {

    open suspend operator fun invoke(): Flow<List<Exam>> {
        return repository.getExamsCachedState()
    }
}
