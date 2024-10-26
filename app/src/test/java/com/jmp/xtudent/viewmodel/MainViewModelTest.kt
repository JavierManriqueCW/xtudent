package com.jmp.xtudent.viewmodel

import com.jmp.commons.utils.test.UnitTest
import com.jmp.xtudent.factory.MocksProvider
import com.jmp.xtudent.scenario.MainViewModelScenario
import com.jmp.xtudent.scenario.MainViewModelScenario.Companion.CREATION_PAGE_INDEX
import com.jmp.xtudent.scenario.MainViewModelScenario.Companion.HOME_PAGE_INDEX
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest : UnitTest() {

    private val mocks: MocksProvider = MocksProvider()
    private val scenario: MainViewModelScenario = MainViewModelScenario(mocks)
    private lateinit var sut: MainViewModel

    @Before
    fun setUp() {
        scenario.givenAnInitialMainState()
        sut = MainViewModel(
            mocks.fetchExams,
            mocks.getExamsCachedState,
            mocks.mainUiProvider
        )
    }

    @Test
    fun `Load intent loaded initial data successfully`() = runTest {
        scenario.givenAnInitialDataLoadingSucceeded()

        sut.sendIntent(MainActivityIntent.Load)

        scenario.thenLoadIntentSucceeded(sut.uiState.value)
    }

    @Test
    fun `Load intent failed loading data as expected`() = runTest {
        scenario.givenAnInitialDataLoadingFailed()

        sut.sendIntent(MainActivityIntent.Load)

        scenario.thenLoadIntentFailed(sut.uiState.value)
    }

    @Test
    fun `ChangePage intent to creation page updates bottom bar icon sizes correctly`() = runTest {
        scenario.givenAMainStateWithSeveralExams()

        sut.sendIntent(MainActivityIntent.ChangePage(CREATION_PAGE_INDEX))

        scenario.thenBottomBarIconSizesAreCorrect(sut.uiState.value, CREATION_PAGE_INDEX)
    }

    @Test
    fun `ChangePage intent to home page updates bottom bar icon sizes correctly`() = runTest {
        scenario.givenAMainStateWithSeveralExams()

        sut.sendIntent(MainActivityIntent.ChangePage(HOME_PAGE_INDEX))

        scenario.thenBottomBarIconSizesAreCorrect(sut.uiState.value, HOME_PAGE_INDEX)
    }
}
