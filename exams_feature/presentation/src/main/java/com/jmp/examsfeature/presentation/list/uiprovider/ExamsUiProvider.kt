package com.jmp.examsfeature.presentation.list.uiprovider

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import com.jmp.common.ui.compose.components.state.TextBoxState
import com.jmp.common.ui.model.ComponentState
import com.jmp.common.ui.theme.CollapsingToolbarSpacerHeight
import com.jmp.common.ui.theme.FilterTextBoxHeight
import com.jmp.common.ui.theme.QuoteCardBackgroundColor
import com.jmp.common.ui.theme.QuoteCarouselHeight
import com.jmp.examsfeature.presentation.R
import com.jmp.examsfeature.presentation.list.state.ExamsState
import com.jmp.examsfeature.presentation.list.state.ToolbarState
import com.jmp.examsfeature.presentation.list.state.ToolbarStateResources
import javax.inject.Inject

open class ExamsUiProvider @Inject constructor() {

    fun provide(): ExamsState =
        ExamsState(
            xStartGradientStop = X_START_GRADIENT_STOP,
            yStartGradientStop = Y_START_GRADIENT_STOP,
            xEndGradientStop = X_END_GRADIENT_STOP,
            yEndGradientStop = Y_END_GRADIENT_STOP,
            exams = listOf(),
            examsComponentState = ComponentState.Initialising,
            maxCollapsingBarHeight = MAX_COLLAPSING_BAR_HEIGHT,
            topStartFadingPoint = TOP_START_FADING_POINT,
            bottomStartFadingPoint = BOTTOM_START_FADING_POINT,
            toolbarState = provideToolbarState()
        )

    private fun provideToolbarState() =
        ToolbarState(
            resources = provideToolbarStateResources(),
            quotes = listOf(),
            filterState = TextBoxState(
                placeholder = R.string.exams_screen_filter_placeholder
            ),
            quoteCarouselAlphaMultiplierFactor = QUOTE_CAROUSEL_ALPHA_MULTIPLIER_FACTOR,
            quoteCarouselBackgroundColor = QuoteCardBackgroundColor,
            componentState = ComponentState.Initialising,
            pageChangeRateInMillis = CAROUSEL_PAGE_CHANGE_RATE_IN_MILLIS
        )

    private fun provideToolbarStateResources(): ToolbarStateResources =
        ToolbarStateResources(
            unknownAuthor = R.string.exams_screen_quotes_unknown_author,
            backgroundImage = R.drawable.ic_olive_branches,
            refreshButtonImage = Icons.Outlined.Refresh
        )

    private companion object {
        private const val X_START_GRADIENT_STOP = 0f
        private const val Y_START_GRADIENT_STOP = 0f
        private const val X_END_GRADIENT_STOP = 400f
        private const val Y_END_GRADIENT_STOP = 50f
        private val MAX_COLLAPSING_BAR_HEIGHT =
            QuoteCarouselHeight + CollapsingToolbarSpacerHeight + FilterTextBoxHeight
        private const val TOP_START_FADING_POINT = 0.05f
        private const val BOTTOM_START_FADING_POINT = 0.95f
        private const val QUOTE_CAROUSEL_ALPHA_MULTIPLIER_FACTOR = 1.75f
        private const val CAROUSEL_PAGE_CHANGE_RATE_IN_MILLIS: Long = 7000
    }
}
