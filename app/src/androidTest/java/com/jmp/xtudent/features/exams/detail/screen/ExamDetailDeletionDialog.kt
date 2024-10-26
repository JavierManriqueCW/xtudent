package com.jmp.xtudent.features.exams.detail.screen

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.performClick
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenTestTags.EXAM_DELETION_DIALOG_CANCEL_BUTTON
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenTestTags.EXAM_DELETION_DIALOG_CONFIRM_BUTTON
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenTestTags.EXAM_DETAIL_DELETION_DIALOG
import com.jmp.xtudent.core.TestUtils.waitForNode
import com.jmp.xtudent.features.exams.list.screen.ExamsScreen

class ExamDetailDeletionDialog(
    private val composeTestRule: ComposeTestRule
) {

    init {
        composeTestRule.apply {
            waitForNode(EXAM_DETAIL_DELETION_DIALOG)
        }
    }

    fun tapOnCancelButton(): ExamDetailScreen {
        composeTestRule.apply {
            waitForNode(EXAM_DELETION_DIALOG_CANCEL_BUTTON).performClick()
            waitForIdle()
        }
        return ExamDetailScreen(composeTestRule)
    }

    fun tapOnConfirmButton(): ExamsScreen {
        composeTestRule.apply {
            waitForNode(EXAM_DELETION_DIALOG_CONFIRM_BUTTON).performClick()
            waitForIdle()
        }

        return ExamsScreen(composeTestRule)
    }
}