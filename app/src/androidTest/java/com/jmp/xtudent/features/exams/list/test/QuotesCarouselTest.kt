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
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
@UninstallModules(ExamsRepositoryModule::class)
open class QuotesCarouselTest : UiTest() {

    @BindValue
    val examsRepository: ExamsRepository = getMockedExamsRepository()

    @Inject
    lateinit var applicationScenario: ApplicationScenario

    @Inject
    lateinit var mockExamsScenario: MockExamsScenario

    @Inject
    lateinit var quotesScenario: QuotesScenario

    @Before
    fun setUp() {
        runBlocking { mockExamsScenario.givenThatThereAreSeveralExams() }
        applicationScenario.givenThatTheOnboardingWasCompleted()
    }

    @Test
    fun shouldSwipeLeftOnTheQuotesCarousel() = runTest {
        quotesScenario.givenSomeQuotes()

        onExamsScreen()
            .waitUntilTheQuotesCarouselStateIsReady()
            .swipeLeftOnTheQuotesCarousel()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldDisplayQuoteColorsProperlyAfterSwipingLeftManyTimes() = runTest {
        quotesScenario.givenSomeQuotes()

        onExamsScreen()
            .waitUntilTheQuotesCarouselStateIsReady()
            .swipeLeftConsecutivelyOnTheQuotesCarousel()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldDisplayErrorQuoteState() = runTest {
        quotesScenario.givenThatAnErrorOccurred()

        onExamsScreen().waitUntilTheQuotesCarouselStateIsError()

        compareScreenshot(composeRule)
    }

    @Test
    fun shouldDisplayInitializingQuotesState() = runTest {
        onExamsScreen()

        compareScreenshot(composeRule)
    }
}
