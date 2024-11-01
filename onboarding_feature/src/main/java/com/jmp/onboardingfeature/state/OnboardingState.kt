package com.jmp.onboardingfeature.state

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp

data class OnboardingState(
    val pages: Int,
    val buttonColor: Color,
    val selectedDotColor: Color,
    val imageIdList: List<Int>,
    val imageSizeList: List<Dp>,
    val imageContentScaleList: List<ContentScale>,
    val lottieTestTags: List<String>,
    val titleResList: List<Int>,
    val descriptionResList: List<Int>,
    val backgroundImageList: List<Int>,
)
