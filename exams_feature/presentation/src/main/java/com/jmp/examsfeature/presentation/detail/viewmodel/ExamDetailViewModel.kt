package com.jmp.examsfeature.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmp.common.ui.model.ComponentState
import com.jmp.common.usecase.exams.GetExamsState
import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.types.doOnError
import com.jmp.commons.utils.types.doOnSuccess
import com.jmp.examsfeature.domain.exams.interactor.DeleteExam
import com.jmp.examsfeature.presentation.detail.state.ExamDetailState
import com.jmp.examsfeature.presentation.detail.uiprovider.ExamDetailUiProvider
import com.jmp.examsfeature.presentation.detail.viewmodel.ExamDetailIntent.ChangeDeletionDialogVisibility
import com.jmp.examsfeature.presentation.detail.viewmodel.ExamDetailIntent.ClearError
import com.jmp.examsfeature.presentation.detail.viewmodel.ExamDetailIntent.Load
import com.jmp.examsfeature.presentation.detail.viewmodel.ExamDetailIntent.RemoveExam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamDetailViewModel @Inject constructor(
    private val examDetailUiProvider: ExamDetailUiProvider,
    private val getExamsCachedState: GetExamsState,
    private val deleteExam: DeleteExam
) : ViewModel() {

    private val _uiState: MutableStateFlow<ExamDetailState> = MutableStateFlow(
        examDetailUiProvider.provide()
    )
    val uiState: StateFlow<ExamDetailState> = _uiState

    private fun updateUiState(newState: ExamDetailState) {
        _uiState.value = newState
    }

    fun sendIntent(intent: ExamDetailIntent) {
        when (intent) {
            is Load -> load(intent.id)
            is ChangeDeletionDialogVisibility -> changeDeletionDialogVisibility(intent.visible)
            is RemoveExam -> removeExam(intent.exam)
            is ClearError -> clearError()
        }
    }

    private fun load(examId: Long) {
        updateUiState(_uiState.value.copy(componentState = ComponentState.Initialising))
        viewModelScope.launch {
            getExamsCachedState().collect { exams ->
                exams.find { exam -> exam.id == examId }?.also { exam ->
                    updateUiState(
                        examDetailUiProvider.provide(
                            componentState = ComponentState.Success,
                            exam = exam,
                            questions = exam.examQuestions
                        )
                    )
                }
            }
        }
    }

    private fun changeDeletionDialogVisibility(visible: Boolean) {
        updateUiState(
            _uiState.value.copy(
                examDeletionDialogState = _uiState.value.examDeletionDialogState.copy(
                    visible = visible
                )
            )
        )
    }

    private fun removeExam(exam: Exam) {
        changeDeletionDialogVisibility(false)
        updateUiState(_uiState.value.copy(loading = true))
        viewModelScope.launch {
            deleteExam(exam)
                .doOnSuccess {
                    updateUiState(
                        _uiState.value.copy(
                            loading = false,
                            navigateBack = true
                        )
                    )
                }
                .doOnError {
                    updateUiState(
                        _uiState.value.copy(
                            loading = false,
                            error = it.message
                        )
                    )
                }
        }
    }

    private fun clearError() {
        updateUiState(_uiState.value.copy(error = null))
    }
}
