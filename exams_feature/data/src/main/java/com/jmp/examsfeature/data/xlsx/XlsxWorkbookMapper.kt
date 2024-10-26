package com.jmp.examsfeature.data.xlsx

import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.model.ExamQuestion
import com.jmp.commons.utils.types.Failure
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook
import javax.inject.Inject

class XlsxWorkbookMapper @Inject constructor() {

    fun mapToExam(workbook: Workbook): Exam =
        workbook.getFilteredListRow().run {
            val name = firstOrNull { it.rowNum == NAME_ROW }
                ?.takeIf { it.checkInformativeRowFormatValidity() }
                ?.first()?.stringCellValue
                ?: throw Failure.WrongExamFormatException

            val description = firstOrNull { it.rowNum == DESCRIPTION_ROW }
                ?.takeIf { it.checkInformativeRowFormatValidity() }
                ?.first()?.stringCellValue
                ?: throw Failure.WrongExamFormatException

            val image = firstOrNull { it.rowNum == IMAGE_ROW }
                ?.takeIf { it.checkInformativeRowFormatValidity() }
                ?.first()?.stringCellValue
                ?: throw Failure.WrongExamFormatException

            val questions = filter { it.rowNum != NAME_ROW && it.rowNum != DESCRIPTION_ROW }
                .mapNotNull { row ->
                    if (row.checkQuestionFormatValidity()) mapExamQuestion(row) else null
                }

            Exam(
                name = name,
                description = description,
                examQuestions = questions,
                image = image
            )
        }

    private fun Workbook.getFilteredListRow(): List<Row> =
        first().filterNot { row -> row.all { it.stringCellValue.isEmpty() } }

    private fun mapExamQuestion(row: Row): ExamQuestion =
        ExamQuestion(
            question = row.first().stringCellValue,
            rightAnswer = row.elementAt(1).stringCellValue,
            wrongAnswers = row
                .filter { it.columnIndex > 1 && it.stringCellValue.isNotEmpty() }
                .map { it.stringCellValue }
        )

    private fun Row.checkInformativeRowFormatValidity(): Boolean =
        count { it.stringCellValue.isNotEmpty() } == MAX_COLUMNS_PER_INFORMATIVE_ROW
                && !first().stringCellValue.isNullOrEmpty()

    private fun Row.checkQuestionFormatValidity(): Boolean =
        count { it.stringCellValue.isNotEmpty() } > MIN_NUMBER_OF_FILLED_COLUMNS_PER_QUESTION_ROW
                && !first().stringCellValue.isNullOrEmpty()

    companion object {
        const val MAX_COLUMNS_PER_INFORMATIVE_ROW = 1
        const val MIN_NUMBER_OF_FILLED_COLUMNS_PER_QUESTION_ROW = 3
        const val NAME_ROW = 0
        const val DESCRIPTION_ROW = 1
        const val IMAGE_ROW = 2
    }
}
