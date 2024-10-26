package com.jmp.xtudent.features.exams.detail.screen

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.test.swipeUp
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenTestTags.EXAM_DETAIL_BACK_BUTTON
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenTestTags.EXAM_DETAIL_DELETE_BUTTON
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenTestTags.EXAM_DETAIL_SCREEN
import com.jmp.xtudent.core.TestUtils.waitForNode
import com.jmp.xtudent.features.exams.list.screen.ExamsScreen

class ExamDetailScreen(
    private val composeTestRule: ComposeTestRule
) {

    init {
        composeTestRule.waitForNode(EXAM_DETAIL_SCREEN)
    }

    fun scrollDown(): ExamDetailScreen = apply {
        composeTestRule.apply {
            waitForNode(EXAM_DETAIL_SCREEN).performTouchInput { swipeUp() }
            waitForIdle()
        }
    }

    fun scrollUp() {
        composeTestRule.apply {
            waitForNode(EXAM_DETAIL_SCREEN).performTouchInput { swipeDown() }
            waitForIdle()
        }
    }

    fun tapOnBackButton(): ExamsScreen {
        composeTestRule.apply {
            waitForNode(EXAM_DETAIL_BACK_BUTTON).performClick()
            waitForIdle()
        }
        return ExamsScreen(composeTestRule)
    }

    fun tapOnDeleteButton(): ExamDetailDeletionDialog {
        composeTestRule.apply {
            waitForNode(EXAM_DETAIL_DELETE_BUTTON).performClick()
            waitForIdle()
        }
        return ExamDetailDeletionDialog(composeTestRule)
    }
}