package com.jmp.examsfeature.presentation.detail.state

data class ExamDeletionDialogState(
    val visible: Boolean = false,
    val title: Int,
    val body: Int,
    val positiveAction: Int,
    val negativeAction: Int
)
