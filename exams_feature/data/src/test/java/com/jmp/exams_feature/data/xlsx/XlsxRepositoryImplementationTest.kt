package com.jmp.exams_feature.data.xlsx

import android.content.ContentResolver
import android.net.Uri
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.exams_feature.data.xlsx.XlsxMapperScenario.Companion.FORMATTED_EXAM
import com.jmp.exams_feature.data.xlsx.XlsxMapperScenario.Companion.WORKBOOK_FROM_ASSETS
import com.jmp.examsfeature.data.xlsx.XlsxRepositoryImplementation
import com.jmp.examsfeature.data.xlsx.XlsxWorkbookMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.MockedStatic
import org.mockito.Mockito.mock
import org.mockito.Mockito.mockStatic

@ExperimentalCoroutinesApi
class XlsxRepositoryImplementationTest {
    
    private val contentResolver: ContentResolver = mock()
    private val mapper: XlsxWorkbookMapper = mock()
    private val uri: Uri = mock(Uri::class.java)
    private val staticWorkbookFactoryFunctions: MockedStatic<WorkbookFactory> =
        mockStatic(WorkbookFactory::class.java)

    private val sut: XlsxRepositoryImplementation =
        XlsxRepositoryImplementation(
            contentResolver,
            mapper
        )
    private val scenario: XlsxRepositoryScenario =
        XlsxRepositoryScenario(
            contentResolver,
            mapper,
            staticWorkbookFactoryFunctions
        )

    @After
    fun tearDown() {
        staticWorkbookFactoryFunctions.close()
    }

    @Test
    fun `should return Success with Exam when file is valid`() = runTest {
        scenario
            .givenValidInputStream(uri)
            .givenMapToExamSuccessful()
            .stubWorkbookFactoryCreate(WORKBOOK_FROM_ASSETS)

        assertEquals(
            Either.Success(FORMATTED_EXAM),
            sut.getExamFromXlsxFile(uri)
        )
    }

    @Test
    fun `should return Error with CouldNotOpenFile when inputStream is null`() = runTest {
        scenario
            .givenInvalidInputStream(uri)
            .givenMapToExamSuccessful()

        assertEquals(
            Either.Error(Failure.CouldNotOpenFile),
            sut.getExamFromXlsxFile(uri)
        )
    }

    @Test
    fun `should return Error with WrongExamFormat when WrongExamFormatException is thrown`() = runTest {
        scenario
            .givenValidInputStream(uri)
            .givenMapToExamFailedWithWrongFormatException()
            .stubWorkbookFactoryCreate(WORKBOOK_FROM_ASSETS)

        assertEquals(
            Either.Error(Failure.WrongExamFormat),
            sut.getExamFromXlsxFile(uri)
        )
    }

    @Test
    fun `should return Error with UnknownError when IOException is thrown`() = runTest {
        scenario
            .givenValidInputStream(uri)
            .givenMapToExamFailedWithIOException()
            .stubWorkbookFactoryCreate(WORKBOOK_FROM_ASSETS)

        assertEquals(
            Either.Error(Failure.UnknownError()).javaClass,
            sut.getExamFromXlsxFile(uri).javaClass
        )
    }
}
