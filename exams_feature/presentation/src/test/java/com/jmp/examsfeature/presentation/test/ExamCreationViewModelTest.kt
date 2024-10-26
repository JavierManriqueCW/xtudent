package com.jmp.examsfeature.presentation.test

import android.net.Uri
import com.jmp.commons.utils.test.UnitTest
import com.jmp.examsfeature.presentation.creation.intent.ExamCreationIntent
import com.jmp.examsfeature.presentation.creation.viewmodel.ExamCreationViewModel
import com.jmp.examsfeature.presentation.factory.ExamCreationViewModelFactory
import com.jmp.examsfeature.presentation.factory.MocksProvider
import com.jmp.examsfeature.presentation.scenario.ExamCreationViewModelScenario
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class ExamCreationViewModelTest : UnitTest() {

    private lateinit var sut: ExamCreationViewModel
    private val mocks: MocksProvider = MocksProvider()
    private val factory: ExamCreationViewModelFactory = ExamCreationViewModelFactory()
    private val scenario: ExamCreationViewModelScenario =
        ExamCreationViewModelScenario(
            mocks,
            factory
        )

    @Before
    fun setUp() {
        scenario.givenAnInitialExamCreationState()
        sut = ExamCreationViewModel(
            mocks.getExamFromXlsxFile,
            mocks.saveExam,
            mocks.examCreationUiProvider
        )
    }

    @Test
    fun `ImportExam intent retrieves exam from xlsx file and saves it`() = runTest {
        scenario.givenThatImportWillSucceed()

        sut.sendIntent(
            ExamCreationIntent.ImportExam(
                suspend { mock(Uri::class.java) }
            )
        )

        scenario.thenExamIsImportedSuccessfully()
    }

    @Test
    fun `ImportExam intent fails to retrieve exam from xlsx`() = runTest {
        scenario.givenThatImportWillFail()

        sut.sendIntent(
            ExamCreationIntent.ImportExam(
                suspend { mock(Uri::class.java) }
            )
        )

        scenario.thenExamImportFails(sut.uiState.value.error)
    }

    @Test
    fun `Clear intent clears ui state`() = runTest {
        sut.sendIntent(ExamCreationIntent.Clear)

        sut.uiState.value.let { state ->
            scenario.thenUiStateIsCleared(
                state.error,
                state.navigateBack
            )
        }
    }
}
