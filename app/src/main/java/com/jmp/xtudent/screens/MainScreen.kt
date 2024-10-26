package com.jmp.xtudent.screens

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberBottomSheetScaffoldState
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
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jmp.common.ui.compose.clearFocusOnTap
import com.jmp.common.ui.compose.components.DisposableEffectWithLifecycle
import com.jmp.common.ui.compose.components.ErrorScreen
import com.jmp.common.ui.compose.components.LoadingIndicator
import com.jmp.common.ui.compose.components.SnackbarHost
import com.jmp.common.ui.model.ComponentState
import com.jmp.common.ui.theme.AnimatedBottomNavigationBarHeight
import com.jmp.common.ui.theme.GeneralPadding
import com.jmp.examsfeature.presentation.creation.screens.ExamCreationScreen
import com.jmp.examsfeature.presentation.creation.screens.ExamCreationScreenTestTags.EXAM_CREATION_SCREEN
import com.jmp.examsfeature.presentation.list.screens.ExamsScreen
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.ERROR_MAIN_SCREEN
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.EXAMS_SCREEN
import com.jmp.xtudent.R
import com.jmp.xtudent.screens.MainScreenTestTags.EXAMS_LIST_BOTTOM_BAR_BUTTON
import com.jmp.xtudent.screens.MainScreenTestTags.EXAM_CREATION_BOTTOM_BAR_BUTTON
import com.jmp.xtudent.state.MainState
import com.jmp.xtudent.viewmodel.MainActivityIntent.ChangePage
import com.jmp.xtudent.viewmodel.MainActivityIntent.Load
import com.jmp.xtudent.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    mainNavigationController: NavHostController,
    getExamFileUri: suspend () -> Uri?
) {
    val scope = rememberCoroutineScope()
    val uiState = viewModel.uiState.collectAsState()
    val snackbarHost = rememberBottomSheetScaffoldState().snackbarHostState

    DisposableEffectWithLifecycle(
        onCreate = { viewModel.sendIntent(Load) }
    )

    when (val safeState = uiState.value.componentState) {
        is ComponentState.Initialising -> {
            LoadingIndicator(modifier = Modifier.fillMaxSize())
        }
        is ComponentState.Success -> {
            val pagerState = rememberPagerState { uiState.value.pagesCount }

            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHost) },
                containerColor = Color.Transparent,
                content = { padding ->
                    DisplayedScreen(
                        modifier = Modifier
                            .statusBarsPadding()
                            .padding(padding)
                            .background(Color.Transparent)
                            .clearFocusOnTap(),
                        mainNavigationController = mainNavigationController,
                        uiState = uiState.value,
                        pagerState = pagerState,
                        snackbarHost = snackbarHost,
                        scope = scope,
                        onPageChanged = { viewModel.sendIntent(ChangePage(it)) },
                        getExamFileUri = getExamFileUri
                    )
                },
                bottomBar = {
                    BottomBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(
                                    topStart = AnimatedBottomNavigationBarHeight,
                                    topEnd = AnimatedBottomNavigationBarHeight,
                                )
                            ),
                        animatedHomeButtonSize = uiState.value.homeButtonSize,
                        animatedGenerationButtonSize = uiState.value.creationButtonSize
                    ) { page ->
                        viewModel.sendIntent(ChangePage(page))
                        scope.launch { pagerState.animateScrollToPage(page) }
                    }
                }
            )

            AnimatedVisibility(
                visible = uiState.value.loading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LoadingIndicator(modifier = Modifier.fillMaxSize())
            }
        }
        is ComponentState.Error -> {
            ErrorScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(ERROR_MAIN_SCREEN),
                error = stringResource(safeState.error.message),
                onRetry = { viewModel.sendIntent(Load) }
            )
        }
    }
}

@Composable
fun DisplayedScreen(
    modifier: Modifier,
    mainNavigationController: NavHostController,
    uiState: MainState,
    pagerState: PagerState,
    snackbarHost: SnackbarHostState,
    scope: CoroutineScope,
    onPageChanged: (Int) -> Unit,
    getExamFileUri: suspend () -> Uri?
) {
    LaunchedEffect(Unit) {
        uiState.getStartDestination().let {
            pagerState.scrollToPage(it)
            onPageChanged(it)
        }
    }

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        userScrollEnabled = false
    ) { pageIndex ->
        when (pageIndex) {
            uiState.homeScreenIndex -> {
                ExamsScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag(EXAMS_SCREEN),
                    viewModel = hiltViewModel(),
                    mainNavigationController = mainNavigationController
                )
            }

            uiState.creationScreenIndex -> {
                ExamCreationScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag(EXAM_CREATION_SCREEN),
                    viewModel = hiltViewModel(),
                    snackbarHost = snackbarHost,
                    getExamFileUri = getExamFileUri,
                    navigateBack = {
                        onPageChanged(uiState.homeScreenIndex)
                        scope.launch { pagerState.animateScrollToPage(uiState.homeScreenIndex) }
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier,
    animatedHomeButtonSize: Dp,
    animatedGenerationButtonSize: Dp,
    onPageSelected: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(GeneralPadding)
            .height(AnimatedBottomNavigationBarHeight)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            IconButton(
                modifier = Modifier
                    .size(animatedHomeButtonSize)
                    .align(Alignment.Center)
                    .testTag(EXAMS_LIST_BOTTOM_BAR_BUTTON),
                onClick = { onPageSelected.invoke(0) },
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_home),
                    contentDescription = null
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            IconButton(
                modifier = Modifier
                    .size(animatedGenerationButtonSize)
                    .align(Alignment.Center)
                    .testTag(EXAM_CREATION_BOTTOM_BAR_BUTTON),
                onClick = { onPageSelected.invoke(1) },
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = null
                )
            }
        }
    }
}

private fun MainState.getStartDestination(): Int =
    if (hasExams) 0 else 1

object MainScreenTestTags {
    const val EXAMS_LIST_BOTTOM_BAR_BUTTON = "exams_list_bottom_bar_button"
    const val EXAM_CREATION_BOTTOM_BAR_BUTTON = "exam_creation_bottom_bar_button"
}
