package com.jmp.common.ui.compose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jmp.common.presentation.R
import com.jmp.common.ui.theme.LoadingIndicatorBackgroundColor

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(
        R.raw.anim_loading
    ))

    Surface(
        modifier = modifier,
        color = LoadingIndicatorBackgroundColor,
    ) {
        Box {
            LottieAnimation(
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .align(Alignment.Center),
                composition = composition,
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}
