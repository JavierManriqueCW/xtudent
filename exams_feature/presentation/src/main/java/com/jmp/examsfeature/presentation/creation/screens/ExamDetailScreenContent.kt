package com.jmp.examsfeature.presentation.creation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.jmp.common.ui.compose.clearFocusOnTap
import com.jmp.common.ui.compose.components.LoadingIndicator
import com.jmp.common.ui.theme.DialogContainerColor
import com.jmp.common.ui.theme.LightRed
import com.jmp.common.ui.theme.MediumBodyTextSize
import com.jmp.common.ui.theme.Shapes
import com.jmp.common.ui.theme.SmallGeneralPadding
import com.jmp.examsfeature.presentation.detail.components.CustomTopAppBar
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenContent
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenTestTags.EXAM_DELETION_DIALOG_CANCEL_BUTTON
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenTestTags.EXAM_DELETION_DIALOG_CONFIRM_BUTTON
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenTestTags.EXAM_DETAIL_DELETION_DIALOG
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenTestTags.EXAM_DETAIL_SCREEN
import com.jmp.examsfeature.presentation.detail.state.ExamDetailState
import com.jmp.examsfeature.presentation.detail.viewmodel.ExamDetailIntent.ChangeDeletionDialogVisibility
import com.jmp.examsfeature.presentation.detail.viewmodel.ExamDetailIntent.ClearError
import com.jmp.examsfeature.presentation.detail.viewmodel.ExamDetailIntent.RemoveExam
import com.jmp.examsfeature.presentation.detail.viewmodel.ExamDetailViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamDetailScreenContent(
    viewModel: ExamDetailViewModel,
    mainNavigationController: NavHostController,
    uiState: ExamDetailState
) {
    val scope = rememberCoroutineScope()
    val snackbarHost = rememberBottomSheetScaffoldState().snackbarHostState

    if (uiState.navigateBack) {
        LaunchedEffect(uiState) {
            mainNavigationController.popBackStack()
        }
    }

    uiState.error?.let { errorResource ->
        stringResource(errorResource).also {
            LaunchedEffect(Unit) {
                scope.launch {
                    snackbarHost.showSnackbar(it)
                    viewModel.sendIntent(ClearError)
                }
            }
        }
    }

    AnimatedVisibility(
        visible = uiState.examDeletionDialogState.visible,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(EXAM_DETAIL_DELETION_DIALOG),
            onDismissRequest = { viewModel.sendIntent(ChangeDeletionDialogVisibility(false)) },
            containerColor = DialogContainerColor,
            textContentColor = Color.White,
            shape = Shapes.large,
            title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(uiState.examDeletionDialogState.title),
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Text(
                    text = stringResource(uiState.examDeletionDialogState.body),
                    fontWeight = FontWeight.Light,
                    fontSize = MediumBodyTextSize,
                    color = Color.LightGray
                )
            },
            confirmButton = {
                TextButton(
                    modifier = Modifier.testTag(EXAM_DELETION_DIALOG_CONFIRM_BUTTON),
                    onClick = { viewModel.sendIntent(RemoveExam(uiState.exam)) },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(uiState.examDeletionDialogState.positiveAction),
                        color = LightRed,
                        textAlign = TextAlign.Center
                    )
                }
            },
            dismissButton = {
                TextButton(
                    modifier = Modifier.testTag(EXAM_DELETION_DIALOG_CANCEL_BUTTON),
                    onClick = { viewModel.sendIntent(ChangeDeletionDialogVisibility(false)) },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(uiState.examDeletionDialogState.negativeAction),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        )
    }

    ExamDetailScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .clearFocusOnTap()
            .testTag(EXAM_DETAIL_SCREEN),
        topBar = {
            CustomTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = SmallGeneralPadding),
                onBackTapped = { mainNavigationController.popBackStack() },
                onDeleteTapped = { viewModel.sendIntent(ChangeDeletionDialogVisibility(true)) }
            )
        },
        snackbarHost = snackbarHost,
        uiState = uiState.questionsUiState
    )

    AnimatedVisibility(
        visible = uiState.loading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        LoadingIndicator(modifier = Modifier.fillMaxSize())
    }
}
