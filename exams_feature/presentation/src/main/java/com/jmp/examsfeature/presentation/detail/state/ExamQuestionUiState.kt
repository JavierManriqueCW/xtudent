package com.jmp.examsfeature.presentation.detail.state

data class ExamQuestionUiState(
    val topStartFadingPoint: Float,
    val bottomStartFadingPoint: Float,
    val questionsState: List<ExamQuestionState>
) {
    companion object
}
