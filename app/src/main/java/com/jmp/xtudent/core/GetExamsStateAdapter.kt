package com.jmp.xtudent.core

import com.jmp.common.usecase.exams.GetExamsState
import com.jmp.commons.utils.model.Exam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExamsStateAdapter @Inject constructor(
    private val useCase: com.jmp.examsfeature.domain.exams.interactor.GetExamsState
) : GetExamsState {

    override suspend fun invoke(): Flow<List<Exam>> = useCase()
}
