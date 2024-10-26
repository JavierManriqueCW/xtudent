package com.jmp.xtudent.features.exams.scenario

import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import com.jmp.xtudent.features.exams.scenario.FakeExamsFactory.makeExam
import com.jmp.xtudent.features.exams.scenario.FakeExamsFactory.makeExams
import kotlinx.coroutines.flow.MutableStateFlow
import org.mockito.kotlin.whenever
import javax.inject.Inject

class MockExamsScenario @Inject constructor(
    private val examsRepository: ExamsRepository
) {

    suspend fun givenThatThereAreNoExams() = apply {
        whenever(examsRepository.fetchExams())
            .thenReturn(Either.Success(Unit))
        whenever(examsRepository.getExamsCachedState())
            .thenReturn(MutableStateFlow(emptyList()))
    }

    suspend fun givenThatThereIsOneExam() = apply {
        whenever(examsRepository.fetchExams())
            .thenReturn(Either.Success(Unit))
        whenever(examsRepository.getExamsCachedState())
            .thenReturn(MutableStateFlow(listOf(makeExam())))
    }

    suspend fun givenThatThereAreSeveralExams() {
        whenever(examsRepository.fetchExams())
            .thenReturn(Either.Success(Unit))
        whenever(examsRepository.getExamsCachedState())
            .thenReturn(MutableStateFlow(makeExams()))
    }

    suspend fun givenThatAnErrorOccurred() {
        whenever(examsRepository.fetchExams())
            .thenReturn(Either.Error(Failure.CouldNotGetExamsFromDatabase))
    }

    suspend fun givenAnExamDeletion() {
        whenever(examsRepository.deleteExam(makeExam()))
            .thenReturn(Either.Success(Unit))
    }
}
