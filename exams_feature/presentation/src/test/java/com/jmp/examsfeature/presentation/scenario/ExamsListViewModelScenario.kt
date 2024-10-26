package com.jmp.examsfeature.presentation.scenario

import com.jmp.common.ui.model.ComponentState
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.examsfeature.presentation.factory.ExamsListViewModelFactory
import com.jmp.examsfeature.presentation.factory.MocksProvider
import com.jmp.examsfeature.presentation.list.state.ExamsState
import kotlinx.coroutines.flow.flowOf
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ExamsListViewModelScenario(
    private val mocks: MocksProvider
) {

    private val factory: ExamsListViewModelFactory = ExamsListViewModelFactory()

    fun givenAnInitialExamsState() {
        whenever(mocks.examsUiProvider.provide())
            .thenReturn(factory.makeInitialExamsState())
    }

    suspend fun givenExamsRetrievedSuccessfully() {
        whenever(mocks.getExamsCachedState.invoke()).thenReturn(flowOf())
    }

    suspend fun givenQuotesFetchedSuccessfully() {
        whenever(mocks.getQuotes.invoke()).thenReturn(
            Either.Success(factory.makeQuotesList())
        )
    }

    suspend fun givenQuotesFetchFailed() {
        whenever(mocks.getQuotes.invoke()).thenReturn(
            Either.Error(Failure.UnknownError())
        )
    }

    suspend fun thenExamsAndQuotesAreLoaded() {
        verify(mocks.getExamsCachedState).invoke()
        verify(mocks.getQuotes).invoke()
    }

    fun thenFilterTextIsUpdated(
        state: ExamsState,
        filterText: String
    ) {
        assert(state.toolbarState.filterState.value == filterText)
    }

    fun thenQuotesAreFetched(state: ExamsState) {
        assert(state.toolbarState.quotes.isNotEmpty())
    }

    fun thenQuotesFetchFailed(state: ExamsState) {
        assert(state.toolbarState.componentState is ComponentState.Error)
    }
}
