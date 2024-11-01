package com.jmp.examsfeature.presentation.list.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jmp.common.ui.compose.components.DisposableEffectWithLifecycle
import com.jmp.common.ui.navigation.Screen
import com.jmp.examsfeature.presentation.list.viewmodel.ExamsIntent.ChangeFilterText
import com.jmp.examsfeature.presentation.list.viewmodel.ExamsIntent.FetchQuotes
import com.jmp.examsfeature.presentation.list.viewmodel.ExamsIntent.Load
import com.jmp.examsfeature.presentation.list.viewmodel.ExamsListViewModel

@Composable
fun ExamsScreen(
    modifier: Modifier = Modifier,
    viewModel: ExamsListViewModel,
    mainNavigationController: NavHostController
) {
    val uiState = viewModel.uiState.collectAsState()

    DisposableEffectWithLifecycle(
        onStart = { viewModel.sendIntent(Load) }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.value.exams.isNotEmpty()) {
            ExamsScreenContent(
                modifier = modifier,
                uiState = uiState.value,
                onFilterTextChanged = { viewModel.sendIntent(ChangeFilterText(it)) },
                fetchQuotes = { viewModel.sendIntent(FetchQuotes) },
                onExamTapped = { mainNavigationController.navigate("${Screen.ExamDetail}/$it") },
            )
        } else {
          EmptyExamsScreen(modifier = modifier)
        }
    }
}

object ExamsScreenTestTags {
    const val EXAMS_SCREEN = "exams_screen"
    const val ERROR_MAIN_SCREEN = "error_main_screen"
    const val EXAMS_LIST = "exams_list"
    const val EMPTY_EXAMS_SCREEN_LOTTIE = "empty_exams_screen_lottie"
    const val QUOTES_CAROUSEL = "quotes_carousel"
    const val QUOTES_CAROUSEL_ERROR_PLACEHOLDER = "quotes_carousel_error_placeholder"
    const val FILTER_TEXT_BOX = "filter_text_box"
    const val CLOSE_FILTER_TEXTBOX_BUTTON = "close_filter_textbox_button"
}
