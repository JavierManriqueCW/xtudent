package com.jmp.examsfeature.presentation.detail.state

import com.jmp.common.ui.model.ComponentState
import com.jmp.commons.utils.model.Exam

data class ExamDetailState(
    val loading: Boolean = true,
    val error: Int? = null,
    val image: String = String(),
    val name: String = String(),
    val description: String = String(),
    val exam: Exam = Exam(),
    val componentState: ComponentState,
    val questionsUiState: ExamQuestionUiState,
    val examDeletionDialogState: ExamDeletionDialogState,
    val navigateBack: Boolean = false
)
