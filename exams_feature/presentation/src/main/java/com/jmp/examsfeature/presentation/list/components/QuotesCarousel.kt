package com.jmp.examsfeature.presentation.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.jmp.common.ui.compose.components.constants.ComposableConstants.REGULAR_TRANSPARENCY
import com.jmp.common.ui.compose.rememberFlowWithLifecycle
import com.jmp.common.ui.model.ComponentState
import com.jmp.common.ui.theme.LargeBodyTextSize
import com.jmp.common.ui.theme.Shapes
import com.jmp.commons.utils.model.Quote
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.QUOTES_CAROUSEL
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.QUOTES_CAROUSEL_ERROR_PLACEHOLDER
import com.jmp.examsfeature.presentation.list.state.ToolbarState
import com.jmp.examsfeature.presentation.list.state.ToolbarStateResources
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun QuotesCarousel(
    modifier: Modifier = Modifier,
    quotes: List<Quote>,
    componentState: ComponentState,
    uiState: ToolbarState,
    quoteSpacing: Dp,
    onRetryTapped: () -> Unit
) {
    when (componentState) {
        is ComponentState.Initialising -> {
            SkeletonPlaceholder(modifier = modifier.padding(horizontal = quoteSpacing))
        }
        is ComponentState.Success -> {
            val pagerState = rememberPagerState { quotes.size }

            HorizontalPagerAutoPlayHandler(
                pagerState = pagerState,
                pageCount = quotes.size,
                pageChangeRateInMillis = uiState.pageChangeRateInMillis
            )

            HorizontalPager(
                modifier = modifier.testTag(QUOTES_CAROUSEL),
                state = pagerState
            ) { page ->
                QuoteContainer(
                    modifier = Modifier.padding(horizontal = quoteSpacing),
                    resources = uiState.resources,
                    quote = quotes[page],
                    backgroundColor = uiState.quoteCarouselBackgroundColor
                )
            }
        }
        is ComponentState.Error -> {
            ErrorPlaceholder(
                modifier = modifier.testTag(QUOTES_CAROUSEL_ERROR_PLACEHOLDER),
                onRetryTapped = onRetryTapped,
                refreshImage = uiState.resources.refreshButtonImage
            )
        }
    }
}

@Composable
private fun HorizontalPagerAutoPlayHandler(
    pagerState: PagerState,
    pageCount: Int,
    pageChangeRateInMillis: Long
) {
    var pageKey by remember { mutableIntStateOf(0) }
    val effectFlow = rememberFlowWithLifecycle(pagerState.interactionSource.interactions, LocalLifecycleOwner.current)

    LaunchedEffect(effectFlow) {
        effectFlow.collectLatest {
            if (it is DragInteraction.Stop) pageKey++
        }
    }

    LaunchedEffect(pageKey) {
        delay(pageChangeRateInMillis)
        val newPage = (pagerState.currentPage + 1) % pageCount
        pagerState.animateScrollToPage(newPage)
        pageKey++
    }
}

@Composable
private fun QuoteContainer(
    modifier: Modifier = Modifier,
    quote: Quote,
    resources: ToolbarStateResources,
    backgroundColor: Color
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .background(
                    color = backgroundColor,
                    shape = Shapes.extraLarge
                )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                    .weight(1f)
                    .align(Alignment.Start),
                text = quote.text,
                textAlign = TextAlign.Start,
                fontStyle = FontStyle.Italic,
                color = Color.White.copy(alpha = REGULAR_TRANSPARENCY),
                fontSize = LargeBodyTextSize,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Text(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                    .align(Alignment.Start),
                text = quote.author.ifEmpty { stringResource(resources.unknownAuthor) },
                textAlign = TextAlign.Start,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
        Image(
            modifier = Modifier
                .size(140.dp)
                .align(Alignment.CenterEnd)
                .padding(end = 40.dp),
            painter = painterResource(resources.backgroundImage),
            colorFilter = ColorFilter.tint(Color.White.copy(alpha = 0.05f)),
            contentDescription = null
        )
    }
}

@Composable
private fun SkeletonPlaceholder(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(
                color = Color.LightGray.copy(alpha = 0.3f),
                shape = Shapes.small
            )
            .shimmer()
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White,
                shape = Shapes.small
            )
        )
    }
}

@Composable
private fun ErrorPlaceholder(
    modifier: Modifier,
    onRetryTapped: () -> Unit,
    refreshImage: ImageVector
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp,)
            .background(
                color = Color.White.copy(alpha = REGULAR_TRANSPARENCY),
                shape = Shapes.small
            )
    ) {
        IconButton(
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.Center),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Gray
            ),
            onClick = onRetryTapped
        ) {
            Icon(
                modifier = Modifier.size(60.dp),
                imageVector = refreshImage,
                contentDescription = null
            )
        }
    }
}
