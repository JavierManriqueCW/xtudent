package com.jmp.examsfeature.presentation.factory

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jmp.common.ui.compose.components.state.TextBoxState
import com.jmp.common.ui.model.ComponentState
import com.jmp.commons.utils.model.Quote
import com.jmp.examsfeature.presentation.list.state.ExamsState
import com.jmp.examsfeature.presentation.list.state.ToolbarState
import com.jmp.examsfeature.presentation.list.state.ToolbarStateResources
import org.mockito.Mockito.mock

class ExamsListViewModelFactory : ViewModelFactory() {

    fun makeQuotesList(): List<Quote> =
        listOf(
            makeQuote(),
            makeQuote(),
            makeQuote()
        )

    private fun makeQuote(): Quote =
        Quote(
            author = "author",
            text = "content"
        )

    fun makeInitialExamsState(): ExamsState =
        ExamsState(
            exams = listOf(),
            examsComponentState = ComponentState.Initialising,
            xStartGradientStop = 0f,
            xEndGradientStop = 0f,
            yEndGradientStop = 0f,
            yStartGradientStop = 0f,
            bottomStartFadingPoint = 0f,
            maxCollapsingBarHeight = 0.dp,
            toolbarState = makeInitialToolbarState(),
            topStartFadingPoint = 0f
        )

    private fun makeInitialToolbarState(): ToolbarState =
        ToolbarState(
            resources = ToolbarStateResources(
                unknownAuthor = 0,
                backgroundImage = 0,
                refreshButtonImage = mock(ImageVector::class.java),
                ),
            quotes = listOf(),
            filterState = TextBoxState(placeholder = 0),
            componentState = ComponentState.Initialising,
            pageChangeRateInMillis = 0,
            quoteCarouselAlphaMultiplierFactor = 0f,
            quoteCarouselBackgroundColor = Color.Black
        )
}
