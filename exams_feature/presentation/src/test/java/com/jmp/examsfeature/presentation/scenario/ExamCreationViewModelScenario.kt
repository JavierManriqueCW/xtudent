package com.jmp.examsfeature.presentation.scenario

import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.examsfeature.presentation.factory.ExamCreationViewModelFactory
import com.jmp.examsfeature.presentation.factory.MocksProvider
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ExamCreationViewModelScenario(
    private val mocks: MocksProvider,
    private val factory: ExamCreationViewModelFactory
) {

    fun givenAnInitialExamCreationState() {
        whenever(mocks.examCreationUiProvider.provide())
            .thenReturn(factory.makeInitialExamCreationState())
    }

    suspend fun givenThatImportWillSucceed() {
        whenever(mocks.getExamFromXlsxFile.invoke(any()))
            .thenReturn(Either.Success(factory.makeExamList().first()))
        whenever(mocks.saveExam.invoke(any()))
            .thenReturn(Either.Success(0))
    }

    suspend fun thenExamIsImportedSuccessfully() {
        verify(mocks.getExamFromXlsxFile).invoke(any())
        verify(mocks.saveExam).invoke(any())
    }

    fun thenUiStateIsCleared(
        error: Int?,
        navigateBack: Boolean
    ) {
        Assert.assertNull(error)
        Assert.assertFalse(navigateBack)
    }

    suspend fun givenThatImportWillFail() {
        whenever(mocks.getExamFromXlsxFile.invoke(any()))
            .thenReturn(Either.Error(Failure.WrongExamFormat))
    }

    suspend fun thenExamImportFails(error: Int?) {
        assertNotNull(error)
        verify(mocks.getExamFromXlsxFile).invoke(any())
        verify(mocks.saveExam, times(0)).invoke(any())
    }
}
