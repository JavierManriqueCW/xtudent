package com.jmp.examsfeature.presentation.test

import com.jmp.common.ui.model.ComponentState
import com.jmp.commons.utils.test.UnitTest
import com.jmp.examsfeature.presentation.detail.viewmodel.ExamDetailIntent
import com.jmp.examsfeature.presentation.detail.viewmodel.ExamDetailViewModel
import com.jmp.examsfeature.presentation.factory.ExamDetailViewModelFactory
import com.jmp.examsfeature.presentation.factory.MocksProvider
import com.jmp.examsfeature.presentation.factory.ViewModelFactory.Companion.EXAM_ID
import com.jmp.examsfeature.presentation.scenario.ExamDetailViewModelScenario
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class ExamDetailViewModelTest : UnitTest() {

    private lateinit var sut: ExamDetailViewModel
    private val mocks: MocksProvider = MocksProvider()
    private val factory: ExamDetailViewModelFactory = ExamDetailViewModelFactory()
    private val scenario: ExamDetailViewModelScenario =
        ExamDetailViewModelScenario(
            mocks,
            factory
        )

    @Before
    fun setUp() {
        scenario.givenAnInitialExamDetailState()
        sut = ExamDetailViewModel(
            mocks.examDetailUiProvider,
            mocks.getExamsCachedState,
            mocks.deleteExam
        )
    }

    @Test
    fun `Load intent updates ui state with exam details`() = runTest {
        scenario
            .givenAnExamsCachedState()
            .givenALoadedExamDetailState()

        sut.sendIntent(ExamDetailIntent.Load(EXAM_ID))

        sut.uiState.value.let { state ->
            assertEquals(
                ComponentState.Success,
                state.componentState
            )
            assertEquals(
                factory.makeExam(),
                state.exam
            )
            assertEquals(
                factory.makeQuestionsState(),
                state.questionsUiState.questionsState
            )
        }
    }

    @Test
    fun `ChangeDeletionDialogVisibility shows dialog`() = runTest {
        sut.sendIntent(ExamDetailIntent.ChangeDeletionDialogVisibility(true))

        assertEquals(
            true,
            sut.uiState.value.examDeletionDialogState.visible
        )
    }

    @Test
    fun `ChangeDeletionDialogVisibility hides dialog`() = runTest {
        sut.sendIntent(ExamDetailIntent.ChangeDeletionDialogVisibility(false))

        assertEquals(
            false,
            sut.uiState.value.examDeletionDialogState.visible
        )
    }

    @Test
    fun `RemoveExam intent removes exam successfully and navigates back`() = runTest {
        scenario.givenASuccessfulExamDeletion()

        sut.sendIntent(ExamDetailIntent.RemoveExam(factory.makeExam()))

        sut.uiState.value.let { state ->
            verify(mocks.deleteExam).invoke(any())
            assertFalse(state.examDeletionDialogState.visible)
            assertTrue(state.navigateBack)
        }
    }

    @Test
    fun `RemoveExam intent fails to remove exam and shows error`() = runTest {
        scenario.givenAFailedExamDeletion()

        sut.sendIntent(ExamDetailIntent.RemoveExam(factory.makeExam()))

        sut.uiState.value.let { state ->
            verify(mocks.deleteExam).invoke(any())
            assertFalse(state.examDeletionDialogState.visible)
            assertNotNull(state.error)
            assertFalse(state.navigateBack)
        }
    }

    @Test
    fun `ClearError intent removes error from ui state`() = runTest {
        sut.sendIntent(ExamDetailIntent.ClearError)

        Assert.assertNull(
            sut.uiState.value.error
        )
    }
}
