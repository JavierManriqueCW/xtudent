package com.jmp.xtudent.features.exams.list.screen

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeUp
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.CLOSE_FILTER_TEXTBOX_BUTTON
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.EXAMS_LIST
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.EXAMS_SCREEN
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.FILTER_TEXT_BOX
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.QUOTES_CAROUSEL
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.QUOTES_CAROUSEL_ERROR_PLACEHOLDER
import com.jmp.xtudent.core.TestUtils.waitForNode
import com.jmp.xtudent.features.exams.creation.screen.ExamCreationScreen
import com.jmp.xtudent.features.exams.detail.screen.ExamDetailScreen
import com.jmp.xtudent.features.exams.scenario.FakeExamsFactory.EXAM_NAME
import com.jmp.xtudent.features.exams.scenario.FakeExamsFactory.NON_EXISTING_EXAM_NAME
import com.jmp.xtudent.screens.MainScreenTestTags.EXAM_CREATION_BOTTOM_BAR_BUTTON

class ExamsScreen(
    private val composeTestRule: ComposeTestRule
) {

    init {
        composeTestRule.apply {
            waitForNode(EXAMS_SCREEN)
            waitForIdle()
        }
    }

    fun scrollDown(): ExamsScreen = apply {
        composeTestRule.apply {
            waitForNode(EXAMS_LIST).performTouchInput{ swipeUp() }
            waitForIdle()
        }
    }

    fun scrollUp(): ExamsScreen = apply {
        composeTestRule.apply {
            waitForNode(EXAMS_LIST).performTouchInput { swipeDown() }
            waitForIdle()
        }
    }

    fun swipeLeftOnTheQuotesCarousel(): ExamsScreen = apply {
        composeTestRule.apply {
            waitForNode(QUOTES_CAROUSEL).performTouchInput { swipeLeft() }
            waitForIdle()
        }
    }

    fun waitUntilTheQuotesCarouselStateIsReady(): ExamsScreen = apply {
        composeTestRule.waitForNode(QUOTES_CAROUSEL)
    }

    fun waitUntilTheQuotesCarouselStateIsError(): ExamsScreen = apply {
        composeTestRule.waitForNode(QUOTES_CAROUSEL_ERROR_PLACEHOLDER)
    }

    fun swipeLeftConsecutivelyOnTheQuotesCarousel(): ExamsScreen = apply {
        composeTestRule.apply {
            waitForNode(QUOTES_CAROUSEL)
                .performTouchInput {
                    repeat(AMOUNT_OF_CONSECUTIVE_LEFT_SWIPES) {
                        swipeLeft()
                    }
                }
            waitForIdle()
        }
    }

    fun tapOnFilterTextBox(): ExamsScreen = apply {
        getFilterTextBox().performClick()
    }

    fun typeExistingExamNameOnFilterTextBox(): ExamsScreen = apply {
        getFilterTextBox().performTextInput(EXAM_NAME)
    }

    fun typeNonExistingExamNameOnFilterTextBox() {
        tapOnFilterTextBox()
        getFilterTextBox().performTextInput(NON_EXISTING_EXAM_NAME)
    }

    fun deleteAllTextOnFilterTextBox() {
        getFilterTextBox().performTextClearance()
    }

    fun tapOnFilterTextBoxAndCloseIt(): ExamsScreen = apply {
        composeTestRule.apply {
            tapOnFilterTextBox()
            waitForNode(CLOSE_FILTER_TEXTBOX_BUTTON).performClick()
            waitForIdle()
        }
    }

    fun tapOutsideOfFilterTextBox(): ExamsScreen = apply {
        composeTestRule.apply {
            waitForNode(EXAMS_SCREEN).performClick()
            waitForIdle()
        }
    }

    fun tapOnExam(): ExamDetailScreen {
        composeTestRule.apply {
            waitForNode(EXAMS_LIST)
                .onChildAt(0)
                .performClick()
            waitForIdle()
        }
        return ExamDetailScreen(composeTestRule)
    }

    fun tapOnExamCreationBottomBarButton(): ExamCreationScreen {
        composeTestRule.apply {
            waitForNode(EXAM_CREATION_BOTTOM_BAR_BUTTON).performClick()
            waitForIdle()
        }
        return ExamCreationScreen(composeTestRule)
    }

    private fun getFilterTextBox(): SemanticsNodeInteraction =
        composeTestRule.waitForNode(FILTER_TEXT_BOX)

    companion object {
        private const val AMOUNT_OF_CONSECUTIVE_LEFT_SWIPES = 15
    }
}
