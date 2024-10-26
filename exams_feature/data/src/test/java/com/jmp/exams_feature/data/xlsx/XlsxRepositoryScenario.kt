package com.jmp.exams_feature.data.xlsx

import android.content.ContentResolver
import android.net.Uri
import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.model.ExamQuestion
import com.jmp.commons.utils.types.Failure
import com.jmp.examsfeature.data.xlsx.XlsxWorkbookMapper
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.mockito.MockedStatic
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream

class XlsxRepositoryScenario(
    private val contentResolver: ContentResolver,
    private val mapper: XlsxWorkbookMapper,
    private val staticWorkbookFactoryFunctions: MockedStatic<WorkbookFactory>
) {

    fun givenValidInputStream(uri: Uri): XlsxRepositoryScenario = apply {
        whenever(contentResolver.openInputStream(uri))
            .thenReturn(ByteArrayInputStream(byteArrayOf()))
    }

    fun givenInvalidInputStream(uri: Uri): XlsxRepositoryScenario = apply {
        whenever(contentResolver.openInputStream(uri))
            .thenReturn(null)
    }

    fun givenMapToExamSuccessful(): XlsxRepositoryScenario = apply {
        whenever(mapper.mapToExam(any<Workbook>()))
            .thenReturn(FORMATTED_EXAM)
    }

    fun givenMapToExamFailedWithWrongFormatException(): XlsxRepositoryScenario = apply {
        whenever(mapper.mapToExam(any<Workbook>()))
            .thenThrow(RuntimeException(Failure.WrongExamFormatException))
    }

    fun givenMapToExamFailedWithIOException(): XlsxRepositoryScenario = apply {
        whenever(mapper.mapToExam(any<Workbook>()))
            .thenThrow(RuntimeException(IOException()))
    }

    fun stubWorkbookFactoryCreate(workbook: Workbook): XlsxRepositoryScenario = apply {
        staticWorkbookFactoryFunctions.`when`<Workbook> {
            WorkbookFactory.create(any<InputStream>())
        }.thenReturn(workbook)
    }

    companion object {

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
