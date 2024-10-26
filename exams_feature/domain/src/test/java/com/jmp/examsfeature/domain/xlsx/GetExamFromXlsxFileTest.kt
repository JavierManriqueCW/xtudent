package com.jmp.examsfeature.domain.xlsx

import com.jmp.examsfeature.domain.xlsx.interactor.GetExamFromXlsxFile
import com.jmp.examsfeature.domain.xlsx.repository.XlsxRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

/**
 * Dummy test class for [GetExamFromXlsxFile]
 */
@ExperimentalCoroutinesApi
class GetExamFromXlsxFileTest {

    private val repository: XlsxRepository = mock()
    private val sut: GetExamFromXlsxFile = GetExamFromXlsxFile(repository)

    @Test
    fun `repository function getExamFromXlsxFile is invoked`() = runTest {
        sut.invoke(mock())
        verify(repository).getExamFromXlsxFile(any())
    }
}
