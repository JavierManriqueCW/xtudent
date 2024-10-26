package com.jmp.xtudent.scenario

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jmp.common.ui.model.ComponentState
import com.jmp.commons.utils.test.TestUtils
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.xtudent.factory.MainViewModelFactory.makeExamList
import com.jmp.xtudent.factory.MainViewModelFactory.makeInitialMainState
import com.jmp.xtudent.factory.MainViewModelFactory.makeLoadedMainState
import com.jmp.xtudent.factory.MocksProvider
import com.jmp.xtudent.state.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MainViewModelScenario(
    private val mocks: MocksProvider
) {

    fun givenAnInitialMainState() {
        whenever(mocks.mainUiProvider.provide())
            .thenReturn(makeInitialMainState())
    }

    fun givenAMainStateWithSeveralExams(): MainViewModelScenario = apply {
        whenever(mocks.mainUiProvider.provide())
            .thenReturn(makeLoadedMainState())
    }

    suspend fun givenAnInitialDataLoadingSucceeded() {
        whenever(mocks.fetchExams.invoke())
            .thenReturn(Either.Success(Unit))
        whenever(mocks.getExamsCachedState.invoke())
            .thenReturn(MutableStateFlow(makeExamList()))
    }

    suspend fun givenAnInitialDataLoadingFailed() {
        whenever(mocks.fetchExams.invoke())
            .thenReturn(Either.Error(Failure.CouldNotGetExamsFromDatabase))
        whenever(mocks.getExamsCachedState.invoke())
            .thenReturn(MutableStateFlow(listOf()))
    }

    suspend fun thenLoadIntentSucceeded(state: MainState) {
        state.apply {
            TestUtils.waitUntil { componentState is ComponentState.Success }
            Assert.assertTrue(hasExams)
        }
        verify(mocks.fetchExams).invoke()
        verify(mocks.getExamsCachedState).invoke()
    }

    suspend fun thenLoadIntentFailed(state: MainState) {
        state.apply {
            TestUtils.waitUntil { componentState is ComponentState.Error }
            Assert.assertFalse(hasExams)
        }
        verify(mocks.fetchExams).invoke()
    }

    fun thenBottomBarIconSizesAreCorrect(
        state: MainState,
        page: Int
    ) {
        assert(state.homeButtonSize == getHomeButtonSize(page))
        assert(state.creationButtonSize == getCreationButton(page))
    }

    private fun getHomeButtonSize(page: Int): Dp =
        when (page) {
            HOME_PAGE_INDEX -> BIG_ICON_SIZE
            else -> SMALL_ICON_SIZE
        }

    private fun getCreationButton(page: Int): Dp =
        when (page) {
            CREATION_PAGE_INDEX -> BIG_ICON_SIZE
            else -> SMALL_ICON_SIZE
        }

    companion object {
        private val SMALL_ICON_SIZE = 18.dp
        private val BIG_ICON_SIZE = 28.dp
        const val HOME_PAGE_INDEX = 0
        const val CREATION_PAGE_INDEX = 1
    }
}
