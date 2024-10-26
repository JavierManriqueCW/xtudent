package com.jmp.exams_feature.data.xlsx

import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.model.ExamQuestion
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileNotFoundException

class XlsxMapperScenario {

    fun createWorkbookWithInvalidNameRow(): Workbook {
        val workbook = WorkbookFactory.create(true)
        val sheet = workbook.createSheet()
        createRow(sheet, 0, listOf("Name", "Extra Data"))
        return workbook
    }

    fun createWorkbookWithInvalidDescriptionRow(): Workbook {
        val workbook = WorkbookFactory.create(true)
        val sheet = workbook.createSheet()
        createRow(sheet, 1, listOf("Description", "Extra Data"))
        return workbook
    }

    fun createWorkbookWithInvalidImageRow(): Workbook {
        val workbook = WorkbookFactory.create(true)
        val sheet = workbook.createSheet()
        createRow(sheet, 2, listOf("image", "Extra Data"))
        return workbook
    }

    private fun createRow(sheet: Sheet, rowNum: Int, cellValues: List<String>) {
        val row = sheet.createRow(rowNum)
        cellValues.forEachIndexed { index, value ->
            row.createCell(index).setCellValue(value)
        }
    }

    companion object {
        private const val WORKBOOK_PATH = "correct_solar_system_exam.xlsx"

        val WORKBOOK_FROM_ASSETS: XSSFWorkbook by lazy {
            XlsxMapperScenario::class.java.classLoader?.getResourceAsStream(WORKBOOK_PATH)
                ?.run { XSSFWorkbook(this) } ?: throw FileNotFoundException()
        }

        val FORMATTED_EXAM: Exam =
            Exam(
                id = 0,
                name = "The solar system",
                description = "Learn about our fascinating space neighbours",
                image= "SPACE_ROCKET",
                examQuestions = listOf(
                    ExamQuestion(
                        id = 0,
                        question = "How long ago was the solar system formed?",
                        rightAnswer = "4.6 billion years ago",
                        wrongAnswers = listOf(
                            "5.2 billion years ago",
                            "6 billion years ago",
                            "13 billion years ago"
                        ),
                    ),
                    ExamQuestion(
                        id = 0,
                        question = "What is the name of the galaxy we live in?",
                        rightAnswer = "Milky Way",
                        wrongAnswers = listOf(
                            "Andromeda",
                            "Ganymede",
                            "Orion’s Belt"
                        )
                    ),
                    ExamQuestion(
                        id = 0,
                        question = "Which is the hottest planet of the solar system?",
                        rightAnswer = "Venus",
                        wrongAnswers = listOf(
                            "Mercury",
                            "Jupiter",
                            "Neptune"
                        )
                    ),
                    ExamQuestion(
                        id = 0,
                        question = "Why is Mars red?",
                        rightAnswer = "Iron colours its surface",
                        wrongAnswers = listOf(
                            "Potassium colours its surface",
                            "Its atmosphere makes it look red",
                            "It has red ice"
                        )
                    ),
                    ExamQuestion(
                        id = 0,
                        question = "What are planets named after?",
                        rightAnswer = "Roman gods",
                        wrongAnswers = listOf(
                            "Greek goods",
                            "Old astronomers",
                            "Zodiac signs"
                        )
                    ),
                    ExamQuestion(
                        id = 0,
                        question = "Where can you experience the shortest day?",
                        rightAnswer = "Jupiter",
                        wrongAnswers = listOf(
                            "Mercury",
                            "Earth",
                            "Saturn"
                        )
                    ),
                    ExamQuestion(
                        id = 0,
                        question = "Are there moons bigger than planets in our system?",
                        rightAnswer = "Yes, Titan and Ganymede are bigger than Mercury",
                        wrongAnswers = listOf(
                            "No, Mercury is slightly bigger than the biggest moon",
                            "No moon can be bigger than a planet",
                            "Our Moon has the same size as Mercury"
                        )
                    ),
                    ExamQuestion(
                        id = 0,
                        question = "What is the highest mountain in our solar system?",
                        rightAnswer = "Olympus Mons, Mars",
                        wrongAnswers = listOf(
                            "Mount Everest, Earth",
                            "Boösaule Montes, Io",
                            "Arsia Mons, Mars"
                        )
                    ),
                    ExamQuestion(
                        id = 0,
                        question = "What kind of star is the sun?",
                        rightAnswer = "Yellow dwarf",
                        wrongAnswers = listOf(
                            "White dwarf",
                            "Yellow star",
                            "White star"
                        )
                    ),
                    ExamQuestion(
                        id = 0,
                        question = "When did mankind first land on the moon?",
                        rightAnswer = "In July 1969",
                        wrongAnswers= listOf(
                            "In June 1967",
                            "In August 1968",
                            "In May 1968"
                        )
                    )
                )
            )
    }
}
