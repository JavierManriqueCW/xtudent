package com.jmp.exams_feature.data.xlsx

import com.jmp.commons.utils.types.Failure
import com.jmp.exams_feature.data.xlsx.XlsxMapperScenario.Companion.FORMATTED_EXAM
import com.jmp.exams_feature.data.xlsx.XlsxMapperScenario.Companion.WORKBOOK_FROM_ASSETS
import com.jmp.examsfeature.data.xlsx.XlsxWorkbookMapper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class XlsxWorkbookMapperTest {

    private val sut = XlsxWorkbookMapper()
    private val scenario = XlsxMapperScenario()

    @Test
    fun `should map a valid workbook to an Exam object`() {
        assertEquals(
            FORMATTED_EXAM,
            sut.mapToExam(WORKBOOK_FROM_ASSETS)
        )
    }

    @Test
    fun `should throw WrongExamFormatException if name row is invalid`() {
        assertThrows(Failure.WrongExamFormatException::class.java) {
            sut.mapToExam(
                scenario.createWorkbookWithInvalidNameRow()
            )
        }
    }

    @Test
    fun `should throw WrongExamFormatException if description row is invalid`() {
        assertThrows(Failure.WrongExamFormatException::class.java) {
            sut.mapToExam(
                scenario.createWorkbookWithInvalidDescriptionRow()
            )
        }
    }

    @Test
    fun `should throw WrongExamFormatException if image row is invalid`() {
        assertThrows(Failure.WrongExamFormatException::class.java) {
            sut.mapToExam(
                scenario.createWorkbookWithInvalidImageRow()
            )
        }
    }
}
