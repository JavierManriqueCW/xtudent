package com.jmp.xtudent.features.exams.list.test

import com.jmp.xtudent.core.ApplicationScenario
import com.jmp.xtudent.core.UiTest
import com.jmp.xtudent.features.exams.scenario.RealExamsScenario
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
open class FilterTextBoxTest : UiTest() {

    @Inject
    lateinit var applicationScenario: ApplicationScenario

    @Inject
    lateinit var realExamsScenario: RealExamsScenario

    @Before
    fun setUp() {
        applicationScenario.givenThatTheOnboardingWasCompleted()
    }

    @Test
    fun shouldFindExamAfterTypingOnFilterTextBox() = runTest {
        realExamsScenario.givenThatThereAreSeveralExams()

        onExamsScreen().typeExistingExamNameOnFilterTextBox()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldShowExamsAfterDeletingTextFilterContent() = runTest {
        realExamsScenario.givenThatThereAreSeveralExams()

        onExamsScreen()
            .typeExistingExamNameOnFilterTextBox()
            .deleteAllTextOnFilterTextBox()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldNotFindExamAfterTypingANonExistingExamNameOnFilterTextBox() = runTest {
        realExamsScenario.givenThatThereAreSeveralExams()

        onExamsScreen().typeNonExistingExamNameOnFilterTextBox()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldCloseFilteringModeAfterTypingOnTheCloseFilterButton() = runTest {
        realExamsScenario.givenThatThereAreSeveralExams()

        onExamsScreen().tapOnFilterTextBoxAndCloseIt()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldNotExpandToolbarOnFilteringMode() = runTest {
        realExamsScenario.givenThatThereAreSeveralExams()

        onExamsScreen()
            .tapOnFilterTextBox()
            .scrollUp()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldExpandToolbarAfterClosingFilteringMode() = runTest {
        realExamsScenario.givenThatThereAreSeveralExams()

        onExamsScreen()
            .tapOnFilterTextBoxAndCloseIt()
            .scrollUp()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldLoseFilterHighlightAndCloseKeyboardAfterTappingOutside() = runTest {
        realExamsScenario.givenThatThereIsOneExam()

        onExamsScreen()
            .tapOnFilterTextBox()
            .tapOutsideOfFilterTextBox()
    }
}
