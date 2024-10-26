package com.jmp.xtudent.core

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.rule.IntentsRule
import com.jmp.xtudent.MainActivity
import com.jmp.xtudent.features.exams.creation.screen.ExamCreationScreen
import com.jmp.xtudent.features.exams.detail.screen.ExamDetailScreen
import com.jmp.xtudent.features.exams.list.screen.ErrorExamsScreen
import com.jmp.xtudent.features.exams.list.screen.ExamsScreen
import com.jmp.xtudent.features.onboarding.screen.OnboardingCarouselScreen
import com.karumi.shot.ScreenshotTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
open class UiTest : ScreenshotTest {

    @get:Rule
    val hiltRule: HiltAndroidRule
        get() = HiltAndroidRule(this)

    @get:Rule
    val composeRule: ComposeTestRule = createEmptyComposeRule()

    @get:Rule
    val intentsRule = IntentsRule()

    @Before
    fun init() {
        hiltRule.inject()
    }

    fun onOnboardingCarouselScreen(): OnboardingCarouselScreen {
        ActivityScenario.launch(MainActivity::class.java)

        return OnboardingCarouselScreen(composeRule)
    }

    fun onExamsScreen(): ExamsScreen {
        ActivityScenario.launch(MainActivity::class.java)

        return ExamsScreen(composeRule)
    }

    fun onErrorExamsScreen(): ErrorExamsScreen {
        ActivityScenario.launch(MainActivity::class.java)

        return ErrorExamsScreen(composeRule)
    }

    fun onExamDetailScreen(): ExamDetailScreen =
        onExamsScreen()
            .tapOnExam()

    fun onExamCreationScreen(): ExamCreationScreen =
        onExamsScreen()
            .tapOnExamCreationBottomBarButton()
}
