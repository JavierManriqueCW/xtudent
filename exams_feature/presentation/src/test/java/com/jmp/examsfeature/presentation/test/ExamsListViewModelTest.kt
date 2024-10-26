package com.jmp.examsfeature.presentation.test

import com.jmp.commons.utils.test.UnitTest
import com.jmp.examsfeature.presentation.factory.MocksProvider
import com.jmp.examsfeature.presentation.list.viewmodel.ExamsIntent
import com.jmp.examsfeature.presentation.list.viewmodel.ExamsListViewModel
import com.jmp.examsfeature.presentation.scenario.ExamsListViewModelScenario
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ExamsListViewModelTest : UnitTest() {

    private val mocks: MocksProvider = MocksProvider()
    private val scenario: ExamsListViewModelScenario = ExamsListViewModelScenario(mocks)
    private lateinit var sut: ExamsListViewModel

    @Before
    fun setUp() {
        scenario.givenAnInitialExamsState()
        sut = ExamsListViewModel(
            mocks.getQuotes,
            mocks.getExamsCachedState,
            mocks.examsUiProvider
        )
    }

    @Test
    fun `Load intent triggers exams retrieval and quote fetching`() = runTest {
        scenario.givenExamsRetrievedSuccessfully()
        scenario.givenQuotesFetchedSuccessfully()

        sut.sendIntent(ExamsIntent.Load)

        scenario.thenExamsAndQuotesAreLoaded()
    }

    @Test
    fun `ChangeFilterText intent updates the filter text correctly`() = runTest {
        val filterText = "new filter text"

        sut.sendIntent(ExamsIntent.ChangeFilterText(filterText))

        scenario.thenFilterTextIsUpdated(sut.uiState.value, filterText)
    }

    @Test
    fun `FetchQuotes intent retrieves quotes correctly`() = runTest {
        scenario.givenQuotesFetchedSuccessfully()

        sut.sendIntent(ExamsIntent.FetchQuotes)

        scenario.thenQuotesAreFetched(sut.uiState.value)
    }

    @Test
    fun `FetchQuotes intent fails to retrieve quotes`() = runTest {
        scenario.givenQuotesFetchFailed()

        sut.sendIntent(ExamsIntent.FetchQuotes)

        scenario.thenQuotesFetchFailed(sut.uiState.value)
    }
}
