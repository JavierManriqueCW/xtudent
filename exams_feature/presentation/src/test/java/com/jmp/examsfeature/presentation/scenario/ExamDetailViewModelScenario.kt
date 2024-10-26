package com.jmp.examsfeature.presentation.scenario

import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.examsfeature.presentation.factory.ExamDetailViewModelFactory
import com.jmp.examsfeature.presentation.factory.MocksProvider
import kotlinx.coroutines.flow.flowOf
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class ExamDetailViewModelScenario(
    private val mocks: MocksProvider,
    private val factory: ExamDetailViewModelFactory
) {

    fun givenAnInitialExamDetailState() {
        whenever(mocks.examDetailUiProvider.provide())
            .thenReturn(factory.makeInitialExamDetailState())
    }

    fun givenALoadedExamDetailState() = apply {
        whenever(mocks.examDetailUiProvider.provide(any(), any(), any()))
            .thenReturn(factory.makeLoadedExamDetailState())
    }

    suspend fun givenAnExamsCachedState(): ExamDetailViewModelScenario = apply {
        whenever(mocks.getExamsCachedState.invoke())
            .thenReturn(
                flowOf(
                    factory.makeExamList()
                        .mapIndexed { index, exam -> exam.copy(id = index.toLong()) }
                )
            )
    }

    suspend fun givenASuccessfulExamDeletion() {
        whenever(mocks.deleteExam(any()))
            .thenReturn(Either.Success(Unit))
    }

    suspend fun givenAFailedExamDeletion() {
        whenever(mocks.deleteExam(any()))
            .thenReturn(Either.Error(Failure.CouldNotDeleteExamFromDatabase))
    }
}
