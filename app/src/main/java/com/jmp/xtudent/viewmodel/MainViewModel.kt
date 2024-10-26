package com.jmp.xtudent.viewmodel

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmp.common.ui.model.ComponentState
import com.jmp.common.usecase.exams.FetchExams
import com.jmp.common.usecase.exams.GetExamsState
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.xtudent.state.MainState
import com.jmp.xtudent.uiprovider.MainUiProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchExams: FetchExams,
    private val getExamsCachedState: GetExamsState,
    mainUiProvider: MainUiProvider
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainState> = MutableStateFlow(mainUiProvider.provide())
    val uiState: StateFlow<MainState> = _uiState

    private fun updateUiState(state: MainState) {
        _uiState.value = state
    }

    fun sendIntent(intent: MainActivityIntent) {
        when (intent) {
            is MainActivityIntent.Load -> load()
            is MainActivityIntent.ChangePage -> changePage(intent.page)
        }
    }

    private fun load() {
        updateUiState(_uiState.value.copy(loading = true))

        viewModelScope.launch {
            when (val result = fetchExams()) {
                is Either.Success -> handleFetchExamsSuccess()
                is Either.Error -> handleFetchExamsErrorState(result.failure)
            }
        }
    }

    private suspend fun handleFetchExamsSuccess() {
        getExamsCachedState().collect {
            updateUiState(
                _uiState.value.copy(
                    componentState = ComponentState.Success,
                    loading = false,
                    hasExams = true
                )
            )
        }
    }

    private fun handleFetchExamsErrorState(failure: Failure) {
        updateUiState(
            _uiState.value.copy(
                componentState = ComponentState.Error(failure),
                loading = false,
            )
        )
    }

    private fun changePage(page: Int) {
        updateUiState(
            _uiState.value.copy(
                homeButtonSize = getHomeButtonSize(page),
                creationButtonSize = getCreationButton(page)
            )
        )
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
        private const val HOME_PAGE_INDEX = 0
        private const val CREATION_PAGE_INDEX = 1
        private val SMALL_ICON_SIZE = 18.dp
        private val BIG_ICON_SIZE = 28.dp
    }
}
