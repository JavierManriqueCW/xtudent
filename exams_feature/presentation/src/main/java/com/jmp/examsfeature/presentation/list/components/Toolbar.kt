package com.jmp.examsfeature.presentation.list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.android.material.math.MathUtils.lerp
import com.jmp.common.ui.compose.components.FilterTextBox
import com.jmp.common.ui.theme.BigGeneralPadding
import com.jmp.common.ui.theme.CollapsingToolbarSpacerHeight
import com.jmp.common.ui.theme.ExpandedSearchFilterRightPadding
import com.jmp.common.ui.theme.GeneralPadding
import com.jmp.common.ui.theme.LockedSearchFilterRightPadding
import com.jmp.common.ui.theme.QuoteCarouselHeight
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.CLOSE_FILTER_TEXTBOX_BUTTON
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.FILTER_TEXT_BOX
import com.jmp.examsfeature.presentation.list.state.ToolbarState

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    progress: Float,
    uiState: ToolbarState,
    isScrolling: Boolean,
    isCollapseLocked: Boolean,
    onFilterTextChanged: (String) -> Unit,
    onRetryTapped: () -> Unit,
    lockCollapseMode: (Boolean) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val interactionSource = remember { MutableInteractionSource() }
    val quoteCarouselDynamicHeight = with(LocalDensity.current) {
        lerp(
            0f,
            QuoteCarouselHeight.toPx(),
            progress
        ).toDp()
    }

    val spacerDynamicHeight = with(LocalDensity.current) {
        lerp(
            0f,
            CollapsingToolbarSpacerHeight.toPx(),
            progress
        ).toDp()
    }

    var filterRightPaddingBias by remember { mutableIntStateOf(GeneralPadding.value.toInt()) }
    val filterRightPadding = animateIntAsState(targetValue = filterRightPaddingBias, label = String())

    if (interactionSource.collectIsPressedAsState().value) {
        lockCollapseMode(true)
    }

    if (isScrolling) {
        focusManager.clearFocus()
        keyboardController?.hide()
    }

    filterRightPaddingBias =
        if (isCollapseLocked) {
            LockedSearchFilterRightPadding.value.toInt()
        }
        else {
            ExpandedSearchFilterRightPadding.value.toInt()
        }

    Column(modifier = modifier) {
        QuotesCarousel(
            modifier = Modifier
                .height(quoteCarouselDynamicHeight)
                .alpha(progress * uiState.quoteCarouselAlphaMultiplierFactor),
            quoteSpacing = GeneralPadding,
            quotes = uiState.quotes,
            uiState = uiState,
            componentState = uiState.componentState,
            onRetryTapped = onRetryTapped,
        )

        Spacer(modifier = Modifier.height(spacerDynamicHeight))

        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (filterTextBox, closeButton) = createRefs()

            FilterTextBox(
                modifier = Modifier
                    .padding(
                        start = BigGeneralPadding,
                        end = filterRightPadding.value.dp
                    )
                    .constrainAs(filterTextBox) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .testTag(FILTER_TEXT_BOX),
                textBoxState = uiState.filterState,
                onTextChanged = onFilterTextChanged,
                interactionSource = interactionSource,
            )

            Column(
                modifier = Modifier
                    .padding(end = GeneralPadding)
                    .constrainAs(closeButton) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    },
            ) {
                AnimatedVisibility(
                    visible = isCollapseLocked,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterHorizontally)
                            .testTag(CLOSE_FILTER_TEXTBOX_BUTTON),
                        onClick = {
                            lockCollapseMode(false)
                            onFilterTextChanged(String())
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}
