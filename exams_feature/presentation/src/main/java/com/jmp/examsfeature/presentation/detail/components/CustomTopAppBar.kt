package com.jmp.examsfeature.presentation.detail.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.jmp.common.ui.compose.components.BackButton
import com.jmp.common.ui.compose.components.CloseButton
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenTestTags.EXAM_DETAIL_BACK_BUTTON
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenTestTags.EXAM_DETAIL_DELETE_BUTTON

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    modifier: Modifier,
    showBackButton: Boolean = true,
    onBackTapped: () -> Unit,
    onDeleteTapped: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
        title = {},
        navigationIcon = {
            if (showBackButton) {
                BackButton(
                    modifier = Modifier
                        .size(48.dp)
                        .testTag(EXAM_DETAIL_BACK_BUTTON)
                ) { onBackTapped() }
            } else {
                CloseButton(modifier = Modifier.size(48.dp)) { onBackTapped() }
            }
        },
        actions = {
            IconButton(
                modifier = Modifier.testTag(EXAM_DETAIL_DELETE_BUTTON),
                colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White),
                onClick = onDeleteTapped
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null
                )
            }
        }
    )
}
