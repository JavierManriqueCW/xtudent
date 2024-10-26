package com.jmp.onboardingfeature.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jmp.onboardingfeature.OnboardingCarouselScreenTestTags.NEXT_BUTTON
import com.jmp.onboardingfeature.OnboardingCarouselScreenTestTags.SKIP_BUTTON
import com.jmp.onboardingfeature.R

@Composable
fun OnboardingButtons(
    navController: NavController,
    fontFamily: FontFamily,
    buttonColor: Color,
    skipTo: String,
    nextOnClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 72.dp, horizontal = 64.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Text(
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                        navController.navigate(skipTo)
                    }
                    .testTag(SKIP_BUTTON),
                text = stringResource(R.string.onboarding_skip_button),
                color = buttonColor,
                fontFamily = fontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )
            Row(modifier = Modifier.wrapContentSize()) {
                Text(
                    modifier = Modifier
                        .clickable {
                            nextOnClick()
                        }
                        .testTag(NEXT_BUTTON),
                    text = stringResource(R.string.onboarding_next_button),
                    color = buttonColor,
                    fontFamily = fontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
