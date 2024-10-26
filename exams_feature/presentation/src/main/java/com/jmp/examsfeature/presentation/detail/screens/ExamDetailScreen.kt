package com.jmp.examsfeature.presentation.detail.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.jmp.examsfeature.presentation.creation.screens.ExamDetailScreenContent
import com.jmp.examsfeature.presentation.detail.viewmodel.ExamDetailIntent.Load
import com.jmp.examsfeature.presentation.detail.viewmodel.ExamDetailViewModel

@Composable
fun ExamDetailScreen(
    viewModel: ExamDetailViewModel,
    mainNavigationController: NavHostController,
    examId: Long
) {
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.sendIntent(Load(examId))
    }

    ExamDetailScreenContent(
        viewModel = viewModel,
        mainNavigationController = mainNavigationController,
        uiState = uiState
    )
}

object ExamDetailScreenTestTags {
    const val EXAM_DETAIL_SCREEN = "exam_detail_screen"
    const val EXAM_DETAIL_DELETE_BUTTON = "exam_detail_delete_button"
    const val EXAM_DETAIL_BACK_BUTTON = "exam_detail_back_button"
    const val EXAM_DETAIL_DELETION_DIALOG = "exam_detail_deletion_dialog"
    const val EXAM_DELETION_DIALOG_CONFIRM_BUTTON = "exam_deletion_dialog_confirm_button"
    const val EXAM_DELETION_DIALOG_CANCEL_BUTTON = "exam_deletion_dialog_cancel_button"
}
