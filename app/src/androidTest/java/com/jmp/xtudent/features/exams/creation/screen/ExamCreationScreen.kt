package com.jmp.xtudent.features.exams.creation.screen

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import com.jmp.examsfeature.presentation.creation.screens.ExamCreationScreenTestTags.EXAM_CREATION_BUTTON
import com.jmp.examsfeature.presentation.creation.screens.ExamCreationScreenTestTags.EXAM_CREATION_SCREEN
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenTestTags.EXAM_DETAIL_SCREEN
import com.jmp.xtudent.core.TestUtils.waitForNode
import com.jmp.xtudent.features.exams.list.screen.ExamsScreen
import com.jmp.xtudent.screens.MainScreenTestTags.EXAMS_LIST_BOTTOM_BAR_BUTTON

class ExamCreationScreen(
    private val composeTestRule: ComposeTestRule
) {

    init {
        composeTestRule.waitForNode(EXAM_CREATION_SCREEN)
    }

    fun scrollToBottom(): ExamCreationScreen = apply {
        composeTestRule.apply {
            waitForNode(EXAM_CREATION_SCREEN).performTouchInput { swipeUp() }
            waitForIdle()
        }
    }

    fun openPicker(): ExamCreationScreen = apply {
        composeTestRule.waitForNode(EXAM_CREATION_BUTTON).performClick()
    }

    fun navigateToExamsScreen(): ExamsScreen {
        composeTestRule.waitForNode(EXAMS_LIST_BOTTOM_BAR_BUTTON).performClick()
        return ExamsScreen(composeTestRule)
    }
}
