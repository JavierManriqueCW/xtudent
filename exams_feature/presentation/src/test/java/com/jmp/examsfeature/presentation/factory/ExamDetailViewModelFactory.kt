package com.jmp.examsfeature.presentation.factory

import com.jmp.common.ui.model.ComponentState
import com.jmp.examsfeature.presentation.detail.state.ExamDeletionDialogState
import com.jmp.examsfeature.presentation.detail.state.ExamDetailState
import com.jmp.examsfeature.presentation.detail.state.ExamQuestionState
import com.jmp.examsfeature.presentation.detail.state.ExamQuestionUiState

class ExamDetailViewModelFactory : ViewModelFactory() {

    fun makeInitialExamDetailState(): ExamDetailState =
        ExamDetailState(
            exam = makeExam(),
            questionsUiState = makeQuestionsUiState(),
            componentState = ComponentState.Initialising,
            examDeletionDialogState = makeExamDeletionDialogState()
        )

    private fun makeQuestionsUiState(): ExamQuestionUiState =
        ExamQuestionUiState(
            topStartFadingPoint = 0f,
            bottomStartFadingPoint = 0f,
            questionsState = makeQuestionsState()
        )

    fun makeQuestionsState(): List<ExamQuestionState> =
        listOf(makeQuestionState())

    private fun makeQuestionState(): ExamQuestionState =
        ExamQuestionState(
            id = 0,
            question = "",
            correctAnswer = "",
            firstWrongAlternative = "",
            secondWrongAlternative = "",
            thirdWrongAlternative = ""
        )

    private fun makeExamDeletionDialogState(): ExamDeletionDialogState =
        ExamDeletionDialogState(
            title = 0,
            body = 0,
            positiveAction = 0,
            negativeAction = 0,
            visible = false
        )

    fun makeLoadedExamDetailState(): ExamDetailState =
        ExamDetailState(
            exam = makeExam(),
            questionsUiState = makeQuestionsUiState(),
            componentState = ComponentState.Success,
            examDeletionDialogState = makeExamDeletionDialogState()
        )
}
