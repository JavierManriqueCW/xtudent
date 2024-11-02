package com.jmp.xtudent.features.exams.creation.test

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.jmp.xtudent.core.ApplicationScenario
import com.jmp.xtudent.core.UiTest
import com.jmp.xtudent.features.exams.scenario.RealExamsScenario
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
class ExamCreationScreenTest : UiTest() {

    @Inject
    lateinit var applicationScenario: ApplicationScenario

    @Inject
    lateinit var realExamsScenario: RealExamsScenario

    @Before
    fun setUp() {
        applicationScenario.givenThatTheOnboardingWasCompleted()
    }

    @Test
    fun shouldShowExamCreationScreen() {
        onExamCreationScreen()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldShowOneExamAfterImportingARightFormatExam() {
        realExamsScenario.givenTheUserSelectsARightFormatXlsxFile()

        onExamCreationScreen()
            .scrollToBottom()
            .openPicker()
            .navigateToExamsScreen()
            .waitUntilExamImageIsLoaded()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldShowABudgieImageAfterImportingAnExamWithAnUndefinedImage() {
        realExamsScenario.givenTheUserSelectsAnExamWithAnUndefinedImage()

        onExamCreationScreen()
            .scrollToBottom()
            .openPicker()
            .navigateToExamsScreen()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldShowNoExamsAndAnErrorMessageAfterImportingAWrongFormatExam() {
        realExamsScenario.givenTheUserSelectsAWrongFormatXlsxFile()

        onExamCreationScreen()
            .scrollToBottom()
            .openPicker()
            .navigateToExamsScreen()

        compareScreenshot(composeRule)
    }
}
