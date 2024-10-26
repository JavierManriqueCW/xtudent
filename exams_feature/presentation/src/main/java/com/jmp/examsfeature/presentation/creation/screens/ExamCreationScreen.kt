package com.jmp.examsfeature.presentation.creation.screens

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jmp.common.ui.compose.fadingEdge
import com.jmp.common.ui.theme.BottomExamCreationContentPadding
import com.jmp.common.ui.theme.GeneralPadding
import com.jmp.common.ui.theme.ImportButtonColor
import com.jmp.common.ui.theme.SmallGeneralPadding
import com.jmp.examsfeature.presentation.R
import com.jmp.examsfeature.presentation.creation.components.StepIndication
import com.jmp.examsfeature.presentation.creation.intent.ExamCreationIntent
import com.jmp.examsfeature.presentation.creation.screens.ExamCreationScreenTestTags.EXAM_CREATION_BUTTON
import com.jmp.examsfeature.presentation.creation.viewmodel.ExamCreationViewModel
import kotlinx.coroutines.launch

@Composable
fun ExamCreationScreen(
    modifier: Modifier = Modifier,
    viewModel: ExamCreationViewModel,
    snackbarHost: SnackbarHostState,
    getExamFileUri: suspend () -> Uri?,
    navigateBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val uiState = viewModel.uiState.collectAsState()
    val error = uiState.value.error?.let { stringResource(it) }

    LaunchedEffect(uiState.value) {
        error?.let {
            scope.launch {
                viewModel.sendIntent(ExamCreationIntent.Clear)
                snackbarHost.showSnackbar(error)
            }
        }

        if (uiState.value.navigateBack) {
            viewModel.sendIntent(ExamCreationIntent.Clear)
            navigateBack()
        }
    }

    ConstraintLayout(modifier) {
        val (content, button) = createRefs()

        Column(
            modifier = Modifier
                .padding(horizontal = GeneralPadding)
                .fillMaxWidth()
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .fadingEdge(
                    topStartFadingPoint = uiState.value.topStartFadingPoint,
                    bottomStartFadingPoint = uiState.value.bottomStartFadingPoint
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                StepIndication(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    step = stringResource(uiState.value.firstIndicator),
                    content = stringResource(uiState.value.firstIndication)
                )

                Spacer(modifier = Modifier.height(SmallGeneralPadding))

                StepIndication(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    step = stringResource(uiState.value.secondIndicator),
                    content = stringResource(uiState.value.secondIndication)
                )

                Spacer(modifier = Modifier.height(SmallGeneralPadding))

                StepIndication(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    step = stringResource(uiState.value.thirdIndicator),
                    content = stringResource(uiState.value.thirdIndication)
                )

                Spacer(modifier = Modifier.height(BottomExamCreationContentPadding))
            }
        }

        IconButton(
            modifier = Modifier
                .constrainAs(button) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .padding(bottom = GeneralPadding)
                .size(56.dp)
                .testTag(EXAM_CREATION_BUTTON),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = ImportButtonColor,
                contentColor = Color.White
            ),
            onClick = {
                viewModel.sendIntent(
                    ExamCreationIntent.ImportExam(getExamFileUri)
                )
            }
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_import),
                tint = Color.White,
                contentDescription = null
            )
        }
    }
}

object ExamCreationScreenTestTags {
    const val EXAM_CREATION_SCREEN = "exam_creation_screen"
    const val EXAM_CREATION_BUTTON = "exam_creation_button"
}
