package com.jmp.onboardingfeature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jmp.common.ui.navigation.Screen
import com.jmp.common.ui.theme.LargeBodyTextSize
import com.jmp.common.ui.theme.Shapes
import com.jmp.onboardingfeature.OnboardingCarouselScreenTestTags.ONBOARDING_SCREEN_NAME
import com.jmp.onboardingfeature.components.OnboardingButtons
import com.jmp.onboardingfeature.components.OnboardingScreenImage
import com.jmp.onboardingfeature.state.OnboardingState
import com.jmp.onboardingfeature.viewmodel.OnboardingIntent
import com.jmp.onboardingfeature.viewmodel.OnboardingViewModel
import kotlinx.coroutines.launch

@Composable
fun OnboardingCarouselScreen(
    viewModel: OnboardingViewModel,
    mainNavigationController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val uiState = viewModel.uiState.collectAsState()
    val pagerState = rememberPagerState { uiState.value.pages }

    HorizontalPager(
        modifier = Modifier
            .fillMaxHeight()
            .testTag(ONBOARDING_SCREEN_NAME),
        state = pagerState
    ) { page ->
        OnboardingStepBody(
            modifier = Modifier
                .paint(
                    painter = painterResource(uiState.value.backgroundImageList[page]),
                    contentScale = ContentScale.FillBounds
                ),
            state = uiState.value,
            page = page
        )
    }
    OnboardingButtons(
        navController = mainNavigationController,
        fontFamily = FontFamily.Default,
        buttonColor = uiState.value.buttonColor,
        skipTo = Screen.Main.route,
        nextOnClick = {
            scope.launch {
                if (pagerState.currentPage == uiState.value.pages - 1) {
                    mainNavigationController.popBackStack()
                    mainNavigationController.navigate(Screen.Main.route)
                    viewModel.sendIntent(OnboardingIntent.DisableShouldShowOnboarding)
                    return@launch
                }
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    )
}

@Composable
private fun OnboardingStepBody(
    modifier: Modifier = Modifier,
    state: OnboardingState,
    page: Int
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(bottom = 48.dp)
                .align(Alignment.Center)
        ) {
            OnboardingScreenImage(
                modifier = Modifier
                    .padding(horizontal = 48.dp)
                    .align(Alignment.CenterHorizontally),
                size = state.imageSizeList[page],
                lottieId = state.imageIdList[page],
                lottieTestTag = state.lottieTestTags[page],
                contentScale = state.imageContentScaleList[page],
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                state.titleResList[page].let { titleRes ->
                    state.descriptionResList[page].let { descriptionRes ->
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(titleRes),
                                color = Color.White,
                                fontFamily = FontFamily.Default,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .background(
                                        color = Color.White.copy(alpha = 0.07f),
                                        shape = Shapes.large
                                    )
                            ) {
                                Text(
                                    modifier = Modifier.padding(
                                        horizontal = 32.dp,
                                        vertical = 16.dp
                                    ),
                                    text = stringResource(descriptionRes),
                                    color = Color.LightGray,
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight.Light,
                                    fontSize = LargeBodyTextSize,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

object OnboardingCarouselScreenTestTags {
    const val ONBOARDING_SCREEN_NAME = "onboarding_carousel_screen"
    const val FIRST_ONBOARDING_LOTTIE = "first_onboarding_lottie"
    const val SECOND_ONBOARDING_LOTTIE = "second_onboarding_lottie"
    const val THIRD_ONBOARDING_LOTTIE = "third_onboarding_lottie"
    const val ONBOARDING_LOTTIE_COMPOSITION_LOADED_SEMANTICS = "onboarding_lottie_composition_loaded_semantics"
    const val NEXT_BUTTON = "next_button"
    const val SKIP_BUTTON = "skip_button"
}
