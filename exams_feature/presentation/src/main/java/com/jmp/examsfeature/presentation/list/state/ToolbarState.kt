package com.jmp.examsfeature.presentation.list.state

import androidx.compose.ui.graphics.Color
import com.jmp.common.ui.compose.components.state.TextBoxState
import com.jmp.common.ui.model.ComponentState
import com.jmp.commons.utils.model.Quote

data class ToolbarState(
    val resources: ToolbarStateResources,
    val quotes: List<Quote>,
    val filterState: TextBoxState,
    val quoteCarouselAlphaMultiplierFactor: Float,
    val quoteCarouselBackgroundColor: Color,
    val componentState: ComponentState,
    val pageChangeRateInMillis: Long
)
