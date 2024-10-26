package com.jmp.examsfeature.presentation.detail.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jmp.common.ui.compose.components.QuestionRow
import com.jmp.common.ui.compose.components.SnackbarHost
import com.jmp.common.ui.compose.fadingEdge
import com.jmp.common.ui.theme.HugeGeneralPadding
import com.jmp.common.ui.theme.QuestionEditingBottomPadding
import com.jmp.examsfeature.presentation.detail.state.ExamQuestionUiState

@Composable
fun ExamDetailScreenContent(
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit)? = null,
    uiState: ExamQuestionUiState,
    snackbarHost: SnackbarHostState,
) {
    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent,
        snackbarHost = { SnackbarHost(snackbarHost) },
        topBar = { topBar?.invoke() }
    ) { padding ->
        Box(
            modifier = Modifier
                .fadingEdge(
                    topStartFadingPoint = uiState.topStartFadingPoint,
                    bottomStartFadingPoint = uiState.bottomStartFadingPoint
                )
                .verticalScroll(rememberScrollState())
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = HugeGeneralPadding)
            ) {
                uiState.questionsState.forEachIndexed { index, examQuestion ->
                    QuestionRow(
                        modifier = Modifier.padding(
                            bottom = HugeGeneralPadding
                        ),
                        questionNumber = index + 1,
                        questionState = examQuestion.question,
                        correctAnswerState = examQuestion.correctAnswer,
                        wrongAnswerState1 = examQuestion.firstWrongAlternative,
                        wrongAnswerState2 = examQuestion.secondWrongAlternative,
                        wrongAnswerState3 = examQuestion.thirdWrongAlternative,
                    )
                }

                Spacer(modifier = Modifier.height(QuestionEditingBottomPadding))
            }
        }
    }
}
