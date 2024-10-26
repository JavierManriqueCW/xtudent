package com.jmp.examsfeature.presentation.list.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import com.jmp.common.ui.compose.components.CollapsingToolbar
import com.jmp.common.ui.compose.fadingEdge
import com.jmp.common.ui.theme.FilterTextBoxHeight
import com.jmp.examsfeature.presentation.list.components.ExamsList
import com.jmp.examsfeature.presentation.list.components.Toolbar
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.EXAMS_LIST
import com.jmp.examsfeature.presentation.list.state.ExamsState

@Composable
fun ExamsScreenContent(
    modifier: Modifier = Modifier,
    uiState: ExamsState,
    onFilterTextChanged: (String) -> Unit,
    fetchQuotes: () -> Unit,
    onExamTapped: (Long) -> Unit,
) {
    val collapseModeLockedState = rememberSaveable { mutableStateOf(false) }
    val listState = rememberSaveable(saver = LazyListState.Saver) { LazyListState() }

    LaunchedEffect(Unit) {
        fetchQuotes()
    }

    CollapsingToolbar(
        modifier = modifier,
        collapseModeLockedState = collapseModeLockedState,
        onCollapseModeChange = { collapseModeLockedState.value = it },
        listState = listState,
        minCollapsingBarHeight = FilterTextBoxHeight,
        maxCollapsingBarHeight = uiState.maxCollapsingBarHeight,
        collapsingToolbar = { toolbarState, isScrolling, lockCollapseMode ->
            Toolbar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(with(LocalDensity.current) { toolbarState.height.toDp() })
                    .graphicsLayer {
                        if (toolbarState.progress > 0) {
                            translationY = toolbarState.offset
                        }
                    },
                progress = toolbarState.progress,
                uiState = uiState.toolbarState,
                isScrolling = isScrolling,
                isCollapseLocked = collapseModeLockedState.value,
                onFilterTextChanged = onFilterTextChanged,
                onRetryTapped = fetchQuotes,
                lockCollapseMode = { lockCollapseMode.invoke(it) },
            )
        },
        content = { toolbarState, _, height ->
            ExamsList(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .graphicsLayer { translationY = toolbarState.height }
                    .fadingEdge(
                        topStartFadingPoint = uiState.topStartFadingPoint,
                        bottomStartFadingPoint = uiState.bottomStartFadingPoint
                    )
                    .testTag(EXAMS_LIST),
                uiState = uiState,
                listState = listState,
                onExamTapped = onExamTapped
            )
        }
    )
}
