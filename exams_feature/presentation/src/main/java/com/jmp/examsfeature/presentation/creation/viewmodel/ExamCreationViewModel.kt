package com.jmp.examsfeature.presentation.creation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.types.doOnError
import com.jmp.commons.utils.types.doOnSuccess
import com.jmp.examsfeature.domain.exams.interactor.SaveExam
import com.jmp.examsfeature.domain.xlsx.interactor.GetExamFromXlsxFile
import com.jmp.examsfeature.presentation.creation.intent.ExamCreationIntent
import com.jmp.examsfeature.presentation.creation.state.ExamCreationState
import com.jmp.examsfeature.presentation.creation.uiprovider.ExamCreationUiProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamCreationViewModel @Inject constructor(
    private val getExamFromXlsxFile: GetExamFromXlsxFile,
    private val saveExam: SaveExam,
    examCreationUiProvider: ExamCreationUiProvider,
) : ViewModel() {

    private val _uiState: MutableStateFlow<ExamCreationState> = MutableStateFlow(
        examCreationUiProvider.provide()
    )
    val uiState: StateFlow<ExamCreationState> = _uiState

    private fun updateUiState(newState: ExamCreationState) {
        _uiState.value = newState
    }

    fun sendIntent(intent: ExamCreationIntent) {
        when (intent) {
            is ExamCreationIntent.ImportExam -> importExam(intent.getExamFileUri)
            is ExamCreationIntent.Clear -> clear()
        }
    }

    private fun importExam(getExamFileUri: suspend () -> Uri?) {
        viewModelScope.launch {
            getExamFileUri()?.let { uri ->
                getExamFromXlsxFile(uri)
                    .doOnSuccess { saveExam(it)}
                    .doOnError {
                        updateUiState(
                            _uiState.value.copy(
                                error = it.message
                            )
                        )
                    }
            }
        }
    }

    private fun saveExam(exam: Exam) {
        viewModelScope.launch {
            saveExam.invoke(exam)
                .doOnSuccess {
                    updateUiState(
                        _uiState.value.copy(
                            navigateBack = true
                        )
                    )
                }
        }
    }

    private fun clear() {
        updateUiState(
            _uiState.value.copy(
                error = null,
                navigateBack = false
            )
        )
    }
}
