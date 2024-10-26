package com.jmp.xtudent.features.exams.detail.test

import androidx.compose.ui.test.onNodeWithTag
import com.jmp.examsfeature.data.di.ExamsRepositoryModule
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreenTestTags.EXAM_DETAIL_DELETION_DIALOG
import com.jmp.xtudent.core.ApplicationScenario
import com.jmp.xtudent.core.TestUtils.getMockedExamsRepository
import com.jmp.xtudent.core.UiTest
import com.jmp.xtudent.features.exams.scenario.MockExamsScenario
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(ExamsRepositoryModule::class)
open class ExamDetailScreenTest : UiTest() {

    @BindValue
    val examsRepository: ExamsRepository = getMockedExamsRepository()

    @Inject
    lateinit var applicationScenario: ApplicationScenario

    @Inject
    lateinit var mockExamsScenario: MockExamsScenario

    @Before
    fun setUp() {
        applicationScenario.givenThatTheOnboardingWasCompleted()
        runBlocking { mockExamsScenario.givenThatThereIsOneExam() }
    }

    @Test
    fun shouldNavigateToExamDetailScreen() = runTest {
        onExamDetailScreen()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldDisplayFullContentAfterScrollingDown() = runTest {
        onExamDetailScreen().scrollDown()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldDisplayContentProperlyAfterScrollingDownAndUpAgain() = runTest {
        onExamDetailScreen()
            .scrollDown()
            .scrollUp()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldShowExamsListAfterTappingOnBackFromExamDetailScreen() = runTest {
        onExamDetailScreen().tapOnBackButton()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldShowDeletionDialogTappingOnDeleteExamButton() = runTest {
        onExamDetailScreen().tapOnDeleteButton()

        compareScreenshot(
            composeRule.onNodeWithTag(EXAM_DETAIL_DELETION_DIALOG)
        )
    }

    @Test
    fun shouldExamDetailScreenAfterCancellingDeletionDialog() = runTest {
        onExamDetailScreen()
            .tapOnDeleteButton()
            .tapOnCancelButton()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldExamsScreenWithNoExamsAfterConfirmingDeletionDialog() = runTest {
        mockExamsScenario.givenAnExamDeletion()

        onExamDetailScreen().tapOnDeleteButton().apply {
            mockExamsScenario.givenThatThereAreNoExams()
            tapOnConfirmButton()
        }

        compareScreenshot(composeRule)
    }
}
