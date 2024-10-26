package com.jmp.xtudent.factory

import com.jmp.common.usecase.exams.FetchExams
import com.jmp.common.usecase.exams.GetExamsState
import com.jmp.xtudent.uiprovider.MainUiProvider
import org.mockito.Mockito.mock

class MocksProvider {

    val fetchExams: FetchExams by lazy {
        mock(FetchExams::class.java)
    }
    val getExamsCachedState: GetExamsState by lazy {
        mock(GetExamsState::class.java)
    }
    val mainUiProvider: MainUiProvider = mock()
}
