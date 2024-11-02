package com.jmp.examsfeature.presentation.list.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jmp.examsfeature.presentation.R
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.EMPTY_EXAMS_LOTTIE_COMPOSITION_LOADED_SEMANTICS
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.EMPTY_EXAMS_SCREEN_LOTTIE

@Composable
fun EmptyExamsScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Center)
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_empty_exam_screen))

            LottieAnimation(
                modifier = Modifier
                    .size(280.dp)
                    .align(Alignment.CenterHorizontally)
                    .testTag(EMPTY_EXAMS_SCREEN_LOTTIE)
                    .semantics {
                        composition?.let {
                            contentDescription = EMPTY_EXAMS_LOTTIE_COMPOSITION_LOADED_SEMANTICS
                        }
                    },
                composition = composition,
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.Fit,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                textAlign = TextAlign.Center,
                color = Color.LightGray,
                fontWeight = FontWeight.ExtraLight,
                lineHeight = 60.sp,
                text = "No exams importedddd",
                fontSize = 24.sp,
            )
        }
    }
}
