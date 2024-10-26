package com.jmp.xtudent.features.onboarding.test

import com.jmp.xtudent.core.ApplicationScenario
import com.jmp.xtudent.core.UiTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
open class OnboardingCarouselScreenTest : UiTest() {

    @Inject
    lateinit var applicationScenario: ApplicationScenario

    @Before
    fun setUp() {
        applicationScenario.givenThatTheOnboardingWasNeverCompleted()
    }

    @Test
    fun shouldOpenOnboardingCarouselScreen() = runTest {
        onOnboardingCarouselScreen()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldNavigateToSecondOnboardingSlide() = runTest {
        onOnboardingCarouselScreen()
            .tapOnNextButton()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldNavigateToThirdOnboardingSlide() = runTest {
        onOnboardingCarouselScreen()
            .tapOnNextButton()
            .tapOnNextButton()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldNavigateToExamsScreenAfterThirdOnboardingSlide() = runTest {
        onOnboardingCarouselScreen()
            .tapConsecutivelyOnNextButton()
    }

    @Test
    fun shouldSkipOnboardingScreen() = runTest {
        onOnboardingCarouselScreen()
            .tapOnSkipButton()

        compareScreenshot(composeRule)
    }
}
