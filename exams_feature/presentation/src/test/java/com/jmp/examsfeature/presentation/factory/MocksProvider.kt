package com.jmp.examsfeature.presentation.factory

import com.jmp.common.usecase.exams.GetExamsState
import com.jmp.examsfeature.domain.exams.interactor.DeleteExam
import com.jmp.examsfeature.domain.exams.interactor.SaveExam
import com.jmp.examsfeature.domain.quotes.interactor.GetQuotes
import com.jmp.examsfeature.domain.xlsx.interactor.GetExamFromXlsxFile
import com.jmp.examsfeature.presentation.creation.uiprovider.ExamCreationUiProvider
import com.jmp.examsfeature.presentation.detail.uiprovider.ExamDetailUiProvider
import com.jmp.examsfeature.presentation.list.uiprovider.ExamsUiProvider
import org.mockito.Mockito
import org.mockito.Mockito.mock

class MocksProvider {

    private val mockedQuotesRepository: FakeQuotesRepositoryImplementation =
        mock(FakeQuotesRepositoryImplementation::class.java)

    private val mockedExamsRepository: FakeExamsRepositoryImplementation =
        mock(FakeExamsRepositoryImplementation::class.java)

    private val mockedXlsxRepository: FakeXlsxRepositoryImplementation =
        mock(FakeXlsxRepositoryImplementation::class.java)

    val getQuotes: GetQuotes by lazy {
        mock(
            GetQuotes::class.java,
            Mockito.withSettings().useConstructor(mockedQuotesRepository)
        )
    }
    val getExamFromXlsxFile: GetExamFromXlsxFile by lazy {
        mock(
            GetExamFromXlsxFile::class.java,
            Mockito.withSettings().useConstructor(mockedXlsxRepository)
        )
    }
    val saveExam: SaveExam by lazy {
        mock(
            SaveExam::class.java,
            Mockito.withSettings().useConstructor(mockedExamsRepository)
        )
    }
    val deleteExam: DeleteExam by lazy {
        mock(
            DeleteExam::class.java,
            Mockito.withSettings().useConstructor(mockedExamsRepository)
        )
    }
    val getExamsCachedState: GetExamsState = mock()
    val examDetailUiProvider: ExamDetailUiProvider = mock(ExamDetailUiProvider::class.java)
    val examsUiProvider: ExamsUiProvider = mock(ExamsUiProvider::class.java)
    val examCreationUiProvider: ExamCreationUiProvider = mock(ExamCreationUiProvider::class.java)
}
