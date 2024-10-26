package com.jmp.xtudent.features.exams.scenario

import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.model.ExamQuestion
import com.jmp.examsfeature.presentation.creation.components.Image

object FakeExamsFactory {

    private const val QUESTION_TEXT = "Lorem ipsum?"
    private const val RIGHT_ANSWER = "Dolor sit amet"
    private const val FIRST_WRONG_ANSWER = "Magna aliqua"
    private const val SECOND_WRONG_ANSWER = "Ut enim ad minim"
    private const val THIRD_WRONG_ANSWER = "Veniam quis nostrud"

    private const val QUESTIONS_AMOUNT = 10
    const val SEVERAL_EXAMS_AMOUNT = 5
    const val EXAM_NAME = "Lorem ipsum"
    const val NON_EXISTING_EXAM_NAME = "Non existent dolor sit amet"
    private const val EXAM_DESCRIPTION = "Dolor sit amet consectetur adipiscing elit"
    private val EXAM_IMAGE = Image.BABY_BUDGIE.name

    fun makeExam(): Exam =
        Exam(
            name = EXAM_NAME,
            image = EXAM_IMAGE,
            description = EXAM_DESCRIPTION,
            examQuestions = makeExamQuestions()
        )

    fun makeExams(): List<Exam> =
        listOf(
            makeExam(),
            makeExam(),
            makeExam()
        )

    private fun makeExamQuestions(): List<ExamQuestion> =
        buildList {
            repeat(QUESTIONS_AMOUNT) {
                add(
                    ExamQuestion(
                        question = QUESTION_TEXT,
                        rightAnswer = RIGHT_ANSWER,
                        wrongAnswers = listOf(
                            FIRST_WRONG_ANSWER,
                            SECOND_WRONG_ANSWER,
                            THIRD_WRONG_ANSWER
                        )
                    )
                )
            }
        }
}