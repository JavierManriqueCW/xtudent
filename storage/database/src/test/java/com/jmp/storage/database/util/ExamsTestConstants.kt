package com.jmp.storage.database.util

import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.model.ExamQuestion
import com.jmp.storage.database.entity.ExamEntity
import com.jmp.storage.database.entity.ExamQuestionEntity
import com.jmp.storage.database.entity.ExamWithQuestions

object ExamsTestConstants {

    const val PRIMARY_EXAM_ID: Long = 1
    private const val PRIMARY_EXAM_NAME: String = "Lorem ipsum"
    private const val PRIMARY_EXAM_DESCRIPTION: String = "Dolor sit amet"
    private const val PRIMARY_EXAM_IMAGE: String = "BABY_BUDGIE"

    private const val SECONDARY_EXAM_ID: Long = 2
    private const val SECONDARY_EXAM_NAME: String = "Consectetur adipiscing elit"
    private const val SECONDARY_EXAM_DESCRIPTION: String = "Magna aliqua"
    private const val SECONDARY_EXAM_IMAGE: String = "SPACE_ROCKET"

    private const val QUESTION_ID: Long = 0
    private const val QUESTION_TEXT: String = "Lorem question"
    private const val QUESTION_RIGHT_ANSWER_TEXT: String = "Lorem right answer"
    private const val FIRST_WRONG_ANSWER_TEXT: String = "Answer 1"
    private const val SECOND_WRONG_ANSWER_TEXT: String = "Answer 2"
    private const val THIRD_WRONG_ANSWER_TEXT: String = "Answer 3"

    private val WRONG_ANSWER_LIST: List<String> = listOf(
        FIRST_WRONG_ANSWER_TEXT,
        SECOND_WRONG_ANSWER_TEXT,
        THIRD_WRONG_ANSWER_TEXT
    )

    private val EXAM_QUESTION: ExamQuestion =
        ExamQuestion(
            id = QUESTION_ID,
            question = QUESTION_TEXT,
            rightAnswer = QUESTION_RIGHT_ANSWER_TEXT,
            wrongAnswers = WRONG_ANSWER_LIST
        )

    private val EXAM_QUESTION_ENTITY: ExamQuestionEntity =
        ExamQuestionEntity(
            questionId = QUESTION_ID,
            question = QUESTION_TEXT,
            rightAnswer = QUESTION_RIGHT_ANSWER_TEXT,
            wrongAnswers = WRONG_ANSWER_LIST,
            examIdFk = PRIMARY_EXAM_ID
        )

    val EXAM_QUESTION_LIST = listOf(EXAM_QUESTION)
    val EXAM_QUESTION_ENTITY_LIST = listOf(EXAM_QUESTION_ENTITY)

    val PRIMARY_EXAM: Exam =
        Exam(
            id = PRIMARY_EXAM_ID,
            name = PRIMARY_EXAM_NAME,
            description = PRIMARY_EXAM_DESCRIPTION,
            image = PRIMARY_EXAM_IMAGE,
            examQuestions = EXAM_QUESTION_LIST
        )

    private val SECONDARY_EXAM: Exam =
        Exam(
            id = SECONDARY_EXAM_ID,
            name = SECONDARY_EXAM_NAME,
            description = SECONDARY_EXAM_DESCRIPTION,
            image = SECONDARY_EXAM_IMAGE,
            examQuestions = emptyList()
        )

    val PRIMARY_EXAM_ENTITY: ExamEntity =
        ExamEntity(
            examId = PRIMARY_EXAM_ID,
            name = PRIMARY_EXAM_NAME,
            description = PRIMARY_EXAM_DESCRIPTION,
            image = PRIMARY_EXAM_IMAGE,
        )

    val EXAM_WITH_QUESTIONS_ENTITY_LIST: List<ExamWithQuestions> = listOf(
        ExamWithQuestions(
            exam = PRIMARY_EXAM_ENTITY,
            questions = EXAM_QUESTION_ENTITY_LIST
        )
    )

    val SINGLE_EXAM_LIST = listOf(PRIMARY_EXAM)
    val MULTIPLE_EXAM_LIST = listOf(PRIMARY_EXAM, SECONDARY_EXAM)
}
