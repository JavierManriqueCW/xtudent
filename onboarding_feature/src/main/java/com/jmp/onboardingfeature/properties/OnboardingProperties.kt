package com.jmp.onboardingfeature.properties

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp

data class OnboardingProperties (
    val pages: Int,
    val buttonColor: Color,
    val selectedDotColor: Color,
    val imageIdList: List<Int>,
    val imageSizeList: List<Dp>,
    val imageContentScaleList: List<ContentScale>,
    val titleResList: List<Int>,
    val descriptionResList: List<Int>,
    val backgroundImageList: List<Int>,
)
