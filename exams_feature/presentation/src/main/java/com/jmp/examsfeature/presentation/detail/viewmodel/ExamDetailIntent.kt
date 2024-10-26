package com.jmp.examsfeature.presentation.detail.viewmodel

import com.jmp.commons.utils.model.Exam

sealed class ExamDetailIntent {

    data class Load(val id: Long) : ExamDetailIntent()

    data class ChangeDeletionDialogVisibility(val visible: Boolean) : ExamDetailIntent()

    data class RemoveExam(val exam: Exam) : ExamDetailIntent()

    data object ClearError : ExamDetailIntent()
}
