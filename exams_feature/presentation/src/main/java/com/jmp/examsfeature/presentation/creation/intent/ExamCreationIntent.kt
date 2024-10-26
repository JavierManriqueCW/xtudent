package com.jmp.examsfeature.presentation.creation.intent

import android.net.Uri

sealed class ExamCreationIntent {

    data class ImportExam(val getExamFileUri: suspend () -> Uri?) : ExamCreationIntent()

    data object Clear : ExamCreationIntent()
}
