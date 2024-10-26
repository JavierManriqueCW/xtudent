package com.jmp.common.ui.theme

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.defaultShimmerTheme

val skeletonTheme = defaultShimmerTheme.copy(
    animationSpec = infiniteRepeatable(
        animation = tween(
            durationMillis = 600,
            delayMillis = 100,
            easing = LinearEasing,
        ),
    ),
    blendMode = BlendMode.DstIn,
    shimmerWidth = 1_000.dp,
)
