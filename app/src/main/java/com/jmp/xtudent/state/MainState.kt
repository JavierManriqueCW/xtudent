package com.jmp.xtudent.state

import androidx.compose.ui.unit.Dp
import com.jmp.common.ui.model.ComponentState

data class MainState(
    val componentState: ComponentState,
    val error: Int? = null,
    val loading: Boolean = false,
    val hasExams: Boolean = false,
    val homeButtonSize: Dp,
    val creationButtonSize: Dp,
    val pagesCount: Int,
    val homeScreenIndex: Int,
    val creationScreenIndex: Int
)
