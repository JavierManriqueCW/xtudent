package com.jmp.examsfeature.presentation.list.state

import androidx.compose.ui.unit.Dp
import com.jmp.common.ui.model.ComponentState
import com.jmp.commons.utils.model.Exam

data class ExamsState(
    val exams: List<Exam>,
    val examsComponentState: ComponentState,
    val xStartGradientStop: Float,
    val yStartGradientStop: Float,
    val xEndGradientStop: Float,
    val yEndGradientStop: Float,
    val maxCollapsingBarHeight: Dp,
    val topStartFadingPoint: Float,
    val bottomStartFadingPoint: Float,
    val toolbarState: ToolbarState
)
