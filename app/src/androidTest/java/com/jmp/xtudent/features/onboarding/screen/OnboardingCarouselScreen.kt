package com.jmp.xtudent.features.onboarding.screen

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.jmp.commons.utils.test.TestUtils.waitUntil
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.EMPTY_EXAMS_SCREEN_LOTTIE
import com.jmp.onboardingfeature.OnboardingCarouselScreenTestTags.FIRST_ONBOARDING_LOTTIE
import com.jmp.onboardingfeature.OnboardingCarouselScreenTestTags.NEXT_BUTTON
import com.jmp.onboardingfeature.OnboardingCarouselScreenTestTags.ONBOARDING_SCREEN_NAME
import com.jmp.onboardingfeature.OnboardingCarouselScreenTestTags.SKIP_BUTTON
import com.jmp.xtudent.core.TestUtils.waitForNode
import com.jmp.xtudent.features.exams.list.screen.ExamsScreen

open class OnboardingCarouselScreen(
    private val composeTestRule: ComposeTestRule
) {

    init { composeTestRule.waitForNode(ONBOARDING_SCREEN_NAME) }

    fun tapOnSkipButton(): ExamsScreen {
        composeTestRule
            .onNodeWithTag(SKIP_BUTTON)
            .performClick()

        return ExamsScreen(composeTestRule)
    }

    fun tapOnNextButton() = apply {
        composeTestRule
            .onNodeWithTag(NEXT_BUTTON)
            .performClick()
    }

    fun tapConsecutivelyOnNextButton(): ExamsScreen {
        repeat(AMOUNT_OF_CONSECUTIVE_TAPS) {
            tapOnNextButton()
        }
        return ExamsScreen(composeTestRule)
    }

    fun waitUntilFirstLottieIsLoaded(): OnboardingCarouselScreen = apply {
        waitUntil {
            composeTestRule
                .onNodeWithTag(FIRST_ONBOARDING_LOTTIE)
                .fetchSemanticsNode()
                .config
                .getOrNull(SemanticsProperties.ProgressBarRangeInfo)
                ?.current != 0f
        }
    }

    companion object {
        private const val AMOUNT_OF_CONSECUTIVE_TAPS = 3
    }
}
