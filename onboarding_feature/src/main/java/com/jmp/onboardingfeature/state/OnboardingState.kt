package com.jmp.onboardingfeature.state

import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class OnboardingState(
    var imageId: Int = 0,
    var pagerState: PagerState? = null,
    var titleRes: Int? = null,
    var descriptionRes: Int? = null,
    var backgroundImage: Int = 0,
    var imageSize: Dp = 0.dp,
    var imageContentScale: ContentScale = ContentScale.None
)
