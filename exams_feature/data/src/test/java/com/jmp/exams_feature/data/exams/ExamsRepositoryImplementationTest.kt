package com.jmp.exams_feature.data.exams

import androidx.room.Room
import com.jmp.commons.utils.types.Either
import com.jmp.exams_feature.data.exams.utils.ExamsTestConstants.PRIMARY_EXAM
import com.jmp.exams_feature.data.exams.utils.ExamsTestConstants.PRIMARY_EXAM_ID
import com.jmp.exams_feature.data.exams.utils.ExamsTestConstants.SINGLE_EXAM_LIST
import com.jmp.exams_feature.data.exams.utils.ExamsScenario
import com.jmp.examsfeature.data.exams.ExamsCacheMemory
import com.jmp.examsfeature.data.exams.ExamsRepositoryImplementation
import com.jmp.storage.database.XtudentDatabase
import com.jmp.storage.database.mapper.ExamEntityMapper
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ExamsRepositoryImplementationTest {

    private val cacheMemory: ExamsCacheMemory = ExamsCacheMemory()
    private val database: XtudentDatabase =
        Room.inMemoryDatabaseBuilder(mock(), XtudentDatabase::class.java).build()
    private val mapper: ExamEntityMapper = mock()

    private val scenario = ExamsScenario(cacheMemory, database, mapper)
    private val sut: ExamsRepositoryImplementation =
        ExamsRepositoryImplementation(cacheMemory, database, mapper)

    @After
    fun tearDown() {
        cacheMemory.saveExams(emptyList())
        database.close()
    }

    @Test
    fun `should save the exam to the database and cache`() = runTest {
        scenario.stubMapExamToEntity()

        sut.saveExam(PRIMARY_EXAM).run {
            assertEquals(Either.Success(PRIMARY_EXAM_ID), this)
            assertEquals(PRIMARY_EXAM, cacheMemory.getExam(PRIMARY_EXAM_ID))
        }
    }

    @Test
    fun `should delete the exam from the database and cache`() = runTest {
        scenario
            .stubMapExamToEntity()
            .givenAnExamStoredCache()
            .givenAnExamStoredInDb()

        sut.deleteExam(PRIMARY_EXAM).run {
            assertEquals(Either.Success(Unit), this)
            assertEquals(null, cacheMemory.getExam(PRIMARY_EXAM_ID))
        }
    }

    @Test
    fun `should fetch exams from the database and save them to cache`() = runTest {
        scenario
            .stubMapExamsFromEntity()
            .givenAnExamStoredInDb()

        assertEquals(Either.Success(Unit), sut.fetchExams())
        assertEquals(SINGLE_EXAM_LIST, cacheMemory.getExamsCachedState().value)
    }
}
