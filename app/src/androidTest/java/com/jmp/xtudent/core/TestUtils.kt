package com.jmp.xtudent.core

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import com.jmp.xtudent.core.di.fake.FakeExamsRepositoryImplementation
import org.mockito.Mockito.mock

object TestUtils {

    private const val DEFAULT_TIMEOUT_MILLIS = 5_000L

    fun ComposeTestRule.waitForNode(
        testTag: String,
        timeoutMillis: Long = DEFAULT_TIMEOUT_MILLIS
    ): SemanticsNodeInteraction =
        onNodeWithTag(testTag).apply {
            waitUntil(timeoutMillis) {
                isDisplayed()
            }
        }

    fun getMockedExamsRepository(): ExamsRepository =
        mock(FakeExamsRepositoryImplementation::class.java)
}
