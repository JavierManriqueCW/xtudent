package com.jmp.xtudent.features.exams.list.screen

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.ERROR_MAIN_SCREEN
import com.jmp.xtudent.core.TestUtils.waitForNode

class ErrorExamsScreen(
    composeTestRule: ComposeTestRule
) {

    init { composeTestRule.waitForNode(ERROR_MAIN_SCREEN) }
}
