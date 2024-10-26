package com.jmp.examsfeature.presentation.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmp.common.ui.model.ComponentState
import com.jmp.common.usecase.exams.GetExamsState
import com.jmp.commons.utils.types.doOnError
import com.jmp.commons.utils.types.doOnSuccess
import com.jmp.examsfeature.domain.quotes.interactor.GetQuotes
import com.jmp.examsfeature.presentation.list.state.ExamsState
import com.jmp.examsfeature.presentation.list.uiprovider.ExamsUiProvider
import com.jmp.examsfeature.presentation.list.viewmodel.ExamsIntent.ChangeFilterText
import com.jmp.examsfeature.presentation.list.viewmodel.ExamsIntent.FetchQuotes
import com.jmp.examsfeature.presentation.list.viewmodel.ExamsIntent.Load
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamsListViewModel @Inject constructor(
    private val getQuotes: GetQuotes,
    private val getExamsCachedState: GetExamsState,
    examsUiProvider: ExamsUiProvider
) : ViewModel() {

    private val _uiState: MutableStateFlow<ExamsState> = MutableStateFlow(examsUiProvider.provide())
    val uiState: StateFlow<ExamsState> = _uiState

    private fun updateUi(newState: ExamsState) {
        _uiState.value = newState
    }

    fun sendIntent(intent: ExamsIntent) {
        when (intent) {
            is Load -> load()
            is ChangeFilterText -> changeFilterText(intent.filter)
            is FetchQuotes -> fetchQuotes()
        }
    }

    private fun load() {
        retrieveExams()
        fetchQuotes()
    }

    private fun changeFilterText(filter: String) {
        updateUi(
            _uiState.value.copy(
                toolbarState = _uiState.value.toolbarState.copy(
                    filterState = _uiState.value.toolbarState.filterState.copy(
                        value = filter
                    )
                )
            )
        )
    }

    private fun retrieveExams() {
        viewModelScope.launch {
            getExamsCachedState().collect { exams ->
                updateUi(
                    _uiState.value.copy(
                        exams = exams,
                        examsComponentState = ComponentState.Success
                    ),
                )
            }
        }
    }

    private fun fetchQuotes() {
        viewModelScope.launch {
            updateUi(
                _uiState.value.copy(
                    toolbarState = _uiState.value.toolbarState.copy(
                        componentState = ComponentState.Initialising
                    )
                )
            )
            getQuotes()
                .doOnSuccess { list ->
                    updateUi(
                        _uiState.value.copy(
                            toolbarState = _uiState.value.toolbarState.copy(
                                quotes = list.map { it },
                                componentState = ComponentState.Success
                            )
                        )
                    )
                }
                .doOnError {
                    updateUi(
                        _uiState.value.copy(
                            toolbarState = _uiState.value.toolbarState.copy(
                                componentState = ComponentState.Error(it)
                            )
                        )
                    )
                }
        }
    }
}
