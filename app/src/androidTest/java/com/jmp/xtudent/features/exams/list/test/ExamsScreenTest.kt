package com.jmp.xtudent.features.exams.list.test

import com.jmp.examsfeature.data.di.ExamsRepositoryModule
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import com.jmp.xtudent.core.ApplicationScenario
import com.jmp.xtudent.core.TestUtils.getMockedExamsRepository
import com.jmp.xtudent.core.UiTest
import com.jmp.xtudent.features.exams.scenario.MockExamsScenario
import com.jmp.xtudent.features.exams.scenario.QuotesScenario
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(ExamsRepositoryModule::class)
open class ExamsScreenTest : UiTest() {

    @BindValue
    val examsRepository: ExamsRepository = getMockedExamsRepository()

    @Inject
    lateinit var mockExamsScenario: MockExamsScenario

    @Inject
    lateinit var quotesScenario: QuotesScenario

    @Inject
    lateinit var applicationScenario: ApplicationScenario

    @Before
    fun setUp() {
        applicationScenario.givenThatTheOnboardingWasCompleted()
        quotesScenario.givenSomeQuotes()
    }

    @Test
    fun shouldOpenExamsScreenWithOneItem() = runTest {
        mockExamsScenario.givenThatThereIsOneExam()

        onExamsScreen()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldOpenExamsScreenWithSeveralItems() = runTest {
        mockExamsScenario.givenThatThereAreSeveralExams()

        onExamsScreen()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldCollapseToolbarAfterScrollingDown() = runTest {
        mockExamsScenario.givenThatThereAreSeveralExams()

        onExamsScreen()
            .scrollDown()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldExpandToolbarAfterScrollingUpAgain() = runTest {
        mockExamsScenario.givenThatThereAreSeveralExams()

        onExamsScreen()
            .scrollDown()
            .scrollUp()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldOpenErrorScreenIfAnErrorOccurs() = runTest {
        mockExamsScenario.givenThatAnErrorOccurred()

        onErrorExamsScreen()

        compareScreenshot(composeRule)
    }
}
