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
import androidx.compose.runtime.remember
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
import com.jmp.common.ui.theme.FirstOnboardingImageSize
import com.jmp.common.ui.theme.LargeBodyTextSize
import com.jmp.common.ui.theme.SecondOnboardingImageSize
import com.jmp.common.ui.theme.Shapes
import com.jmp.common.ui.theme.ThirdOnboardingImageSize
import com.jmp.onboardingfeature.OnboardingCarouselScreenTestTags.ONBOARDING_SCREEN_NAME
import com.jmp.onboardingfeature.components.OnboardingButtons
import com.jmp.onboardingfeature.components.OnboardingScreenImage
import com.jmp.onboardingfeature.properties.OnboardingProperties
import com.jmp.onboardingfeature.state.OnboardingState
import com.jmp.onboardingfeature.viewmodel.OnboardingViewModel
import kotlinx.coroutines.launch

@Composable
fun OnboardingCarouselScreen(
    viewModel: OnboardingViewModel,
    mainNavigationController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val onboardingProperties = remember { buildOnboardingProperties() }
    val currentPageViewState = remember { OnboardingState() }
    val pagerState = rememberPagerState { onboardingProperties.pages }

    HorizontalPager(
        modifier = Modifier
            .fillMaxHeight()
            .testTag(ONBOARDING_SCREEN_NAME),
        state = pagerState
    ) { currentPageIndex ->
        updateCurrentPageViewState(
            currentPageIndex = currentPageIndex,
            state = currentPageViewState,
            onboardingProperties = onboardingProperties
        )
        OnboardingStepBody(
            modifier = Modifier
                .paint(
                    painter = painterResource(currentPageViewState.backgroundImage),
                    contentScale = ContentScale.FillBounds
                ),
            viewState = currentPageViewState
        )
    }
    OnboardingButtons(
        navController = mainNavigationController,
        fontFamily = FontFamily.Default,
        buttonColor = onboardingProperties.buttonColor,
        skipTo = Screen.Main.route,
        nextOnClick = {
            scope.launch {
                if (pagerState.currentPage == onboardingProperties.pages - 1) {
                    mainNavigationController.popBackStack()
                    mainNavigationController.navigate(Screen.Main.route)
                    viewModel.disableShouldShowOnboarding()
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
    viewState: OnboardingState
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
                size = viewState.imageSize,
                lottieId = viewState.imageId,
                contentScale = viewState.imageContentScale,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                viewState.titleRes?.let { titleRes ->
                    viewState.descriptionRes?.let { descriptionRes ->
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

private fun buildOnboardingProperties(): OnboardingProperties =
    OnboardingProperties(
        pages = 3,
        imageIdList = listOf(
            R.raw.anim_first_onboarding_step,
            R.raw.anim_second_onboarding_step,
            R.raw.anim_third_onboarding_step
        ),
        titleResList = listOf(
            R.string.first_onboarding_step_title,
            R.string.second_onboarding_step_title,
            R.string.third_onboarding_step_title
        ),
        descriptionResList = listOf(
            R.string.first_onboarding_step_description,
            R.string.second_onboarding_step_description,
            R.string.third_onboarding_step_description
        ),
        backgroundImageList = listOf(
            R.drawable.bg_onboarding_first_step,
            R.drawable.bg_onboarding_second_step,
            R.drawable.bg_onboarding_third_step
        ),
        imageSizeList = listOf(
            FirstOnboardingImageSize,
            SecondOnboardingImageSize,
            ThirdOnboardingImageSize
        ),
        imageContentScaleList = listOf(
            ContentScale.Crop,
            ContentScale.Crop,
            ContentScale.FillBounds
        ),
        buttonColor = Color.White,
        selectedDotColor = Color.White
    )

private fun updateCurrentPageViewState(
    currentPageIndex: Int,
    state: OnboardingState,
    onboardingProperties: OnboardingProperties
) {
    state.apply {
        imageId = onboardingProperties.imageIdList[currentPageIndex]
        titleRes = onboardingProperties.titleResList[currentPageIndex]
        descriptionRes = onboardingProperties.descriptionResList[currentPageIndex]
        backgroundImage = onboardingProperties.backgroundImageList[currentPageIndex]
        imageSize = onboardingProperties.imageSizeList[currentPageIndex]
        imageContentScale = onboardingProperties.imageContentScaleList[currentPageIndex]
    }
}

object OnboardingCarouselScreenTestTags {
    const val ONBOARDING_SCREEN_NAME = "onboarding_carousel_screen"
    const val NEXT_BUTTON = "next_button"
    const val SKIP_BUTTON = "skip_button"
}
