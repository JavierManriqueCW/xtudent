package com.jmp.onboardingfeature.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun OnboardingScreenImage(
    modifier: Modifier = Modifier,
    size: Dp,
    lottieId: Int,
    lottieTestTag: String,
    contentScale: ContentScale
) {
    Row(modifier = modifier) {
        if (lottieId != 0) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieId))

            LottieAnimation(
                modifier = Modifier
                    .size(size)
                    .testTag(lottieTestTag
                    ),
                composition = composition,
                iterations = LottieConstants.IterateForever,
                contentScale = contentScale,
            )
        }
    }
}
