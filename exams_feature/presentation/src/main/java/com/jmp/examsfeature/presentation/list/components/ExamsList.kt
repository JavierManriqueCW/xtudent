package com.jmp.examsfeature.presentation.list.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jmp.common.ui.compose.clickable
import com.jmp.common.ui.compose.components.LoadingIndicator
import com.jmp.common.ui.model.ComponentState
import com.jmp.common.ui.theme.ExamRowItemVerticalPadding
import com.jmp.common.ui.theme.GeneralPadding
import com.jmp.examsfeature.presentation.list.state.ExamsState

@Composable
fun ExamsList(
    modifier: Modifier = Modifier,
    uiState: ExamsState,
    listState: LazyListState,
    onExamTapped: (Long) -> Unit
) {
    when (uiState.examsComponentState) {
        is ComponentState.Initialising -> LoadingIndicator(modifier = Modifier.fillMaxSize())
        is ComponentState.Success -> {
            LazyColumn(
                modifier = modifier,
                state = listState
            ) {
                items(
                    items = uiState.exams.filter { exam ->
                        exam.name.contains(uiState.toolbarState.filterState.value)
                    }
                ) { exam ->
                    ExamItemRow(
                        modifier = Modifier
                            .clickable { onExamTapped(exam.id) }
                            .fillMaxWidth()
                            .padding(
                                vertical = ExamRowItemVerticalPadding,
                                horizontal = GeneralPadding
                            ),
                        exam = exam
                    )
                }
            }
        }
        is ComponentState.Error -> {}
    }
}
