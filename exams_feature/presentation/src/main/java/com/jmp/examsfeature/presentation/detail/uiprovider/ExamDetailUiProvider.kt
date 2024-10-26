package com.jmp.examsfeature.presentation.detail.uiprovider

import com.jmp.common.ui.model.ComponentState
import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.model.ExamQuestion
import com.jmp.examsfeature.presentation.R
import com.jmp.examsfeature.presentation.detail.state.ExamDeletionDialogState
import com.jmp.examsfeature.presentation.detail.state.ExamDetailState
import com.jmp.examsfeature.presentation.detail.state.ExamQuestionState
import com.jmp.examsfeature.presentation.detail.state.ExamQuestionUiState
import javax.inject.Inject

open class ExamDetailUiProvider @Inject constructor() {

    fun provide(): ExamDetailState =
        ExamDetailState(
            componentState = ComponentState.Initialising,
            questionsUiState = ExamQuestionUiState.EMPTY,
            examDeletionDialogState = provideExamDeletionDialogState()
        )

    fun provide(
        componentState: ComponentState,
        exam: Exam,
        questions: List<ExamQuestion>
    ): ExamDetailState =
        ExamDetailState(
            componentState = componentState,
            loading = false,
            image = exam.image,
            name = exam.name,
            description = exam.description,
            exam = exam,
            questionsUiState = provideExamQuestionsUiState(questions),
            examDeletionDialogState = provideExamDeletionDialogState()
        )

    private fun provideExamDeletionDialogState(): ExamDeletionDialogState =
        ExamDeletionDialogState(
            title = R.string.exam_detail_deletion_dialog_title,
            body = R.string.exam_detail_deletion_dialog_body,
            positiveAction = R.string.removal_dialog_positive_response,
            negativeAction = R.string.removal_dialog_negative_response
        )

    private fun provideExamQuestionsUiState(
        list: List<ExamQuestion>
    ): ExamQuestionUiState =
        ExamQuestionUiState(
            topStartFadingPoint = TOP_START_FADING_POINT,
            bottomStartFadingPoint = BOTTOM_START_FADING_POINT,
            questionsState =
            list.map { question ->
                ExamQuestionState(
                    id = question.id,
                    question = question.question,
                    correctAnswer = question.rightAnswer,
                    firstWrongAlternative = question.wrongAnswers[0],
                    secondWrongAlternative = question.wrongAnswers[1],
                    thirdWrongAlternative = question.wrongAnswers[2],
                )
            }
        )

    companion object {
        private const val TOP_START_FADING_POINT = 0.15f
        private const val BOTTOM_START_FADING_POINT = 0.8f

        private val ExamQuestionUiState.Companion.EMPTY
            get() = ExamQuestionUiState(
                topStartFadingPoint = TOP_START_FADING_POINT,
                bottomStartFadingPoint = BOTTOM_START_FADING_POINT,
                questionsState = emptyList()
            )
    }
}
